package Controller.InputManagers;

import Controller.StateManager;
import Model.GameModel;
import Model.Stati;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Questa classe gestisce gli input da tastiera
 * @see java.awt.event.KeyListener
 * @see GameModel
 * @see StateManager
 * @author Guido Paluzzi, Matteo Santucci
 */
public class KeyManager implements KeyListener {

    private final GameModel gameModel;
    private final StateManager stateManager;

    /**
     * Class constructor
     * @param gameModel: Game Model instance
     * @param stateManager: StateManager instance
     */
    public KeyManager(GameModel gameModel, StateManager stateManager) {
        this.gameModel = gameModel;
        this.stateManager = stateManager;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * The respective command is executed based on the current status and the pressed key
     * @param e: the event to be processed
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
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setAction("RIGHT");
                    }
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setAction("LEFT");
                    }
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setAction("DOWN");
                    }
                    case KeyEvent.VK_W, KeyEvent.VK_UP-> {
                        gameModel.getPartita().getPlayer().setMoving(true);
                        gameModel.getPartita().getPlayer().setAction("UP");
                    }
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                    case KeyEvent.VK_P -> {
                        gameModel.setStatoAttuale(Stati.PAUSE);
                        stateManager.changeState(Stati.PAUSE);
                    }
                    case KeyEvent.VK_ENTER -> {
                        gameModel.getPartita().getPlayer().setAction("BOMB");
                    }
                    case KeyEvent.VK_N -> {
                        gameModel.getPartita().setCheat(true);
                    }
                }
            }
            case PAUSE -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.getPartita().getPlayer().setMoving(false);
                        gameModel.getPartita().getPlayer().setAction("");
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                    }
                    case KeyEvent.VK_R -> {
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                        gameModel.getPartita().restartGame();
                    }
                }
            }
            case SETTINGS -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                    case KeyEvent.VK_C -> {
                        gameModel.setStatoAttuale(Stati.COMMAND_INFO);
                        stateManager.changeState(Stati.COMMAND_INFO);
                    }
                }
            }
            case COMMAND_INFO -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.setStatoAttuale(Stati.SETTINGS);
                        stateManager.changeState(Stati.SETTINGS);
                    }
                }
            }
            case GAME_OVER -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_BACK_SPACE -> {
                        gameModel.getPartita().restartGame();
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                    }
                }
            }
            case WIN -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_R -> {
                        gameModel.getPartita().restartGame();
                        gameModel.setStatoAttuale(Stati.PARTITA);
                        stateManager.changeState(Stati.PARTITA);
                    }
                    case KeyEvent.VK_M -> {
                        gameModel.getPartita().restartGame();
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                }
            }
            case LOGIN -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_M -> {
                        stateManager.removeLoginPanel();
                        gameModel.setStatoAttuale(Stati.MENU);
                        stateManager.changeState(Stati.MENU);
                    }
                }
            }
        }

    }

    /**
     * The commands are executed based on the released key
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {

            }
            case PARTITA -> {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_W, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT-> {
                        gameModel.getPartita().getPlayer().setMoving(false);
                        gameModel.getPartita().getPlayer().setAction("");
                    }
                }
            }
        }

    }
}
