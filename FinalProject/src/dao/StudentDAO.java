package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Score;
import model.Student;
import util.JDBCUtil;

public class StudentDAO implements DAOInterface<Student> {

    @Override
    public boolean insert(Student student) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO Student (id, full_name, date_of_birth, gender, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, student.getStudentId());
                pst.setString(2, student.getFullName());
                pst.setDate(3, Date.valueOf(student.getDateOfBirth())); // Convert LocalDate to SQL Date
                pst.setString(4, student.getGender());
                pst.setString(5, student.getPhoneNumber());
                pst.setString(6, student.getAddress());

                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Student student) {
        try (Connection connection = JDBCUtil.getConnection()) {
            connection.setAutoCommit(false);

            try {
                ScoreDAO scoreDAO = new ScoreDAO();
                if (!scoreDAO.delete(new Score(student.getStudentId(), 0.0, 0.0, 0.0))) {
                    connection.rollback();
                    return false;
                }

                String sql = "DELETE FROM Student WHERE id = ?";
                try (PreparedStatement pst = connection.prepareStatement(sql)) {
                    pst.setInt(1, student.getStudentId());

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    public List<Student> getAllStudentsWithScores() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT s.id, s.full_name, s.date_of_birth, s.gender, s.phone_number, s.address, sc.score1, sc.score2, sc.score3 FROM Student s JOIN Score sc ON s.id = sc.id";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int studentId = rs.getInt("id");
                        String fullName = rs.getString("full_name");
                        Date dateOfBirth = rs.getDate("date_of_birth");
                        String gender = rs.getString("gender");
                        String phoneNumber = rs.getString("phone_number");
                        String address = rs.getString("address");
                        double score1 = rs.getDouble("score1");
                        double score2 = rs.getDouble("score2");
                        double score3 = rs.getDouble("score3");

                        Student student = new Student(studentId, fullName, dateOfBirth.toLocalDate(), gender, phoneNumber, address, new Score(studentId, score1, score2, score3));
                        studentList.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    
    public Student searchById(int studentId) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM Student s JOIN Score sc ON s.id = sc.id WHERE s.id = ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, studentId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        Student student = new Student();
                        student.setStudentId(rs.getInt("id"));
                        student.setFullName(rs.getString("full_name"));
                        student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                        student.setGender(rs.getString("gender"));
                        student.setPhoneNumber(rs.getString("phone_number"));
                        student.setAddress(rs.getString("address"));

                        Score score = new Score();
                        score.setScore1(rs.getDouble("score1"));
                        score.setScore2(rs.getDouble("score2"));
                        score.setScore3(rs.getDouble("score3"));
                       
                        student.setScore(score);

                        return student;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> getTopStudents(int limit) {
        List<Student> topStudents = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT s.id, s.full_name, s.date_of_birth, s.gender, s.phone_number, s.address, sc.score1, sc.score2, sc.score3 " +
                         "FROM Student s JOIN Score sc ON s.id = sc.id " +
                         "ORDER BY (sc.score1 + sc.score2 + sc.score3) DESC LIMIT ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setInt(1, limit);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int studentId = rs.getInt("id");
                        String fullName = rs.getString("full_name");
                        Date dateOfBirth = rs.getDate("date_of_birth");
                        String gender = rs.getString("gender");
                        String phoneNumber = rs.getString("phone_number");
                        String address = rs.getString("address");
                        double score1 = rs.getDouble("score1");
                        double score2 = rs.getDouble("score2");
                        double score3 = rs.getDouble("score3");

                        Student student = new Student(studentId, fullName, dateOfBirth.toLocalDate(), gender, phoneNumber, address, new Score(studentId, score1, score2, score3));
                        topStudents.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topStudents;
    }
    
    public static boolean update(Student student) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE Student SET full_name = ?, date_of_birth = ?, gender = ?, phone_number = ?, address = ? WHERE id = ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, student.getFullName());
                pst.setDate(2, Date.valueOf(student.getDateOfBirth()));
                pst.setString(3, student.getGender());
                pst.setString(4, student.getPhoneNumber());
                pst.setString(5, student.getAddress());
                pst.setInt(6, student.getStudentId());

                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}


