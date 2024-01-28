package gui;

import java.awt.Color;
import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import dao.ScoreDAO;
import dao.StudentDAO;
import model.Score;
import model.Student;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.awt.event.ActionEvent;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AdmissionManagement extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel managementContentPane;
    private JTable table;
    private JTextField studentIdTextField;
    private JTextField fullNameTextField;
    private JDateChooser dateOfBirthChooser;
    private JTextField phoneNumberTextField;
    private JTextField addressTextField;
    private JTextField score1TextField;
    private JTextField score2TextField;
    private JTextField score3TextField;
    private SearchDialog searchDialogFrame;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FlatLightLaf.setup();
                	
                    AdmissionManagement frame = new AdmissionManagement();
                    frame.setTitle("Admission Management");
                    frame.setVisible(true);
                    frame.setResizable(false);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdmissionManagement() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(110, 40, 1070, 600);
        managementContentPane = new JPanel();
        managementContentPane.setBackground(new Color(68, 196, 195));
        managementContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(managementContentPane);
        managementContentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 244, 189));
        panel.setBounds(822, 0, 234, 563);
        managementContentPane.add(panel);
        panel.setLayout(null);
        
        
        
        //Button
        JButton insertButton = new JButton("INSERT", new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\insert.png"));
        insertButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        insertButton.setBackground(new Color(129, 252, 195));
        insertButton.setBounds(68, 246, 100, 30);
        insertButton.setFocusable(false);
        panel.add(insertButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        deleteButton.setBackground(new Color(129, 252, 195));
        deleteButton.setBounds(68, 307, 100, 30);
        deleteButton.setFocusable(false);
        panel.add(deleteButton);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        updateButton.setBackground(new Color(129, 252, 195));
        updateButton.setBounds(68, 366, 100, 30);
        updateButton.setFocusable(false);
        panel.add(updateButton);
        
        JButton searchButton = new JButton("SEARCH", null);
        searchButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        searchButton.setBackground(new Color(129, 252, 195));
        searchButton.setBounds(68, 426, 100, 30);
        searchButton.setFocusable(false);
        panel.add(searchButton);
        
        JButton refreshButton = new JButton("REFRESH", null);
        refreshButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        refreshButton.setFocusable(false);
        refreshButton.setBackground(new Color(128, 255, 128));
        refreshButton.setBounds(699, 210, 100, 20);
        managementContentPane.add(refreshButton);
        
        JButton logoutButton = new JButton("LOG OUT", null);
        logoutButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        logoutButton.setFocusable(false);
        logoutButton.setBackground(new Color(255, 128, 128));
        logoutButton.setBounds(118, 502, 100, 20);
        panel.add(logoutButton);
        
        JButton exportExcelButton  = new JButton("EXPORT", null);
        exportExcelButton.setFont(new Font("Segoe UI Light", Font.BOLD, 13));
        exportExcelButton.setFocusable(false);
        exportExcelButton.setBackground(new Color(128, 255, 128));
        exportExcelButton.setBounds(31, 210, 100, 20);
        managementContentPane.add(exportExcelButton );


        //Table
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Full Name", "Date of Birth", "Gender", "Phone Number", "Address", "Score 1", "Score 2", "Score 3"
        	}
        ) {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
        		Integer.class, String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class
        	};
        	@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        	@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        }); 
        table.setBackground(new Color(255, 255, 255));
        table.getTableHeader().setPreferredSize(table.getTableHeader().getPreferredSize());
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(0);
        table.setRowSelectionAllowed(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(31, 242, 768, 303);
        managementContentPane.add(scrollPane);
        
        
        
        JLabel studentIdLabel = new JLabel("Student ID");
        studentIdLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        studentIdLabel.setBounds(54, 94, 74, 14);
        managementContentPane.add(studentIdLabel);
        
        studentIdTextField = new JTextField();
        studentIdTextField.setBounds(145, 92, 130, 20);
        managementContentPane.add(studentIdTextField);
        studentIdTextField.setColumns(10);
        
        JLabel fullNameLabel = new JLabel("Student name");
        fullNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        fullNameLabel.setBounds(54, 131, 95, 14);
        managementContentPane.add(fullNameLabel);
        
        fullNameTextField = new JTextField();
        fullNameTextField.setBounds(145, 130, 130, 20);
        managementContentPane.add(fullNameTextField);
        fullNameTextField.setColumns(10);
        
        JLabel dateOfBirthLabel = new JLabel("Date of birth");
        dateOfBirthLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        dateOfBirthLabel.setBounds(54, 170, 96, 14);
        managementContentPane.add(dateOfBirthLabel);
        
        dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setBounds(145, 168, 130, 20);
        managementContentPane.add(dateOfBirthChooser);
        
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        genderLabel.setBounds(346, 94, 57, 14);
        managementContentPane.add(genderLabel);
        
        JComboBox<Object> genderComboBox = new JComboBox<Object>();
        genderComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Male", "Female"}));
        genderComboBox.setBounds(404, 92, 130, 22);
        managementContentPane.add(genderComboBox);
        
        JLabel phoneNumberLabel = new JLabel("Phone");
        phoneNumberLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        phoneNumberLabel.setBounds(346, 131, 57, 14);
        managementContentPane.add(phoneNumberLabel);
        
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setColumns(10);
        phoneNumberTextField.setBounds(404, 130, 130, 20);
        managementContentPane.add(phoneNumberTextField);
        
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        addressLabel.setBounds(346, 170, 57, 14);
        managementContentPane.add(addressLabel);
        
        addressTextField = new JTextField();
        addressTextField.setColumns(10);
        addressTextField.setBounds(404, 168, 130, 20);
        managementContentPane.add(addressTextField);
        
        JLabel score1Label = new JLabel("Score 1");
        score1Label.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        score1Label.setBounds(593, 94, 52, 14);
        managementContentPane.add(score1Label);
        
        score1TextField = new JTextField();
        score1TextField.setColumns(10);
        score1TextField.setBounds(649, 92, 130, 20);
        managementContentPane.add(score1TextField);
        
        JLabel score2Label = new JLabel("Score 2");
        score2Label.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        score2Label.setBounds(593, 131, 52, 14);
        managementContentPane.add(score2Label);
        
        score2TextField = new JTextField();
        score2TextField.setColumns(10);
        score2TextField.setBounds(649, 130, 130, 20);
        managementContentPane.add(score2TextField);
        
        JLabel score3Label = new JLabel("Score 3");
        score3Label.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        score3Label.setBounds(593, 170, 52, 14);
        managementContentPane.add(score3Label);
        
        score3TextField = new JTextField();
        score3TextField.setColumns(10);
        score3TextField.setBounds(649, 168, 130, 20);
        managementContentPane.add(score3TextField);

        JLabel managementProgramLabel = new JLabel("Management Program");
        managementProgramLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        managementProgramLabel.setBounds(294, 19, 279, 46);
        managementContentPane.add(managementProgramLabel);
        
        
        
        //Decoration
        JLabel img1 = new JLabel();
        img1.setBounds(31, 14, 68, 67);
        managementContentPane.add(img1);
        img1.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\deco_4.png"));
        
        JLabel img2 = new JLabel();
        img2.setBounds(739, 11, 68, 67);
        managementContentPane.add(img2);
        img2.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\deco_2.png"));
        
        JLabel img3 = new JLabel();
        img3.setBounds(16, 476, 68, 67);
        panel.add(img3);
        img3.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\deco_3.png"));
            
        JLabel avt = new JLabel();
        avt.setBounds(29, 37, 180, 178);
        panel.add(avt);
        avt.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\avatar_deco.png"));
               
        JLabel img2_1 = new JLabel();
        img2_1.setBounds(673, 208, 24, 25);
        managementContentPane.add(img2_1);
        img2_1.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\refresh-icon.png"));
        
        
        
        
        
         
        //Event Handling
        //insert
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(studentIdTextField.getText());

                    if (isIdExists(id)) {
                        JOptionPane.showMessageDialog(AdmissionManagement.this, "Student ID is invalid. Please enter another ID.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String fullName = fullNameTextField.getText();
                    java.util.Date dateOfBirth = dateOfBirthChooser.getDate();
                    String gender = genderComboBox.getSelectedItem().toString();
                    String phoneNumber = phoneNumberTextField.getText();
                    String address = addressTextField.getText();
                    double score1 = Double.parseDouble(score1TextField.getText());
                    double score2 = Double.parseDouble(score2TextField.getText());
                    double score3 = Double.parseDouble(score3TextField.getText());

                    if (isValidScore(score1) && isValidScore(score2) && isValidScore(score3)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{id, fullName, sdf.format(dateOfBirth), gender, phoneNumber, address, score1, score2, score3});

                        Student student = new Student(id, fullName, dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), gender, phoneNumber, address);
                        Score score = new Score(id, score1, score2, score3);
                        StudentDAO studentDAO = new StudentDAO();
                        studentDAO.insert(student);

                        ScoreDAO scoreDAO = new ScoreDAO();
                        scoreDAO.insert(score);
                        
                        JOptionPane.showMessageDialog(AdmissionManagement.this, "Student added.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(AdmissionManagement.this, "Invalid score value. Please enter scores between 0.0 and 10.0.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AdmissionManagement.this, "Invalid input. Please enter valid data.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean isValidScore(double score) {
                return score >= 0.0 && score <= 10.0;
            }

            private boolean isIdExists(int id) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    int existingId = (int) model.getValueAt(i, 0);
                    if (id == existingId) {
                        return true;
                    }
                }
                return false;
            }
        });

        
        	
        //delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length > 0) {
                    int option = JOptionPane.showConfirmDialog(AdmissionManagement.this, "Are you sure about deleting?", "Deleting confirmation", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        StudentDAO studentDAO = new StudentDAO();
                        ScoreDAO scoreDAO = new ScoreDAO();
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                      
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            int selectedRow = selectedRows[i];

                            if (selectedRow < model.getRowCount()) {
                                int studentId = (int) table.getValueAt(selectedRow, 0);

                                Student studentToDelete = new Student();
                                studentToDelete.setStudentId(studentId);
                                Score scoreToDelete = new Score();
                                scoreToDelete.setStudentId(studentId);

                                boolean studentDeleted = studentDAO.delete(studentToDelete);
                                boolean scoreDeleted = scoreDAO.delete(scoreToDelete);

                                if (studentDeleted || scoreDeleted) {
                                    model.removeRow(selectedRow);
                                }
                            }
                        }

                        JOptionPane.showMessageDialog(AdmissionManagement.this, "Data has been removed successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(AdmissionManagement.this, "Please choose 1 row to remove from table.");
                }
            }
        });

        
        //search
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openSearchDialog();
        	}
        });
        
        //update
        updateButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openUpdateDialog();
        	}
        });
        
        //refresh
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	loadDataToTable();
            }
        });
        
        //log out
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(AdmissionManagement.this,
                        "Are you sure you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    dispose();

                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.setTitle("Login");
                    loginDialog.setVisible(true);
                    loginDialog.setResizable(false);
                }
            }
        });
        
        //export
        exportExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });


        loadDataToTable();
    }
    
    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        StudentDAO studentDAO = new StudentDAO();
        List<Student> studentList = studentDAO.getAllStudentsWithScores();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Student student : studentList) {
            model.addRow(new Object[]{
                    student.getStudentId(),
                    student.getFullName(),
                    sdf.format(Date.from(student.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant())),
                    student.getGender(),
                    student.getPhoneNumber(),
                    student.getAddress(),
                    student.getScore().getScore1(),
                    student.getScore().getScore2(),
                    student.getScore().getScore3()
            });
        }
    }
    
    private void openSearchDialog() {
        if (searchDialogFrame == null || !searchDialogFrame.isVisible()) {
        	searchDialogFrame = new SearchDialog();
        	searchDialogFrame.setTitle("Search");
        	searchDialogFrame.setVisible(true);
        	searchDialogFrame.setResizable(false);
        	searchDialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        	searchDialogFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                	searchDialogFrame = null;
                }
            });
        }
    }
    
    private void openUpdateDialog() {
        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length > 0) {
            for (int selectedRow : selectedRows) {
                int id = (int) table.getValueAt(selectedRow, 0);
                String fullName = (String) table.getValueAt(selectedRow, 1);
                String dateOfBirthStr = (String) table.getValueAt(selectedRow, 2);
                String gender = (String) table.getValueAt(selectedRow, 3);
                String phoneNumber = (String) table.getValueAt(selectedRow, 4);
                String address = (String) table.getValueAt(selectedRow, 5);
                double score1 = (double) table.getValueAt(selectedRow, 6);
                double score2 = (double) table.getValueAt(selectedRow, 7);
                double score3 = (double) table.getValueAt(selectedRow, 8);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateOfBirth = null;
                try {
                    dateOfBirth = sdf.parse(dateOfBirthStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                LocalDate localDateOfBirth = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Student selectedStudent = new Student(id, fullName, localDateOfBirth, gender, phoneNumber, address);
                Score selectedScore = new Score(id, score1, score2, score3);

                UpdateDialog updateDialog = new UpdateDialog();
                updateDialog.setStudentData(selectedStudent, selectedScore);
                
                updateDialog.setTitle("Update");
                updateDialog.setVisible(true);
                updateDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select rows to update.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportToExcel() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Data");

        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }
        
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object cellValue = model.getValueAt(row, col);

                if (cellValue instanceof Number) {
                    cell.setCellValue(((Number) cellValue).doubleValue());
                } else if (cellValue instanceof String) {
                    cell.setCellValue((String) cellValue);
                } else {
                    cell.setCellValue(cellValue.toString());
                }
            }
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            if (!selectedFile.getName().toLowerCase().endsWith(".xlsx")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".xlsx");
            }

            try (FileOutputStream fileOut = new FileOutputStream(selectedFile)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "Exported to Excel successfully!\nFile saved at: " + selectedFile.getAbsolutePath(),
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting to Excel!", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

