package Controller;

import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputsManager implements KeyListener {
    private Player player;

    public KeyInputsManager(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                player.setMoving(false);
                break;
        }
    }
}
