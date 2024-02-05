package View;

import javax.swing.*;


/**
 * This class is the game window and contains the GamePanel
 * @see GamePanel
 * @see JFrame
 * @author Guido Paluzzi
 */
public class GameFrame extends JFrame {

    private final GamePanel gamePanel;

    /**
     * Class constructor. Sets the values ​​for the game window and then adds the gamePanel
     * @param gamePanel: the game panel
     */
    public GameFrame(GamePanel gamePanel){
        super("JBomberman");
        this.gamePanel = gamePanel;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamePanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
