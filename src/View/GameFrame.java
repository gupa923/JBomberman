package View;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel panel;

    public GameFrame(GamePanel panel){
        super("JBomberman");
        this.panel = panel;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
