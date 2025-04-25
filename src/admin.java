import library.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class admin extends JFrame {
    private JPanel adminP;
    private JButton close;
    private JButton logInButton;
    private JLabel userName;
    private JLabel passwordLabel;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JLabel newUserLabel;
    private JLabel img;
    private JLabel forgotPass;
    private JButton backButton;

    public admin(){
        adminP = new JPanel();
        setTitle("Library Management System l2");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1366,766);
        setLocationRelativeTo(null);
        adminP.setLayout(null);


        // Load and Set Background Image
        ImageIcon img1 = new ImageIcon(getClass().getResource("/Images/createUser.png"));
        img = new JLabel(img1);
        img.setBounds(0, 0, 1366, 766);

        // Close Button
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/Images/close icon.png"));
        close = new JButton(closeIcon);
        close.setBounds(1310, 10, 40, 40);
        close.setBorderPainted(false);
        close.setContentAreaFilled(false);
        close.setFocusPainted(false);
        close.setOpaque(false);
        add(close);

        // Create Username & Password Fields
        // Create Labels
        newUserLabel = new JLabel("New Admin");
        newUserLabel.setBounds(630, 250, 100, 30);
        newUserLabel.setFont(new Font("serif", Font.BOLD,14)); // Set font size
        newUserLabel.setForeground(Color.black);
        add(newUserLabel);

        userName = new JLabel("Username:");
        userName.setBounds(450, 300, 100, 30);
        userName.setFont(new Font("serif", Font.BOLD,16)); // Set font size
        userName.setForeground(Color.black);
        add(userName);

        txtName = new JTextField();
        txtName.setBounds(550, 300, 250, 30);
        add(txtName);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(450, 350, 100, 30);
        passwordLabel.setFont(new Font("serif", Font.BOLD,16)); // Set font size
        passwordLabel.setForeground(Color.black);
        add(passwordLabel);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(550, 350, 250, 30);
        add(txtPassword);

        // Create Login Button
        logInButton = new JButton("Login Now");
        logInButton.setBounds(600, 400, 150, 40);
        add(logInButton);

        forgotPass = new JLabel("Forgot Password");
        forgotPass.setBounds(620, 450, 150, 30);
        forgotPass.setFont(new Font("serif", Font.BOLD,14)); // Set font size
        forgotPass.setForeground(Color.black);
        add(forgotPass);

        ImageIcon backIcon = new ImageIcon(getClass().getResource("/Images/back.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(10, 10, 50, 40);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        add(backButton);



        add(img);
        setVisible(true);


        close.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int yes = JOptionPane.showConfirmDialog(admin.this,"Are you sure you want to close this Application","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(yes == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();

            }
        });

        newUserLabel.addComponentListener(new ComponentAdapter() {
        });
        newUserLabel.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new newUserAdmin();
                dispose();
            }
        });
        forgotPass.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new forgotPass();
//                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
    }

    private void handleLogin() {
        String userId = txtName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Validation: Check if fields are empty
        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User ID and Password cannot be blank!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection c = Connect.getConnection()) {
            // Check if user exists
            String checkUserQuery = "SELECT password FROM login WHERE userId = ?";
            try (PreparedStatement pst = c.prepareStatement(checkUserQuery)) {
                pst.setString(1, userId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this, "User not registered! Create a new account.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        String storedPassword = rs.getString("password");
                        if (!storedPassword.equals(password)) {
                            JOptionPane.showMessageDialog(this, "Your password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }

            // If everything is correct, login successfully
//            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new adminHomepage().setVisible(true);  // Open the main system page
            dispose();  // Close login window

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error! Try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new admin();
    }
}
