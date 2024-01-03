package Controller;

import Model.Accounts;
import Model.Stati;
import Model.User;
import View.GamePanel;
import View.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginManager {
    private LoginPanel loginPanel;
    private GamePanel gamePanel;
    private Accounts accounts;
    private ArrayList<String> userStrings;
    private StateManager stateManager;
    private LoginListener loginListener;
    private RegisterListener registerListener;
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

    private class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean flag = accounts.login(new String[] {loginPanel.getUsernameField().getText(), loginPanel.getPasswordField().getText()});
            System.out.println(flag);
            if (flag){
                JOptionPane.showMessageDialog(loginPanel, "Login successful!");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
                stateManager.removeLoginPanel();
                stateManager.changeState(Stati.MENU);
            }else {
                JOptionPane.showMessageDialog(loginPanel, "Invalid username or password");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
            }
        }
    }

    private class RegisterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("VAFFANCULO");
            boolean flag = accounts.register(new String[] {loginPanel.getUsernameField().getText(), loginPanel.getPasswordField().getText()});
            if (flag){
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
                JOptionPane.showMessageDialog(loginPanel, "registration successful!");
                stateManager.removeLoginPanel();
                stateManager.changeState(Stati.MENU);
            }else {
                JOptionPane.showMessageDialog(loginPanel, "username or password already taken");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
            }
        }
    }

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

    public LoginListener getLoginListener() {
        return loginListener;
    }

    public RegisterListener getRegisterListener() {
        return registerListener;
    }
}
