package Controller;

import Model.Accounts;
import Model.Stati;
import Model.User;
import View.GamePanel;
import View.LoginPanel;
import View.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages the login and so the users creation and saving in the text file.
 * @author Guido Paluzzi, Matteo Santucci
 */
public class LoginManager {
    private final LoginPanel loginPanel;
    private final GamePanel gamePanel;
    private final Accounts accounts;
    private ArrayList<String> userStrings;
    private final StateManager stateManager;
    private final LoginListener loginListener;
    private final RegisterListener registerListener;
    private UserView uv;

    /**
     * Class Constructor
     * @param gamePanel: Game JPanel
     */
    public LoginManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        loginPanel = this.gamePanel.getLoginPanel();
        loadFile();
        accounts = new Accounts(userStrings);
        stateManager = StateManager.getInstance();
        loginListener = new LoginListener();
        registerListener = new RegisterListener();

        this.loginPanel.getLoginButton().addActionListener(loginListener);
        this.loginPanel.getRegisterButton().addActionListener(registerListener);
    }

    /**
     * This class is an "action listener" that manages the login
     */
    private class LoginListener implements ActionListener{
        /**
         * Manages the login and check if the entered characters are valid and does the login
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTextValid()){
                boolean flag = accounts.login(new String[] {loginPanel.getUsernameField().getText(), loginPanel.getPasswordField().getText()});
                if (flag){
                    JOptionPane.showMessageDialog(loginPanel, "Login successful!");
                    loginPanel.getUsernameField().setText("");
                    loginPanel.getPasswordField().setText("");
                    User t = accounts.getActiveUser();
                    uv = new UserView(t.getNickname(), t.getGamePlayed(), t.getVictories(), t.getRecord(), t.getAvatarIndex());
                    t.addObserver(uv);
                    stateManager.removeLoginPanel();
                    stateManager.changeState(Stati.MENU);
                }else {
                    JOptionPane.showMessageDialog(loginPanel, "Invalid username or password");
                    loginPanel.getUsernameField().setText("");
                    loginPanel.getPasswordField().setText("");
                }
            }else {
                JOptionPane.showMessageDialog(loginPanel, "username and password must not contain spaces");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
            }

        }

    }

    /**
     * This class validates the entered text
     * @return returns "true" is the entered text is correct
     */
    private boolean isTextValid() {
        String[] tNickname = loginPanel.getUsernameField().getText().split(" ");
        String[] tPassword = loginPanel.getPasswordField().getText().split(" ");
        return tNickname.length == 1 && tPassword.length== 1;
    }

    /**
     * This class is an "action listener" che manages the registration
     */
    private class RegisterListener implements ActionListener{
        /**
         * Check if the text is valid, then makes the registration
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginPanel.getAvatarField().getText().equals("")){
                JOptionPane.showMessageDialog(loginPanel, "Insert Avatar Index");
                return;
            }
            int i = Integer.parseInt(loginPanel.getAvatarField().getText());
            if (i < 1 || i > 4){
                JOptionPane.showMessageDialog(loginPanel, "Invelid avatar index");
                return;
            }
            if (isTextValid()){
                boolean flag = accounts.register(new String[] {loginPanel.getUsernameField().getText(), loginPanel.getPasswordField().getText(), loginPanel.getAvatarField().getText()});
                if (flag){
                    loginPanel.getUsernameField().setText("");
                    loginPanel.getPasswordField().setText("");
                    JOptionPane.showMessageDialog(loginPanel, "registration successful!");
                    User t = accounts.getActiveUser();
                    uv = new UserView(t.getNickname(), t.getGamePlayed(), t.getVictories(), t.getRecord(), t.getAvatarIndex());
                    t.addObserver(uv);
                    saveUsers();
                    stateManager.removeLoginPanel();
                    stateManager.changeState(Stati.MENU);
                }else {
                    JOptionPane.showMessageDialog(loginPanel, "username or password already taken");
                    loginPanel.getUsernameField().setText("");
                    loginPanel.getPasswordField().setText("");
                    loginPanel.getAvatarField().setText("");
                }
            }else{
                JOptionPane.showMessageDialog(loginPanel, "username and password must not contain spaces");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
                loginPanel.getAvatarField().setText("");
            }

        }

    }

    /**
     * This method saves the users in a text test file called "accounts.txt".
     */
    public void saveUsers() {
        StringBuilder sb = new StringBuilder();
        for (User u : accounts.getUsers()){
            sb.append(u.toString() + "\n");
        }

        try {
            PrintWriter pw = new PrintWriter(new File("res/accounts.txt"));
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    private void loadFile(){
        userStrings = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("res/accounts.txt"));
            while (s.hasNextLine()){
                String temp = s.nextLine();
                if (temp.length() != 0) {
                    userStrings.add(temp);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public UserView getUv() {
        return uv;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public LoginListener getLoginListener() {
        return loginListener;
    }

    public RegisterListener getRegisterListener() {
        return registerListener;
    }
}
