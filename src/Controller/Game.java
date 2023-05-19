package Controller;

import Model.GameModel;
import View.GameFrame;
import View.GamePanel;

/**
 * Questa classe crea un'instanza della classe GameModel, che gestisce i dati del gioco
 * poi crea un'oggetto GameFrame e un GamePanel, creando così la finestra.
 * La classe GamePanel disegna tutti gli oggetti che devono essere disegnati.
 * Viene creata anche una nuova istanza della Classe Threadche con il metodo start()
 * chiamerà il metodo run() ottenuto implementando l'interfaccia Runnable
 *
 * @see java.lang.Runnable
 * @author gupa9
 */
public class Game implements Runnable{
    private GameFrame frame;
    private GamePanel panel;
    //private Prova test;
    private GameModel gameModel;
    private Thread gameThread;
    private int FPS = 60;
    private int UPS = 100;

    /**
     * qui vengono inizializzati i vari campi.
     * aggiungo poi l'oggetto della classe PlayerGraphics instanziato in
     * GamePanel, come osservatore di Player.
     * aggiunga al GamePanel il KeyListener KeyInputManager
     * chiamo il metodo di JPanel requestFocus()
     * infine chiamo startExecution()
     */
    public Game(){
        panel = new GamePanel();
        frame = new GameFrame(panel);

        gameModel = new GameModel();
        panel.addKeyListener(new KeyInputsManager(gameModel.getPlayer()));
        panel.requestFocus();
        gameModel.getPlayer().addObserver(panel.getPlayerGraphics());

        startExecution();
    }

    /**
     * questo metodo crea un'instanza della classe Thread e con il metodo start() fa
     * partire l'esecuzione del programma
     *
     */
    private void startExecution() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * in questo metodo viene implementato il gameLoop.
     * tra i campi di questa classe ci sono due valori che sono il target di FPS e di UPS
     * il loop controlla quanto tempo è passata dall'ultimo update, se è uguale ad UPS viene chiamato il metodo
     * updateGame() di GameModel, lo stesso vale per gli FPS, solo che in questo caso verrà chiamato il metodo
     * repaint() del GamePanel
     */

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                gameModel.updateGame();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }
}
