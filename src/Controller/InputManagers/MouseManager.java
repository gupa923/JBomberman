package Controller.InputManagers;

import Controller.StateManager;
import Model.GameModel;
import Model.Stati;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * classico mouse listener
 *
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private GameModel gameModel;
    private StateManager stateManager;

    public MouseManager(GameModel gameModel, StateManager stateManager) {
        this.gameModel = gameModel;
        this.stateManager = stateManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {
                if (gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbPlay().setMousePressed(true);
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbSettings().setMousePressed(true);
                    gameModel.setStatoAttuale(Stati.SETTINGS);
                    stateManager.changeState(Stati.SETTINGS);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbLogin().setMousePressed(true);
                }else if(gameModel.getMenu().getbExit().getBounds().contains(e.getX(), e.getY())){
                    System.exit(0);
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {
                if (gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbPlay().setMousePressed(false);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbPlay().setMousePressed(false);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbLogin().setMousePressed(false);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
