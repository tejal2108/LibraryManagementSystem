import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forgotPass extends JFrame {
    private JPanel forgotPass;
    private JButton close;
    private JLabel img;
    private JButton backButton;

    public forgotPass(){
        forgotPass = new JPanel();
        setTitle("admin/adminHomePage/ForgotPass");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1366,766);
        setLocationRelativeTo(null);
        forgotPass.setLayout(null);


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
                int yes = JOptionPane.showConfirmDialog(forgotPass.this,"Are you sure you want to close this Application","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(yes == JOptionPane.YES_OPTION)
                    System.exit(0);
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
                new admin();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new forgotPass();
    }

}
