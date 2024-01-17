package View;

import View.UtilityInterfaces.ImgImporter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginPanel extends JPanel implements ImgImporter {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;

    public LoginPanel(){
        //setSize(272, 272);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameLabel.setFont(new Font("SansSerif", 0, 30));
        passwordLabel.setFont(new Font("SansSerif", 0, 30));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setFont(new Font("SansSerif", 0, 30));
        passwordField.setFont(new Font("SansSerif", 0, 30));
        usernameField.setText("");
        passwordField.setText("");

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        loginButton.setFont(new Font("SansSerif", 0, 30));
        registerButton.setFont(new Font("SansSerif", 0, 30));

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

    public JTextField getUsernameField() {
        return usernameField;
    }




    public JPasswordField getPasswordField() {
        return passwordField;
    }

}
