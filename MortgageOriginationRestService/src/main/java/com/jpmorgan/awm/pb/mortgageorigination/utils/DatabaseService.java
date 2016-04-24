package com.jpmorgan.awm.pb.mortgageorigination.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database object to Get Connection
 * 
 * @author Gagan Goswami
 */
public class DatabaseService {

	private static Connection con;
	private static final String Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String ConnectionString = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "mortgage";
	private static final String pwd = "password";

	public DatabaseService() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		con = DriverManager.getConnection(ConnectionString, user, pwd);
		return con;
	}
}