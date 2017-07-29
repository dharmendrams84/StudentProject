package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

public class DBUtils {
	//public static Logger logger = new java.utilLogger("DBUtils.class");
	private static  String username = "system";
	private static  String password = "system";
	private static  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static  String portno = "1521";
	private static  String sid = "orcl";
	private static  String driverClass = "oracle.jdbc.driver.OracleDriver";
	
	public DBUtils(){
		
	}
	
	/*@PostConstruct*/
	protected void initalizeDBUtils(){
		
		username = "system";
		password = "system";
		url = "jdbc:oracle:thin:@localhost:1521:orcl";
		portno = "1521";
		sid = "orcl";
		driverClass = "oracle.jdbc.driver.OracleDriver";
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

		}
		return connection;
	}
	
	public static void main(String[] args) {
		Connection connection = getConnection();
	}
}
