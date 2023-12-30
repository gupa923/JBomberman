package Controller;

import View.GamePanel;
import View.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginManager {
    private LoginPanel loginPanel;
    private GamePanel gamePanel;
    public LoginManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        loginPanel = this.gamePanel.getLoginPanel();


        this.loginPanel.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("STOCAZZO");
            }
        });
        this.loginPanel.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("VAFFANCULO");
            }
        });
    }
}
