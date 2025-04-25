import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main extends JFrame{
    private JLabel mainPage;
    private JPanel mainJPanel;

    public main(){
        setContentPane(mainJPanel);
        mainPage = new JLabel();
        setTitle("AdminStudentPage");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1366,766);
        setLocationRelativeTo(null);
        setVisible(true);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    new login().setVisible(true);
                    dispose(); // Close the current window
                } catch (InterruptedException e) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
        t.start();


    }

    public static void main(String[] args) {
        new main();
    }
}
