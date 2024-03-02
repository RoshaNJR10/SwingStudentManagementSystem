package com.nist.studentmanagementsystem.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	final static String DATABASE_NAME="school";
	final static String USER_NAME="root";
	final static String PASSWORD="";
	final static String DRIVER_NAME="com.mysql.cj.jdbc.Driver";//cj version8+
	final static String URL="jdbc:mysql://localhost/";

	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL+DATABASE_NAME, USER_NAME, PASSWORD);
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//return DriverManager.getConnection("jdbc:mysql://localhost/school", "root","");
	}
}
  