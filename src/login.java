import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame{
    private JPanel loginJPanel;
    private JLabel img;
    private JButton close;
    private JButton adminButton;
    private JButton studentButton;
    private JLabel whoU;


    public login(){
//        setContentPane(loginJPanel);
        loginJPanel = new JPanel();
        setTitle("Library Management System l2");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1366,766);
        setLocationRelativeTo(null);
        loginJPanel.setLayout(null);


        // Load and Set Background Image
        ImageIcon img1 = new ImageIcon(getClass().getResource("/Images/login.png"));
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

        whoU = new JLabel("How Would You Like to Proceed?");
//        whoU.setBounds(700,200,300,40);
        whoU.setBounds(500, 200, 400, 40); // Adjusted position for better alignment
        whoU.setFont(new Font("serif", Font.BOLD,24)); // Set font size
        whoU.setHorizontalAlignment(SwingConstants.CENTER); // Center text within the label
//        whoU.setForeground([161,226,230]); //#a1e2e6
        add(whoU);

//        Admin
        adminButton = new JButton("Admin");
        adminButton.setBounds(500, 300, 180, 40);
        add(adminButton);

        //Student
        studentButton = new JButton("Student");
        studentButton.setBounds(700, 300, 180, 40);
        add(studentButton);

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
                int yes = JOptionPane.showConfirmDialog(login.this,"Are you sure you want to close this Application","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(yes == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new admin().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new login();
    }

}
