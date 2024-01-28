package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import dao.ScoreDAO;
import dao.StudentDAO;
import model.Score;
import model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.sql.Date;
import java.time.ZoneId;
import java.awt.Font;
import java.awt.Color;

public class UpdateDialog extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField studentIdField;
    private JTextField fullNameField;
    private JDateChooser dateOfBirthField;
    private JLabel lblGender;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JTextField score1Field;
    private JTextField score2Field;
    private JTextField score3Field;
    private JComboBox<Object> genderComboBox;
    AdmissionManagement admissionManagement;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FlatLightLaf.setup();
                	
                    UpdateDialog frame = new UpdateDialog();
                    frame.setVisible(true);
                    frame.setTitle("Update");
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UpdateDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(380, 170, 560, 374);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(83, 210, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID Selected");
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        lblNewLabel.setBounds(36, 48, 83, 14);
        contentPane.add(lblNewLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(129, 43, 74, 20);
        contentPane.add(studentIdField);
        studentIdField.setColumns(10);
        studentIdField.setEditable(false);

        fullNameField = new JTextField();
        fullNameField.setColumns(10);
        fullNameField.setBounds(129, 81, 148, 20);
        contentPane.add(fullNameField);

        JLabel lblStudentName = new JLabel("Student name");
        lblStudentName.setFont(new Font("Consolas", Font.BOLD, 12));
        lblStudentName.setBounds(36, 83, 97, 19);
        contentPane.add(lblStudentName);

        JLabel lblDateOfBirth = new JLabel("Date of birth");
        lblDateOfBirth.setFont(new Font("Consolas", Font.BOLD, 12));
        lblDateOfBirth.setBounds(36, 122, 97, 14);
        contentPane.add(lblDateOfBirth);

        dateOfBirthField = new JDateChooser();
        dateOfBirthField.setBounds(129, 118, 148, 20);
        contentPane.add(dateOfBirthField);

        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Consolas", Font.BOLD, 12));
        lblGender.setBounds(310, 122, 50, 14);
        contentPane.add(lblGender);

        genderComboBox = new JComboBox<Object>();
        genderComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Male", "Female"}));
        genderComboBox.setBounds(361, 118, 89, 20);
        contentPane.add(genderComboBox);

        phoneNumberField = new JTextField();
        phoneNumberField.setColumns(10);
        phoneNumberField.setBounds(129, 156, 148, 20);
        contentPane.add(phoneNumberField);

        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setFont(new Font("Consolas", Font.BOLD, 12));
        lblPhone.setBounds(36, 159, 81, 14);
        contentPane.add(lblPhone);

        addressField = new JTextField();
        addressField.setColumns(10);
        addressField.setBounds(361, 156, 148, 20);
        contentPane.add(addressField);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Consolas", Font.BOLD, 12));
        lblAddress.setBounds(310, 159, 50, 14);
        contentPane.add(lblAddress);

        score1Field = new JTextField();
        score1Field.setColumns(10);
        score1Field.setBounds(206, 193, 180, 20);
        contentPane.add(score1Field);

        JLabel lblScore = new JLabel("Score 1");
        lblScore.setFont(new Font("Consolas", Font.BOLD, 12));
        lblScore.setBounds(142, 197, 53, 14);
        contentPane.add(lblScore);

        score2Field = new JTextField();
        score2Field.setColumns(10);
        score2Field.setBounds(206, 227, 180, 20);
        contentPane.add(score2Field);

        JLabel lblScore_1 = new JLabel("Score 2");
        lblScore_1.setFont(new Font("Consolas", Font.BOLD, 12));
        lblScore_1.setBounds(142, 230, 53, 14);
        contentPane.add(lblScore_1);

        score3Field = new JTextField();
        score3Field.setColumns(10);
        score3Field.setBounds(206, 259, 180, 20);
        contentPane.add(score3Field);

        JLabel lblScore_2 = new JLabel("Score 3");
        lblScore_2.setFont(new Font("Consolas", Font.BOLD, 12));
        lblScore_2.setBounds(142, 263, 53, 14);
        contentPane.add(lblScore_2);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        updateButton.setBackground(new Color(255, 255, 128));
        updateButton.setBounds(229, 296, 89, 23);
        contentPane.add(updateButton);
        
        JLabel img = new JLabel();
        img.setBounds(440, 26, 70, 70);
        contentPane.add(img);
        img.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\updating.png"));
        
        //event
        updateButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performUpdate();
            }
        });
    }
    
    public void setStudentData(Student student, Score score) {
        studentIdField.setText(String.valueOf(student.getStudentId()));
        fullNameField.setText(student.getFullName());
        dateOfBirthField.setDate(Date.from(student.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        genderComboBox.setSelectedItem(student.getGender());
        phoneNumberField.setText(student.getPhoneNumber());
        addressField.setText(student.getAddress());

        if (score != null) {
            score1Field.setText(String.valueOf(score.getScore1()));
            score2Field.setText(String.valueOf(score.getScore2()));
            score3Field.setText(String.valueOf(score.getScore3()));
        }
    }
    
    private void performUpdate() {
        try {
            int updatedStudentId = Integer.parseInt(studentIdField.getText());
            String updatedFullName = fullNameField.getText();
            Date updatedDateOfBirth = Date.valueOf(dateOfBirthField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            String updatedGender = genderComboBox.getSelectedItem().toString();
            String updatedPhoneNumber = phoneNumberField.getText();
            String updatedAddress = addressField.getText();
            double updatedScore1 = Double.parseDouble(score1Field.getText());
            double updatedScore2 = Double.parseDouble(score2Field.getText());
            double updatedScore3 = Double.parseDouble(score3Field.getText());

            if (isValidScore(updatedScore1) && isValidScore(updatedScore2) && isValidScore(updatedScore3)) {
                Student updatedStudent = new Student(updatedStudentId, updatedFullName, updatedDateOfBirth.toLocalDate(), updatedGender, updatedPhoneNumber, updatedAddress);
                Score updatedScore = new Score(updatedStudentId, updatedScore1, updatedScore2, updatedScore3);

                updateData(updatedStudent, updatedScore);

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid score value. Please enter scores between 0.0 and 10.0.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData(Student updatedStudent, Score updatedScore) {
        boolean studentUpdated = StudentDAO.update(updatedStudent);
        boolean scoreUpdated = ScoreDAO.update(updatedScore);

        if (studentUpdated && scoreUpdated) {
            JOptionPane.showMessageDialog(this, "Update successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Update failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isValidScore(double score) {
        return score >= 0.0 && score <= 10.0;
    }
}
