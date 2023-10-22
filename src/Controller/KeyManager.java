package Controller;

import Model.GameModel;
import Model.Stati;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * classico keylistener
 */
public class KeyManager implements KeyListener {

    private GameModel gameModel;
    private StateManager stateManager;

    public KeyManager(GameModel gameModel, StateManager stateManager) {
        this.gameModel = gameModel;
        this.stateManager = stateManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {

            }
        }
    }

    /**
     * in base al tasto premuto imposta moving a true e imposta la direction del player
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(gameModel.getStatoAttuale()){
            case MENU -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> {
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                    }
                    case KeyEvent.VK_I -> {
                        gameModel.setStatoAttuale(Stati.SETTINGS);
                        stateManager.changeState(Stati.SETTINGS);
                    }
                }
            }
            case PARTITA -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_D -> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setDirection("RIGHT");
                    }
                    case KeyEvent.VK_A-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setDirection("LEFT");
                    }
                    case KeyEvent.VK_S-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setDirection("DOWN");
                    }
                    case KeyEvent.VK_W-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setDirection("UP");
                    }
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                    case KeyEvent.VK_P -> {
                        gameModel.setStatoAttuale(Stati.PAUSE);
                        stateManager.changeState(Stati.PAUSE);
                    }
                }
            }
            case PAUSE -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                    }
                }
            }
            case SETTINGS -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                }
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
        switch (gameModel.getStatoAttuale()){
            case MENU -> {

            }
            case PARTITA -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_W-> {
                        gameModel.getPartita().getPlayer().setMoving(false);
                        gameModel.getPartita().getPlayer().setDirection("");
                    }
                }
            }
        }

    }
}
