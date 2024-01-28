package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private static final String url = "jdbc:postgresql://localhost:5432/Admission";
	private static final String username = "postgres";
	private static final String password = "88888888";
    
	public static Connection getConnection() {
		Connection c = null;
		
		try {
	        c = DriverManager.getConnection(url, username, password);
	        if (c != null) {
                System.out.println("");
                
            } else {
                System.out.println("Cannot connect to Database!");
            }
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				System.out.println("Disconnected Database!");
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}