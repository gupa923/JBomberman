package View;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButon, registerButton;

    public LoginPanel(){
        Font f = new Font(Font.SANS_SERIF, 0, 20);
        setPreferredSize(new Dimension(300, 150));
        setFocusable(true);
        requestFocusInWindow();
        setVisible(false);

        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(f);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(f);

        usernameField = new JTextField();
        usernameField.setFont(f);
        passwordField = new JPasswordField();
        passwordField.setFont(f);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(f);
        JButton registerButton = new JButton("Register");
        registerButton.setFont(f);

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

    public JButton getLoginButon() {
        return loginButon;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
