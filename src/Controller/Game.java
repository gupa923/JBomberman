package Controller;

import Controller.EntityManagers.EnemySpawnerManager;
import Controller.EntityManagers.PlayerManager;
import Controller.InputManagers.KeyManager;
import Controller.InputManagers.MouseManager;
import Model.GameModel;
import View.GameFrame;
import View.GamePanel;

/**
 * questa classe inizializza tutte le cose chiama i vari costruttori e da inizio al gioco
 *
 * è singleton
 * @see Runnable
 * @author gupa9
 */
public class Game implements Runnable{

    private static Game instance;
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread thread;
    private MouseManager mouseManager;
    private KeyManager keyManager;
    private GameModel gameModel;
    private StateManager stateManager;
    private PlayerManager playerManager;
    private LevelManager levelManager;
    private EnemySpawnerManager enemySpawnerManager;
    private AudioManager audioManager;

    private int FPS = 60;
    private int UPS = 120;

    private Game() {

        this.audioManager = new AudioManager();
        //creazione model
        this.gameModel = GameModel.getInstance();
        this.stateManager = new StateManager();
        this.playerManager = PlayerManager.getInstance();
        this.levelManager = LevelManager.getInstance();
        enemySpawnerManager = new EnemySpawnerManager();

        //creazione view
        this.gamePanel = new GamePanel(stateManager.getMenuGraphics());
        this.gameFrame = new GameFrame(gamePanel);

        stateManager.setGamePanel(gamePanel);

        gamePanel.setMatchGraphics(stateManager.getMatchGraphics());
        gamePanel.setMenuGraphics(stateManager.getMenuGraphics());
        //aggiungo alla view le varie cose grafiche
        gamePanel.getMatchGraphics().setPlayerGraphics(playerManager.getPlayerGraphics());
        gamePanel.getMatchGraphics().setLevelGraphics(levelManager.getLevelGraphicsArrayList());

        //inizializzazione dei listener
        mouseManager = new MouseManager(gameModel, stateManager);
        keyManager = new KeyManager(gameModel, stateManager);

        //aggiunta dei listener

        gamePanel.addMouseListener(mouseManager);
        gamePanel.addMouseMotionListener(mouseManager);
        gamePanel.addKeyListener(keyManager);
        gamePanel.requestFocus();

    }

    /**
     * iniza l'esecuzione del trhead
     *
     */
    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    /**
     * gameloop
     * il gameloop è un while che viene eseguito finchè l'esecuzione non viene interrotta.
     * il loop aggiorna il model e la view.
     * gli update per secondo sono 120 e il game loop in base a quanto tempo è passato dall'ultimo update, chiama gameModel.update().
     * lo stesso vale per i frame, impostati a 120
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
                gameModel.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
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

    public static Game getInstance(){
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
