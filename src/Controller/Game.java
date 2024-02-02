package Controller;

import Controller.EntityManagers.EnemySpawnerManager;
import Controller.EntityManagers.PlayerManager;
import Controller.InputManagers.KeyManager;
import Controller.InputManagers.MouseManager;
import Model.GameModel;
import View.AudioPlayer;
import View.GameFrame;
import View.GamePanel;

/**
 * Questa classe controlla tutta l'esecuzione del gioco, istanzia quindi tutti gli elementi del model e del controller.
 * @see Runnable
 * @author gupa9
 */
public class Game implements Runnable{
    private static Game instance;
    private final GameFrame gameFrame;
    private final GamePanel gamePanel;
    private Thread thread;
    private final MouseManager mouseManager;
    private final KeyManager keyManager;
    private final GameModel gameModel;
    private final StateManager stateManager;
    private final PlayerManager playerManager;
    private final LevelManager levelManager;
    private final EnemySpawnerManager enemySpawnerManager;
    private final AudioManager audioManager;

    private final int FPS = 60;
    private final int UPS = 120;

    /**
     * Costruttore della classe, inizializza le classi del gioco
     */
    private Game() {
        this.audioManager = new AudioManager();

        this.gameModel = GameModel.getInstance();
        this.stateManager = StateManager.getInstance();
        this.playerManager = PlayerManager.getInstance();
        this.levelManager = LevelManager.getInstance();
        enemySpawnerManager = new EnemySpawnerManager();

        this.gamePanel = new GamePanel(stateManager.getMenuGraphics());
        this.gameFrame = new GameFrame(gamePanel);
        stateManager.setGamePanel(gamePanel);
        gamePanel.setMatchGraphics(stateManager.getMatchGraphics());
        gamePanel.setMenuGraphics(stateManager.getMenuGraphics());
        gamePanel.getMatchGraphics().setPlayerGraphics(playerManager.getPlayerGraphics());
        gamePanel.getMatchGraphics().setLevelGraphics(levelManager.getLevelGraphicsArrayList());

        mouseManager = new MouseManager(gameModel, stateManager);
        keyManager = new KeyManager(gameModel, stateManager);

        gamePanel.addMouseListener(mouseManager);
        gamePanel.addMouseMotionListener(mouseManager);
        gamePanel.addKeyListener(keyManager);
        gamePanel.requestFocus();
        AudioPlayer.PlaySong();
    }

    /**
     * Con questo metodo inizia l'esecuzione vera e propria del gioco
     */
    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Questa classe è quella che contiene il game loop. Viene controllato il tempo che passa tra un aggiornamento del model e l'altro, se è passato troppo tempo il model viene aggiornato nuovamente, in modo da avere 120 aggiornamenti per secondo.
     * In modo analogo la viene fatto per la view viene ridisegnata 60 volte al secondo
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
