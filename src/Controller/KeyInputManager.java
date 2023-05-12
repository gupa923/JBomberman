package Controller;

import View.GamePanel;

import static GameInfo.Constants.Direction.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * questa classe è un listener che gestisce gli input da testiera
 *
 *
 *
 */

public class KeyInputManager implements KeyListener {

    private GamePanel panel;

    public KeyInputManager(GamePanel panel) {
        this.panel = panel;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     *
     * questo metodo dice al programma cosa fare quando viene premuto un tasto della testiera
     * se il tasto premuto è uno tra quelli nello switch impostiamo la direzione del personaggio al relativo caso
     * ed impostiamo a vero il campo isMoving
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                //panel.setDy(-10);
                panel.setDirection(UP);
                panel.setMoving(true);
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                //panel.setDy(10);
                panel.setDirection(DOWN);
                panel.setMoving(true);
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                //panel.setDx(-10);
                panel.setDirection(LEFT);
                panel.setMoving(true);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                //panel.setDx(10);
                panel.setDirection(RIGHT);
                panel.setMoving(true);
                break;

        }
    }

    /**
     * questo metodo dice al programma cosa fare quando un tasto della tastiera viene rilasciato
     * quindi diremo che il personaggio non si sta più muovendo quindi isMoving sarà false
     *
     * questo metodo fa in modo di avere un movimento più fluido
     *
     * @param e the event to be processed
     */

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                panel.setMoving(false);
                break;
        }
    }
}
