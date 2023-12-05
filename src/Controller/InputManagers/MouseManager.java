package Controller.InputManagers;

import Controller.StateManager;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;
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
            case PARTITA -> {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON3 -> {
                        gameModel.getPartita().getPlayer().setAction("BOMB");
                    }
                    case MouseEvent.BUTTON1 -> {
                        Player player = gameModel.getPartita().getPlayer();
                        Hitbox hitbox = player.getHitbox();
                        if (hitbox.y /16 == (e.getY()/3)/16){
                            if (hitbox.x/16 <= (e.getX()/3)/16){
                                player.setMoving(true);
                                player.setAction("RIGHT");
                            }else{
                                player.setMoving(true);
                                player.setAction("LEFT");
                            }
                        }
                        else if (hitbox.x/16 == (e.getX()/3)/16){
                            if (hitbox.y/16 <= (e.getY()/3)/16){
                                player.setMoving(true);
                                player.setAction("DOWN");
                            }else {
                                player.setMoving(true);
                                player.setAction("UP");
                            }
                        }
                    }
                }
            }
            case MENU -> {
                if (gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY())){
                    //gameModel.getMenu().getbPlay().setMousePressed(true);
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    //gameModel.getMenu().getbSettings().setMousePressed(true);
                    gameModel.setStatoAttuale(Stati.SETTINGS);
                    stateManager.changeState(Stati.SETTINGS);
                }else if (gameModel.getMenu().getbLogin().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.LOGIN);
                    stateManager.changeState(Stati.LOGIN);
                    //gameModel.getMenu().getbLogin().setMousePressed(true);
                }else if(gameModel.getMenu().getbExit().getBounds().contains(e.getX(), e.getY())){
                    System.exit(0);
                }
            }
            case PAUSE -> {
                if (gameModel.getPause().getbResume().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getPartita().getPlayer().setMoving(false);
                    gameModel.getPartita().getPlayer().setAction("");
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }else if (gameModel.getPause().getbClose().getBounds().contains(e.getX(), e.getY())){
                    System.exit(0);
                }else if(gameModel.getPause().getbQuit().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getPartita().getPlayer().setMoving(false);
                    gameModel.getPartita().getPlayer().setAction("");
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
            case SETTINGS -> {
                if (gameModel.getSettings().getbAudio() .getBounds().contains(e.getX(), e.getY())){
                }
                else if (gameModel.getSettings().getbComandi().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.COMMAND_INFO);
                    stateManager.changeState(Stati.COMMAND_INFO);
                }else if (gameModel.getSettings().getbStartPage().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {
                if (gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY())){
                    //gameModel.getMenu().getbPlay().setMousePressed(false);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    //gameModel.getMenu().getbPlay().setMousePressed(false);
                }else if (gameModel.getMenu().getbLogin().getBounds().contains(e.getX(), e.getY())){
                   // gameModel.getMenu().getbLogin().setMousePressed(false);
                }
            }
            case PARTITA -> {
                if (e.getButton() == MouseEvent.BUTTON1){
                    gameModel.getPartita().getPlayer().setMoving(true);
                    gameModel.getPartita().getPlayer().setAction("STAY");
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
        switch (gameModel.getStatoAttuale()){
            case MENU -> {
                if (gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbPlay().setMousePressed(true);
                } else{
                    gameModel.getMenu().getbPlay().setMousePressed(false);
                }
                if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbSettings().setMousePressed(true);
                }else {
                    gameModel.getMenu().getbSettings().setMousePressed(false);
                }
                 if (gameModel.getMenu().getbLogin().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbLogin().setMousePressed(true);
                }else{
                     gameModel.getMenu().getbLogin().setMousePressed(false);
                 }
                 if(gameModel.getMenu().getbExit().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getMenu().getbExit().setMousePressed(true);
                }else{
                    gameModel.getMenu().getbExit().setMousePressed(false);
                }
            }
            case PAUSE -> {
                if (gameModel.getPause().getbResume().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getPause().getbResume().setMousePressed(true);
                } else{
                    gameModel.getPause().getbResume().setMousePressed(false);
                }
                if (gameModel.getPause().getbClose().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getPause().getbClose().setMousePressed(true);
                }else {
                    gameModel.getPause().getbClose().setMousePressed(false);
                }
                if (gameModel.getPause().getbQuit().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getPause().getbQuit().setMousePressed(true);
                }else{
                    gameModel.getPause().getbQuit().setMousePressed(false);
                }
            }
            case SETTINGS -> {
                if (gameModel.getSettings().getbAudio() .getBounds().contains(e.getX(), e.getY())){
                    gameModel.getSettings().getbAudio().setMousePressed(true);
                } else{
                    gameModel.getSettings().getbAudio().setMousePressed(false);
                }
                if (gameModel.getSettings().getbComandi().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getSettings().getbComandi().setMousePressed(true);
                }else {
                    gameModel.getSettings().getbComandi().setMousePressed(false);
                }
                if (gameModel.getSettings().getbStartPage().getBounds().contains(e.getX(), e.getY())){
                    gameModel.getSettings().getbStartPage().setMousePressed(true);
                }else{
                    gameModel.getSettings().getbStartPage().setMousePressed(false);
                }
            }
        }
    }
}
