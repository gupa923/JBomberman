package Model;

import Model.Prova;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputsManager implements KeyListener {
    private Prova prova;

    public KeyInputsManager(Prova prova) {
        this.prova = prova;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D ->{
                prova.setMoving(true);
                prova.setDirection("RIGHT");
            }
            case KeyEvent.VK_W -> {
                prova.setMoving(true);
                prova.setDirection("UP");
            }
            case KeyEvent.VK_A->{
                prova.setMoving(true);
                prova.setDirection("LEFT");
            }
            case KeyEvent.VK_S -> {
                prova.setMoving(true);
                prova.setDirection("DOWN");
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
                prova.setMoving(false);
                break;
        }
    }
}
