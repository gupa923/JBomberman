package View;

import View.UtilityInterfaces.ImgImporter;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel implements ImgImporter {

    private  JTextField usernameField, avatarField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;

    public LoginPanel(){
        //setSize(272, 272);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        setLayout(new GridLayout(4, 4));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel avatarLabel = new JLabel("Avatar");
        usernameLabel.setFont(new Font("SansSerif", 0, 30));
        passwordLabel.setFont(new Font("SansSerif", 0, 30));
        avatarLabel.setFont(new Font("SansSerif", 0, 30));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        avatarField = new JTextField();
        usernameField.setFont(new Font("SansSerif", 0, 30));
        passwordField.setFont(new Font("SansSerif", 0, 30));
        avatarField.setFont(new Font("SansSerif", 0, 30));
        usernameField.setText("");
        passwordField.setText("");
        avatarField.setText("");

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        loginButton.setFont(new Font("SansSerif", 0, 30));
        registerButton.setFont(new Font("SansSerif", 0, 30));

        add(usernameLabel);
        add(new JLabel());
        add(usernameField);
        add(passwordLabel);
        add(new JLabel());
        add(passwordField);
        add(avatarLabel);
        add(new JLabel());
        add(avatarField);
        add(loginButton);
        add(new JLabel());
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
