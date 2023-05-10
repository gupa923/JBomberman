package Gioco;

/**
 * questa classe gestirà il game loop e creando le istanze di GameWindow e GamePanel si occuperà anche della visualizzazione delle immagini a schermo
 *
 *
 * @autor gupa923
 */

public class Game
{
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    /**
     * nel costruttore creo le due istanze in modo che quando creo un instanza di Game vengoano creati anche il JFrame e il JPanel
     *
     *
     */
    public Game(){
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);

    }
}
