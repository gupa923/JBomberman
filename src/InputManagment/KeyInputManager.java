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
            case KeyEvent.VK_W -> {
                panel.setDirection(UP);
                panel.setMoving(true);
            }
            case KeyEvent.VK_S -> {
                panel.setDirection(DOWN);
                panel.setMoving(true);
            }
            case KeyEvent.VK_A -> {
                panel.setDirection(LEFT);
                panel.setMoving(true);
            }
            case KeyEvent.VK_D -> {
                panel.setDirection(RIGHT);
                panel.setMoving(true);
            }
            default -> {
                panel.setDirection(0);
                panel.setMoving(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                panel.setMoving(false);
                break;
        }
    }
}
