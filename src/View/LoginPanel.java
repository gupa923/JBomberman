package View;

import View.UtilityInterfaces.ImgImporter;

import javax.swing.*;
import java.awt.*;

/**
 * This class manages the graphical representation of the login screen
 * @see javax.swing.JPanel
 * @see View.UtilityInterfaces.ImgImporter
 * @author Guido Paluzzi, Matteo Santucci
 */
public class LoginPanel extends JPanel implements ImgImporter {

    private  JTextField usernameField, avatarField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    private ImageIcon avatar1, avatar2, avatar3, avatar4;

    /**
     * Class constructor
     */
    public LoginPanel(){
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

        setLayout(new GridLayout(7, 4));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel avatarLabel = new JLabel("Avatar");
        usernameLabel.setFont(new Font("SansSerif", 0, 30));
        passwordLabel.setFont(new Font("SansSerif", 0, 30));
        avatarLabel.setFont(new Font("SansSerif", 0, 30));
        loadAvatars();
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

        JLabel avatar1Label = new JLabel(":1",avatar1, SwingConstants.CENTER);
        avatar1Label.setFont(new Font("SansSerif", 0, 20));
        JLabel avatar2Label = new JLabel(":2",avatar2, SwingConstants.CENTER);
        avatar2Label.setFont(new Font("SansSerif", 0, 20));
        JLabel avatar3Label = new JLabel(":3",avatar3, SwingConstants.CENTER);
        avatar3Label.setFont(new Font("SansSerif", 0, 20));
        JLabel avatar4Label = new JLabel(":4",avatar4, SwingConstants.CENTER);
        avatar4Label.setFont(new Font("SansSerif", 0, 20));

        add(usernameLabel);
        add(new JLabel());
        add(new JLabel());
        add(usernameField);
        add(passwordLabel);
        add(new JLabel());
        add(new JLabel());
        add(passwordField);
        add(avatarLabel);
        add(new JLabel());
        add(new JLabel());
        add(avatarField);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(avatar1Label);
        add(avatar2Label);
        add(avatar3Label);
        add(avatar4Label);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(loginButton);
        add(new JLabel());
        add(new JLabel());
        add(registerButton);
    }

    private void loadAvatars() {
        avatar1 = new ImageIcon(loadImg("/Imgs/Avatars/Avatar_1.png"));
        avatar2 = new ImageIcon(loadImg("/Imgs/Avatars/Avatar_2.png"));
        avatar3 = new ImageIcon(loadImg("/Imgs/Avatars/Avatar_3.png"));
        avatar4 = new ImageIcon(loadImg("/Imgs/Avatars/Avatar_4.png"));
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

    public JTextField getAvatarField() {
        return avatarField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

}
