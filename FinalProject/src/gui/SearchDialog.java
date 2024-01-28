package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import dao.StudentDAO;
import model.Student;
import java.awt.Font;

public class SearchDialog extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable studentTable;
    private JTable topStudentsTable;
    private JFormattedTextField studentIdInput;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FlatLightLaf.setup();
                	
                    SearchDialog frame = new SearchDialog();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setTitle("Search");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SearchDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 150, 850, 440);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(83, 210, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel searchByIdLabel = new JLabel("Search by Student ID");
        searchByIdLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        searchByIdLabel.setBounds(65, 28, 192, 20);
        contentPane.add(searchByIdLabel);

        JLabel studentIdInputLabel = new JLabel("Input Student ID: ");
        studentIdInputLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        studentIdInputLabel.setBounds(210, 70, 110, 14);
        contentPane.add(studentIdInputLabel);

        studentIdInput = new JFormattedTextField();
        studentIdInput.setBounds(320, 67, 180, 20);
        contentPane.add(studentIdInput);

        JButton findStudentButton = new JButton("FIND");
        findStudentButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        findStudentButton.setBackground(new Color(255, 255, 128));
        findStudentButton.setBounds(518, 66, 89, 23);
        findStudentButton.setFocusable(false);
        contentPane.add(findStudentButton);

        DefaultTableModel studentTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Full Name", "Date of Birth", "Gender", "Phone Number", "Address", "Score 1", "Score 2", "Score 3" }
        );

        studentTable = new JTable(studentTableModel);
        studentTable.getTableHeader().setPreferredSize(studentTable.getTableHeader().getPreferredSize());
        studentTable.setRowHeight(30);
        studentTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        studentScrollPane.setBounds(25, 100, 787, 52);
        contentPane.add(studentScrollPane);

        JLabel topStudentsLabel = new JLabel("Top");
        topStudentsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        topStudentsLabel.setBounds(276, 198, 41, 23);
        contentPane.add(topStudentsLabel);

        JButton findTopStudentsButton = new JButton("FIND");
        findTopStudentsButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        findTopStudentsButton.setBackground(new Color(255, 255, 128));
        findTopStudentsButton.setBounds(507, 200, 89, 23);
        findTopStudentsButton.setFocusable(false);
        contentPane.add(findTopStudentsButton);

        DefaultTableModel topStudentsTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Full Name", "Date of Birth", "Gender", "Phone Number", "Address", "Score 1", "Score 2", "Score 3" }
        );

        topStudentsTable = new JTable(topStudentsTableModel);
        topStudentsTable.getTableHeader().setPreferredSize(studentTable.getTableHeader().getPreferredSize());
        topStudentsTable.setRowHeight(30);
        topStudentsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane topStudentsScrollPane = new JScrollPane(topStudentsTable);
        topStudentsScrollPane.setBounds(25, 232, 787, 142);
        contentPane.add(topStudentsScrollPane);

        // Decoration
        JLabel searchImageLabel = new JLabel();
        searchImageLabel.setBounds(744, 17, 68, 67);
        contentPane.add(searchImageLabel);
        searchImageLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\searching.png"));

        JFormattedTextField topStudentsCountInput = new JFormattedTextField();
        topStudentsCountInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
        topStudentsCountInput.setBounds(315, 201, 41, 20);
        contentPane.add(topStudentsCountInput);

        JLabel highestSummaryLabel = new JLabel("highest summary");
        highestSummaryLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        highestSummaryLabel.setBounds(366, 198, 134, 23);
        contentPane.add(highestSummaryLabel);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        

        findStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentId = Integer.parseInt(studentIdInput.getText());
                    StudentDAO studentDAO = new StudentDAO();

                    Student student = studentDAO.searchById(studentId);

                    if (student != null) {
                        DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
                        tableModel.setRowCount(0);
                        
                        Date dob = java.sql.Date.valueOf(student.getDateOfBirth());
                        
                        if (student.getScore() != null) {
                            tableModel.addRow(new Object[]{
                                    student.getStudentId(),
                                    student.getFullName(),
                                    sdf.format(dob),
                                    student.getGender(),
                                    student.getPhoneNumber(),
                                    student.getAddress(),
                                    student.getScore().getScore1(),
                                    student.getScore().getScore2(),
                                    student.getScore().getScore3()
                            });
                        } else {
                            tableModel.addRow(new Object[]{
                                    student.getStudentId(),
                                    student.getFullName(),
                                    student.getDateOfBirth(),
                                    student.getGender(),
                                    student.getPhoneNumber(),
                                    student.getAddress(),
                                    0.0,
                                    0.0,
                                    0.0
                            });
                        }
                    } else {
                        JOptionPane.showMessageDialog(SearchDialog.this, "Student not found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SearchDialog.this, "Please enter a valid student ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        findTopStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentDAO studentDAO = new StudentDAO();

                int numberLimited = Integer.parseInt(topStudentsCountInput.getText());

                List<Student> topStudents = studentDAO.getTopStudents(numberLimited);

                DefaultTableModel tableModel_1 = (DefaultTableModel) topStudentsTable.getModel();
                tableModel_1.setRowCount(0);
                
                           
                for (Student student : topStudents) {
                	Date dob = java.sql.Date.valueOf(student.getDateOfBirth());
                	
                    if (student != null) {
                        if (student.getScore() != null) {
                            tableModel_1.addRow(new Object[]{
                                    student.getStudentId(),
                                    student.getFullName(),
                                    sdf.format(dob),
                                    student.getGender(),
                                    student.getPhoneNumber(),
                                    student.getAddress(),
                                    student.getScore().getScore1(),
                                    student.getScore().getScore2(),
                                    student.getScore().getScore3()
                            });
                        } else {
                            tableModel_1.addRow(new Object[]{
                                    student.getStudentId(),
                                    student.getFullName(),
                                    student.getDateOfBirth(),
                                    student.getGender(),
                                    student.getPhoneNumber(),
                                    student.getAddress(),
                                    0.0,
                                    0.0,
                                    0.0
                            });
                        }
                    }
                }
            }
        });
    }
}
