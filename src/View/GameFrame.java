package View;

import javax.swing.*;


/**
 * Questa classe è la finestra del gioco e contiene il GamePanel
 * @see GamePanel
 * @see JFrame
 * @author Guido Paluzzi
 */
public class GameFrame extends JFrame {

    private final GamePanel gamePanel;

    /**
     * Costruttore della classe. Imposta i valori relativi alla finestra di gioco e poi aggiunge il gamePanel
     * @param gamePanel: il pannello del gioco
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
