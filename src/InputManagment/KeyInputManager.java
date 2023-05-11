package InputManagment;

import static GameInfo.Constants.Direction.*;
import Gioco.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputManager implements KeyListener {

    private GamePanel panel;

    public KeyInputManager(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
