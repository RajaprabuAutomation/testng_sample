package com.test.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Database {
	
	static String url = "jdbc:mysql://localhost:3306/ngpcredential";
	static String dbname = "root";
	static String dbpassword = "Admin@123";
	
	
	public void pinDetailsToDB(String uniqueid, String username,String password, String pid, String puid, String pucode, String skup, String pin, String env) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbname, dbpassword);
			System.out.println("Entering PIN details to DB!");
			
			String query = "INSERT INTO ngpcredential.ngpdetails (uniqueid, Username, password, pid, puid, pucode, skup, pin, env)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      preparedStmt.setString(1, uniqueid);
			      preparedStmt.setString(2, username);
			      preparedStmt.setString(3, password);
			      preparedStmt.setString(4, pid);
			      preparedStmt.setString(5, puid);
			      preparedStmt.setString(6, pucode);
			      preparedStmt.setString(7, skup);
			      preparedStmt.setString(8, pin);
			      preparedStmt.setString(9, env);

			      // execute the preparedstatement
			      preparedStmt.execute();
			      
			      connection.close();

			      System.out.println("Written in DB!");
			}
			catch (SQLException e) {
				throw new IllegalStateException("Cannot Enter Details!", e);
	}
		
	}
	
/*	public static void main(String args[]) throws ClassNotFoundException {
		Database db = new Database();
		db.pinDetailsToDB("uniqueid", "UN_20191112123959@selenium.com", "Test@123", "1013340", "7267", "AON", "21389232", "J8XQBVBGXPH84", "PROD");

}*/
}