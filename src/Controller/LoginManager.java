package Controller;

import Model.Accounts;
import Model.Stati;
import View.GamePanel;
import View.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginManager {
    private LoginPanel loginPanel;
    private GamePanel gamePanel;
    private Accounts accounts;
    private ArrayList<String> userStrings;
    private StateManager stateManager;
    public LoginManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        loginPanel = this.gamePanel.getLoginPanel();
        loadFile();
        accounts = new Accounts(userStrings);
        stateManager = StateManager.getInstance();

        this.loginPanel.getLoginButton().addActionListener(new ActionListener() {
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
        });
        this.loginPanel.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("VAFFANCULO");
                accounts.register(loginPanel.getUsernameField().getText());
            }
        });
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    private void loadFile(){
        userStrings = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("res/accounts.txt"));
            while (s.hasNextLine()){
                userStrings.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
