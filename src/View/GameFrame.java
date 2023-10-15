package View;

import javax.swing.*;


/**
 * questa classe è la finestra del gioco
 *
 * @see GamePanel
 * @see JFrame
 * @author gupa9
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    /**
     * ha campo GamePanel
     * inanzi tutto viene impostato che quando la finestra viene chiusa termina anche l'esecuzione del programma,
     * viene in seguito aggiunto il gamePanel,
     * viene impedido all'utente di poter modificare la dimensione della finestra.
     * con la funzione pack() si fa in modo che la finestra si addatti alla dimensione del GamePanel
     * infine la finestra viene posizionata al centro dello schermo e viene resa visibile.
     *
     * @param gamePanel
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
