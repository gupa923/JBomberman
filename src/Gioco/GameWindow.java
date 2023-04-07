package Gioco;

import javax.swing.*;

/**
 * questa classe gestisce la finestra del gioco
 *
 *
 * @autor gupa923
 */
public class GameWindow  extends JFrame{

    /**
     * questo costruttore crea la finistra.
     * ha come argomento un istanza di GamePanel.
     * in primo luogo chiama il costruttore della classe JFrame
     * con il secondo comando faccio in modo che quando chiudo la finestra si interrompa anche l'esecuzione del programma
     * con il metodo add aggiungo il gamePanel al JFrame
     * con il metodo setLocation imposto la posizione in cui la finestra deve apparire
     *con il metodo setResizeble impedisco che l'utente cambi le dimensioni della finestra durante l'esecuzione del programma
     *
     * con il metodo pack()faccio in modo che la dimensione del JFrame si adatti a quella dei componenti al suo interno in questo caso il gamePanel
     *
     * infine con setVisible faccio apparire la finestra
     * @param gamePanel
     */
    public GameWindow(GamePanel gamePanel){
        super("JBomberman");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setLocation(0, 0);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
