package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.utils.DatabaseService;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.client.MortgageApplication;
import com.myorg.losmodel.util.ModelUtils;

@Service
public class MortgageDAOImpl implements MortgageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	public List<MortgageApplicationResponse> getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId) {
		// TODO Need to replace DAO logic to get Mortgage applications from
		// database as JSON String.

		List<MortgageApplicationResponse> mortgageApplicationsList = new ArrayList<MortgageApplicationResponse>();
		MortgageApplication mortgageApplication = new MortgageApplication();
		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();
		mortgageApplicationResponse.setMortgageApplication(mortgageApplication);
		LOSResponse response = new LOSResponse();
		response.setReturnMsg("List of Applications");
		response.setReturnType("Success");
		mortgageApplicationResponse.setResponse(response);
		mortgageApplicationsList.add(mortgageApplicationResponse);
		return mortgageApplicationsList;
	}

	public MortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException {
		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();
		LOSResponse response = new LOSResponse();

		MortgageApplication mortgageApplication = null;
		if (mortgageApplicationRequest != null && mortgageApplicationRequest.getMortgageApplication() != null) {

			try {
				mortgageApplication = mortgageApplicationRequest.getMortgageApplication();

				Map<String, Object> attributeMap = ModelUtils
						.getObjectToDbAttributeMapping(mortgageApplicationRequest.getMortgageApplication());

				ObjectMapper mapper = new ObjectMapper();

				String jsonMortgageApplication = mapper.writeValueAsString(mortgageApplication);
				int transId = (int) mortgageApplication.getApplicationID();
				String statusCd = mortgageApplicationRequest.getSaveType();
				upsert(attributeMap, transId, statusCd, jsonMortgageApplication,
						mortgageApplication.getClientPartyId());

				response.setReturnMsg("Application Saved Sucessfully");
				response.setReturnType("Success");
				mortgageApplicationResponse.setResponse(response);
				mortgageApplicationResponse.setMortgageId(23444345243l);

			} catch (JsonProcessingException e) {
				response.setReturnMsg("Error in Saving the Application");
				response.setReturnType("Error");
				mortgageApplicationResponse.setResponse(response);
				e.printStackTrace();
			}
		}

		return mortgageApplicationResponse;
	}

	public boolean upsert(Map<String, Object> attributeMap, int tranId, String statusCd, String jsonObject,
			String clientPartyId) throws SQLException {

		// Vaibhav added some code.. in Model..
		// For this DAO method ModelUtils.java ->
		// getObjectToDbAttributeMapping(MortgageApplication)
		// Also need to pass JSON String in jsonObject - > MortgageApplication
		// Object
		Connection conn = DatabaseService.getConnection();
		boolean ret = true;
		conn.setAutoCommit(false);
		int transactionId = tranId;
		PreparedStatement ps1 = conn.prepareStatement(
				"insert into transaction(transaction_id,status_cd,complete_tran_obj,party_id) values (?,?,?,?)");
		PreparedStatement ps2 = conn.prepareStatement(
				"update transaction set status_cd = ?, complete_tran_obj = ?, party_id = ? where transaction_id = ?");
		PreparedStatement ps3 = conn.prepareStatement("insert into transaction_data_item"
				+ " select ?,attribute_id,?,?,?,? from attribute_metadata where col_nm = ?");

		/*
		 * PreparedStatement ps4 = conn.prepareStatement(
		 * "update transaction_data_item set col_id = a.attribute_id, col_simple_val = ?,col_large_val =? "
		 * +
		 * "sequence_no = , col_large_bin_val = ? from  transaction_data_item t"
		 * + " inner join attribute_metadata a on a.attribute_id = t.col_id ");
		 */

		PreparedStatement ps5 = conn.prepareStatement("delete transaction_data_item" + " where transaction_id = ?");

		// PreparedStatement ps6 =

		try {
			if (transactionId <= 0) { // New transaction

				// Generate transaction_if
				CallableStatement ps0 = conn.prepareCall("{? = call get_last_key_id('TRANSACTION_ID')}");
				ps0.registerOutParameter(1, Types.DOUBLE);
				ps0.executeUpdate();
				transactionId = ps0.getInt(1);

				// Executing insert to transaction
				ps1.setInt(1, transactionId);
				ps1.setString(2, statusCd);
				ps1.setString(3, jsonObject);
				ps1.setString(4, clientPartyId);
				ps1.execute();

			} else {

				// Executing update to transaction
				ps2.setString(1, statusCd);
				ps2.setString(2, jsonObject);
				ps2.setString(3, clientPartyId);
			}

			// In the case of transaction data item we always delete and
			// re-insert

			ps5.setInt(1, transactionId);
			ps5.execute();

			Iterator<String> it = attributeMap.keySet().iterator();
			while (it.hasNext()) {
				String columnNm = it.next();
				Object value = attributeMap.get(columnNm);

				if (value instanceof String) {
					// Scaler value
					ps3.setInt(1, transactionId);
					ps3.setString(2, (String) value);
					ps3.setNull(3, Types.CLOB);
					ps3.setInt(4, 1);
					ps3.setNull(5, Types.BLOB);
					ps3.setString(6, columnNm);
					ps3.execute();

				} else if (value instanceof byte[]) {
					// BLOB
					ps3.setInt(1, transactionId);
					ps3.setNull(2, Types.VARCHAR);
					ps3.setNull(3, Types.CLOB);
					ps3.setInt(4, 1);
					ps3.setBytes(5, (byte[]) value);
					ps3.setString(6, columnNm);
					ps3.execute();

				} else if (value instanceof ArrayList<?>) {
					// Multi-Select
					Iterator<?> mul = ((ArrayList<?>) value).iterator();
					int seq = 1;
					while (mul.hasNext()) {

						String scalerMultiValue = (String) mul.next();
						ps3.setInt(1, transactionId);
						ps3.setString(2, scalerMultiValue);
						ps3.setNull(3, Types.CLOB);
						ps3.setInt(4, seq++);
						ps3.setNull(5, Types.BLOB);
						ps3.setString(6, columnNm);
						ps3.execute();
					}
				}

			}

			// System.out.println(transactionId);
			conn.commit();

		} catch (Exception ee) {
			try {
				conn.rollback();
				ret = false;
			} catch (Exception e) {
				throw new SQLException("ROLLBACK FAILLED", e);

			}
			throw new SQLException("ROLLED BACK DUE TO EXCEPTION", ee);
		}

		return ret;
	}

	/*
	 * public MortgageApplicationResponse
	 * saveMortgageDetails(MortgageApplicationRequest
	 * mortgageApplicationRequest) throws SQLException {
	 * MortgageApplicationResponse mortgageApplicationResponse = new
	 * MortgageApplicationResponse();
	 * 
	 * // TODO Need to replace DAO logic once we will have Query from //
	 * Shubhrajit for Save Mortgage Application
	 * 
	 * LOSResponse response = new LOSResponse(); response.setReturnMsg(
	 * "Application Saved Sucessfully"); response.setReturnType("Success");
	 * mortgageApplicationResponse.setResponse(response);
	 * mortgageApplicationResponse.setMortgageId(23444345243l);
	 * 
	 * return mortgageApplicationResponse; }
	 */

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
