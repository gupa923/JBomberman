package Controller;

import Model.KeyInputsManager;
import Model.Prova;
import View.GameFrame;
import View.GamePanel;

public class Game implements Runnable{
    private GameFrame frame;
    private GamePanel panel;
    private Prova test;
    private Thread gameThread;
    private int FPS = 120;
    private int UPS = 200;

    public Game(){
        panel = new GamePanel();
        frame = new GameFrame(panel);
        test = new Prova(0, 0, 16, 24);
        test.addObserver(panel.getProvaG());

        panel.addKeyListener(new KeyInputsManager(test));
        panel.requestFocus();

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
                test.updatePos();
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
