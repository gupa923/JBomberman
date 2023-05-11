package Gioco;

/**
 * questa classe gestirà il game loop e creando le istanze di GameWindow e GamePanel si occuperà anche della visualizzazione delle immagini a schermo
 *
 *
 * @autor gupa923
 */

public class Game implements Runnable
{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread thread;
    private int FPS = 120;

    /**
     * nel costruttore creo le due istanze in modo che quando creo un instanza di Game vengoano creati anche il JFrame e il JPanel
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
        thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }

}
