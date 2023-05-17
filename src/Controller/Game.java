package Controller;

import View.GameFrame;
import View.GamePanel;

/**
 * questa classe gestirà il game loop e creando le istanze di GameWindow e View.GamePanel si occuperà anche della visualizzazione delle immagini a schermo
 *
 *
 * @autor gupa923
 */

public class Game implements Runnable
{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 120;
    private final int UPS = 120;

    /**
     * nel costruttore creo le due istanze in modo che quando creo un instanza di Controller.Game vengoano creati anche il JFrame e il JPanel
     *
     *
     */
    public Game(){
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
        
        startGame();
    }

    private void startGame() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;  // si usano nanosecondi
        double timePerUpdate = 1000000000.0 / UPS;


        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true) {


            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime)/ timePerUpdate;
            deltaF += (currentTime - previousTime)/ timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1){
                gamePanel.updatePos();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamePanel.repaint();

                frames++;
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS = " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

}
