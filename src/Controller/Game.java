package Controller;

import Model.GameModel;
import View.GameFrame;
import View.GamePanel;

public class Game implements Runnable{
    private GameFrame frame;
    private GamePanel panel;
    //private Prova test;
    private GameModel gameModel;
    private Thread gameThread;
    private int FPS = 60;
    private int UPS = 100;

    public Game(){
        panel = new GamePanel();
        frame = new GameFrame(panel);

        gameModel = new GameModel();
        panel.addKeyListener(new KeyInputsManager(gameModel.getPlayer()));
        panel.requestFocus();
        gameModel.getPlayer().addObserver(panel.getPlayerGraphics());

        startExecution();
    }

    private void startExecution() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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
