package kma.qlbh.interfaces;

import javax.swing.JFrame;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Login loginForm = new Login();
        loginForm.setVisible(true);
        loginForm.pack();
        loginForm.setLocationRelativeTo(null);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
