package Controller;

import Model.GameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * classico keylistener
 */
public class KeyManager implements KeyListener {

    private GameModel gameModel;
    public KeyManager(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * in base al tasto premuto imposta moving a true e imposta la direction del player
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D -> {
                gameModel.getPlayer().setMoving(true);
                gameModel.getPlayer().setDirection("RIGHT");
            }
            case KeyEvent.VK_A-> {
                gameModel.getPlayer().setMoving(true);
                gameModel.getPlayer().setDirection("LEFT");
            }
            case KeyEvent.VK_S-> {
                gameModel.getPlayer().setMoving(true);
                gameModel.getPlayer().setDirection("DOWN");
            }
            case KeyEvent.VK_W-> {
                gameModel.getPlayer().setMoving(true);
                gameModel.getPlayer().setDirection("UP");
            }
        }
    }

    /**
     * in base al tasto rilasciato moving viene impostato a false e la direction viene impostata a STAY;
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_W-> {
                gameModel.getPlayer().setMoving(false);
                gameModel.getPlayer().setDirection("");
            }
        }
    }
}
