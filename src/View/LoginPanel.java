package View;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPanel(){
        //setSize(272, 272);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameLabel.setFont(new Font("SansSerif", 0, 20));
        passwordLabel.setFont(new Font("SansSerif", 0, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setFont(new Font("SansSerif", 0, 20));
        passwordField.setFont(new Font("SansSerif", 0, 20));

         loginButton = new JButton("Login");
         registerButton = new JButton("Register");
        loginButton.setFont(new Font("SansSerif", 0, 20));
        registerButton.setFont(new Font("SansSerif", 0, 20));

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
