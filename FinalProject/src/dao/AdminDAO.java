package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;

public class AdminDAO {
	public static boolean checkLogin(String username, String password) {
        boolean isValid = false;

        try (Connection connection = JDBCUtil.getConnection()) {
        	String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        	PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet resultSet = pst.executeQuery()) {
                isValid = resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
