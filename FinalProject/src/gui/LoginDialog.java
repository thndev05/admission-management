package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import dao.AdminDAO;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginDialog extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel hidePasswordLabel;
    private JLabel viewPasswordLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FlatLightLaf.setup();
                	
                    LoginDialog frame = new LoginDialog();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setTitle("Login");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 75, 892, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setBounds(401, 90, 94, 41);
        contentPane.add(titleLabel);
        titleLabel.setForeground(new Color(252, 210, 84));
        titleLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 30));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(0, 0, 0));
        usernameLabel.setBackground(new Color(255, 255, 128));
        usernameLabel.setBounds(212, 170, 84, 24);
        contentPane.add(usernameLabel);
        usernameLabel.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));

        usernameTextField = new JTextField();
        usernameTextField.setBackground(new Color(255, 255, 255));
        usernameTextField.setBounds(303, 167, 307, 32);
        contentPane.add(usernameTextField);
        usernameTextField.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        usernameTextField.setMargin(new Insets(0, 12, 0, 12));
        usernameTextField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBounds(303, 246, 276, 32);
        contentPane.add(passwordField);
        passwordField.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        passwordField.setMargin(new Insets(0, 12, 0, 12));
        
        //event
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = usernameTextField.getText();
                    String password = new String(passwordField.getPassword());

                    if (AdminDAO.checkLogin(username, password)) {
                        openAdmissionManagementWindow();
                    } else {
                        showAlert("Login Failed!", "Invalid username or password! \nPlease try again.");
                    }
                }
            }
        });

        passwordField.setEchoChar('*');

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(0, 0, 0));
        passwordLabel.setBounds(212, 249, 84, 24);
        contentPane.add(passwordLabel);
        passwordLabel.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(303, 322, 307, 32);
        contentPane.add(loginButton);
        loginButton.setBackground(new Color(153, 255, 255));
        loginButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
//        loginButton.setOpaque(true);
        loginButton.setFocusable(false);
        

        hidePasswordLabel = new JLabel();
        hidePasswordLabel.setBounds(586, 250, 24, 24);
        contentPane.add(hidePasswordLabel);
        hidePasswordLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\icons8_hide.png"));
        hidePasswordLabel.setVisible(true);
        
        viewPasswordLabel = new JLabel();
        viewPasswordLabel.setBounds(586, 250, 24, 24);
        contentPane.add(viewPasswordLabel);
        viewPasswordLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\icons8_show.png"));
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 878, 500);
        contentPane.add(backgroundLabel);
        backgroundLabel.setIcon(new ImageIcon("C:\\Users\\ACER\\Pictures\\FinalProject\\bg.jpg"));
        viewPasswordLabel.setVisible(false);
        
        
        
        
        hidePasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
                hidePasswordLabel.setVisible(false);
                viewPasswordLabel.setVisible(true);
            }
        });
        
        viewPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setEchoChar('*');
                hidePasswordLabel.setVisible(true);
                viewPasswordLabel.setVisible(false);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                if (AdminDAO.checkLogin(username, password)) {
                    openAdmissionManagementWindow();
                } else {
                    showAlert("Login Failed!", "Invalid username or password! \nPlease try again.");
                }
            }
        });

        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String username = usernameTextField.getText();
                    String password = new String(passwordField.getPassword());

                    if (AdminDAO.checkLogin(username, password)) {
                        openAdmissionManagementWindow();
                    } else {
                        showAlert("Login Failed!", "Invalid username or password! \nPlease try again.");
                    }
                }
            }
        });
    }

    private void openAdmissionManagementWindow() {
        AdmissionManagement admissionManagement = new AdmissionManagement();
        admissionManagement.setTitle("Admission Management");;
        admissionManagement.setVisible(true);
        dispose();
    }

    private void showAlert(String title, String content) {
        JOptionPane.showMessageDialog(this, content, title, JOptionPane.ERROR_MESSAGE);
    }
}
