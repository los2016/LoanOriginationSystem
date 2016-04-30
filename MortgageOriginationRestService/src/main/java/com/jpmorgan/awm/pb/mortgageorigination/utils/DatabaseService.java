package com.jpmorgan.awm.pb.mortgageorigination.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

/**
 * Database object to Get Connection
 * 
 * @author Gagan Goswami
 */
public class DatabaseService {

	private static Connection con;
	private static final String Driver = "oracle.jdbc.driver.OracleDriver";
	// private static final String ConnectionString =
	// "jdbc:oracle:thin:@localhost:1521:XE";
	// private static final String user = "mortgage";

	@Value("${oracle.db.url}")
	private static String connectionString;

	@Value("${oracle.db.user}")
	private static String user;

	@Value("${oracle.db.password}")
	private static String pwd;

	public DatabaseService() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		con = DriverManager.getConnection(connectionString, user, pwd);
		return con;
	}
}