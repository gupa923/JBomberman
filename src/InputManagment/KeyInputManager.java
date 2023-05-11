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
                panel.setDy(-10);
                //panel.setDirection(UP);
                //panel.setMoving(true);
                break;

            case KeyEvent.VK_S:
                panel.setDy(10);
                //panel.setDirection(DOWN);
                //panel.setMoving(true);
                break;

            case KeyEvent.VK_A:
                panel.setDx(-10);
                //panel.setDirection(LEFT);
               // panel.setMoving(true);
                break;

            case KeyEvent.VK_D:
                panel.setDx(10);
               // panel.setDirection(RIGHT);
               // panel.setMoving(true);
                break;

            default:
               // panel.setDirection(0);
              //  panel.setMoving(false);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //switch (e.getKeyCode()){
          //  case KeyEvent.VK_W:
            //case KeyEvent.VK_S:
            //case KeyEvent.VK_A:
            //case KeyEvent.VK_D:
              //  panel.setMoving(false);
                //break;
       // }
    }
}
