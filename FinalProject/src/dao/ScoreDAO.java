package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Score;
import util.JDBCUtil;

public class ScoreDAO implements DAOInterface<Score>{

	@Override
	public boolean insert(Score score) {
		try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO Score(id, score1, score2, score3) VALUES(?, ?, ?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, score.getStudentId());
                pst.setDouble(2, score.getScore1());
                pst.setDouble(3, score.getScore2());
                pst.setDouble(4, score.getScore3());
                
                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean delete(Score score) {
		try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM Score WHERE id = ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, score.getStudentId());
                
                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	public static boolean update(Score score) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE Score SET score1 = ?, score2 = ?, score3 = ? WHERE id = ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setDouble(1, score.getScore1());
                pst.setDouble(2, score.getScore2());
                pst.setDouble(3, score.getScore3());
                pst.setInt(4, score.getStudentId());

                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
