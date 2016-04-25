package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.SaveMortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.utils.DatabaseService;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.client.MortgageApplication;
import com.myorg.losmodel.model.questions.TxMortgageApplication;
import com.myorg.losmodel.util.ModelUtils;

@Service
public class MortgageDAOImpl implements MortgageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(MortgageDAOImpl.class);

	public MortgageApplicationResponse getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId,
			long mortgageId) {

		logger.info("getMortgageDetails :: clientOrAdvisor: {} clientOrAdvisorPartyId : {} mortgageId : {}",
				clientOrAdvisor, clientOrAdvisorPartyId, mortgageId);
		List<MortgageApplication> mortgageApplicationList = new ArrayList<MortgageApplication>();
		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();

		List<TxMortgageApplication> txApplicationList = new ArrayList<TxMortgageApplication>();
		try {

			String sql = "";
			if (mortgageId == 0l) {
				sql = "select t.TRANSACTION_ID,t.COMPLETE_TRAN_OBJ from mortgage.transaction t where t.party_id = ?";

				txApplicationList = jdbcTemplate.query(sql, new Object[] { clientOrAdvisorPartyId },
						new TxMortgageApplicationRowMapper());
			} else {
				sql = "select t.TRANSACTION_ID,t.COMPLETE_TRAN_OBJ from mortgage.transaction t where t.transaction_id = ?";

				txApplicationList = jdbcTemplate.query(sql, new Object[] { mortgageId },
						new TxMortgageApplicationRowMapper());
			}

			// Logic to Convert to Mortgage Application

			for (TxMortgageApplication txMortgageApplication : txApplicationList) {

				logger.info("getMortgageDetails :: Processing for Transaction ID : {}",
						txMortgageApplication.getTransactionId());

				ObjectMapper mapper = new ObjectMapper();
				mortgageApplicationList
						.add(mapper.readValue(txMortgageApplication.getJsonString(), MortgageApplication.class));
			}

			// Added list object to main List
			mortgageApplicationResponse.setMortgageApplications(mortgageApplicationList);
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("List of Applications");
			response.setReturnType("Success");
			mortgageApplicationResponse.setResponse(response);
			logger.info("getMortgageDetails :: Exit ");

		} catch (Exception e) {
			LOSResponse messageResponse = new LOSResponse();
			messageResponse.setReturnMsg("Login Failed");
			messageResponse.setReturnType("Error");
			mortgageApplicationResponse.setResponse(messageResponse);
			e.printStackTrace();
			return mortgageApplicationResponse;
		}

		return mortgageApplicationResponse;
	}

	public SaveMortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException {
		SaveMortgageApplicationResponse mortgageApplicationResponse = new SaveMortgageApplicationResponse();
		LOSResponse response = new LOSResponse();
		logger.info("saveMortgageDetails :: Entry ");
		MortgageApplication mortgageApplication = null;
		if (mortgageApplicationRequest != null && mortgageApplicationRequest.getMortgageApplication() != null) {
			long transId = -1;
			try {
				mortgageApplication = mortgageApplicationRequest.getMortgageApplication();

				Map<String, Object> attributeMap = ModelUtils
						.getObjectToDbAttributeMapping(mortgageApplicationRequest.getMortgageApplication());

				ObjectMapper mapper = new ObjectMapper();

				String jsonMortgageApplication = mapper.writeValueAsString(mortgageApplication);
				transId = mortgageApplication.getApplicationID();
				String statusCd = mortgageApplicationRequest.getSaveType();

				logger.info("saveMortgageDetails :: Calling upsert for Transaction ID {} ", transId);

				transId = upsert(attributeMap, transId, statusCd, jsonMortgageApplication,
						mortgageApplication.getClientPartyId());
				if (transId < 1) {
					logger.info("saveMortgageDetails :: Transaction ID {} ", transId);
					throw new Exception("Dao layer failed to save the mortgage");
				}
				response.setReturnMsg("Application Saved Sucessfully");
				response.setReturnType("Success");
				mortgageApplicationResponse.setResponse(response);
				mortgageApplicationResponse.setMortgageId(transId);
			} catch (Exception e) {
				response.setReturnMsg("Error in Saving the Application");
				response.setReturnType("Error");
				mortgageApplicationResponse.setMortgageId(transId);
				mortgageApplicationResponse.setResponse(response);
				e.printStackTrace();
			}
		}
		logger.info("saveMortgageDetails :: Exit ");
		return mortgageApplicationResponse;
	}

	// Shubhrajit - 25/4
	public long upsert(Map<String, Object> attributeMap, long tranId, String statusCd, String jsonObject,
			String clientPartyId) throws SQLException {

		// Forcing a commit

		// Vaibhav added some code.. in Model..
		// For this DAO method ModelUtils.java ->
		// getObjectToDbAttributeMapping(MortgageApplication)
		// Also need to pass JSON String in jsonObject - > MortgageApplication
		// Object
		Connection conn = DatabaseService.getConnection();
		// Shubhrajit - 25/4
		long ret = -1;
		conn.setAutoCommit(false);
		long transactionId = tranId;
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
				transactionId = ps0.getLong(1);

				// Executing insert to transaction
				ps1.setLong(1, transactionId);
				ps1.setString(2, statusCd);
				ps1.setString(3, jsonObject);
				ps1.setString(4, clientPartyId);
				ps1.execute();

			} else {

				// Executing update to transaction
				ps2.setString(1, statusCd);
				ps2.setString(2, jsonObject);
				ps2.setString(3, clientPartyId);
				ps2.setLong(4,transactionId);
				ps2.execute();
			}

			// In the case of transaction data item we always delete and
			// re-insert
			ret = transactionId;
			ps5.setLong(1, transactionId);
			ps5.execute();

			Iterator<String> it = attributeMap.keySet().iterator();
			while (it.hasNext()) {
				String columnNm = it.next();
				Object value = attributeMap.get(columnNm);

				if (value instanceof String) {
					// Scaler value
					ps3.setLong(1, transactionId);
					ps3.setString(2, (String) value);
					ps3.setNull(3, Types.CLOB);
					ps3.setInt(4, 1);
					ps3.setNull(5, Types.BLOB);
					ps3.setString(6, columnNm);
					ps3.execute();

				} else if (value instanceof byte[]) {
					// BLOB
					ps3.setLong(1, transactionId);
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
						ps3.setLong(1, transactionId);
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
				ret = -1;
			} catch (Exception e) {
				throw new SQLException("ROLLBACK FAILLED", e);

			}
			throw new SQLException("ROLLED BACK DUE TO EXCEPTION", ee);
		}

		return ret;
	}

	private class TxMortgageApplicationRowMapper implements RowMapper<TxMortgageApplication> {

		public TxMortgageApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
			TxMortgageApplication txMortgageApplication = new TxMortgageApplication();
			if (rs != null) {
				logger.info("TxMortgageApplication mapRow :: Transaction ID {} ", rs.getInt("TRANSACTION_ID"));
				txMortgageApplication.setTransactionId(rs.getInt("TRANSACTION_ID"));
				txMortgageApplication.setJsonString(rs.getClob("COMPLETE_TRAN_OBJ").toString());
			}
			return txMortgageApplication;

		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
