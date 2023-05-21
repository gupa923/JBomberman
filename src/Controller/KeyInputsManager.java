package Controller;

import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * questa classe gestisce gli input da tastiera e fa cambiare il Player di conseguenza
 *
 *
 * @see Player
 * @see java.util.EventListener
 * @see java.awt.event.KeyListener
 * @author gupa9
 */

public class KeyInputsManager implements KeyListener {
    private Player player;

    public KeyInputsManager(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * questa classe imposta lo stato del Player a moving, e indica la direction corrispondente al tasto premuto.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D ->{
                player.setMoving(true);
                player.setDirection("RIGHT");
            }
            case KeyEvent.VK_W -> {
                player.setMoving(true);
                player.setDirection("UP");
            }
            case KeyEvent.VK_A->{
                player.setMoving(true);
                player.setDirection("LEFT");
            }
            case KeyEvent.VK_S -> {
                player.setMoving(true);
                player.setDirection("DOWN");
            }

        }
    }

    /**
     * questo metodo fa cessare il movimento quando il tasto viene rilasciato
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                player.setMoving(false);
                player.setDirection("STAY");
                break;
        }
    }
}
