package Controller.InputManagers;

import Controller.StateManager;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;
import Model.GameModel;
import Model.Stati;
import View.AudioPlayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static View.AudioPlayer.PLAY_EFFECTS;

/**
 * This class manage mouse inputs, both keys and mouse movement.
 * @see java.awt.event.MouseListener
 * @see java.awt.event.MouseMotionListener
 * @see GameModel
 * @see StateManager
 * @author Guido Paluzzi, Matteo Santucci
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private final GameModel gameModel;
    private final StateManager stateManager;

    /**
     * Class Constructor
     * @param gameModel: Game Model instance
     * @param stateManager: StateManager instance
     */
    public MouseManager(GameModel gameModel, StateManager stateManager) {
        this.gameModel = gameModel;
        this.stateManager = stateManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Based on the current status, It controls mouse location at the moment it was pressed and performs the relevant action.
     * @param e the event to be processed
     */
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
                    if (gameModel.getPartita().isRestarted()) {
                        stateManager.getLoginManager().getAccounts().getActiveUser().setGamePlayed();
                        gameModel.getPartita().setRestarted(false);
                    }
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }else if (gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.SETTINGS);
                    stateManager.changeState(Stati.SETTINGS);
                }else if (gameModel.getMenu().getbStats().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.STATS);
                    stateManager.changeState(Stati.STATS);
                    //gameModel.getMenu().getbLogin().setMousePressed(true);
                }else if(gameModel.getMenu().getbExit().getBounds().contains(e.getX(), e.getY())){
                    stateManager.getLoginManager().saveUsers();
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
                    if(PLAY_EFFECTS == true){
                        AudioPlayer.StopSong();
                    }else {
                        AudioPlayer.PlaySong();
                    }
                    PLAY_EFFECTS = !PLAY_EFFECTS;
                }
                else if (gameModel.getSettings().getbComandi().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.COMMAND_INFO);
                    stateManager.changeState(Stati.COMMAND_INFO);
                }else if (gameModel.getSettings().getbStartPage().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
            case STATS -> {
                if(gameModel.getStatsMenu().getbBack().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
            case WIN -> {
                if (gameModel.getPartita().getWin().getbNewGame().getBounds().contains(e.getX(), e.getY())){
                    stateManager.getLoginManager().getAccounts().getActiveUser().setGamePlayed();
                    stateManager.getLoginManager().saveUsers();
                    gameModel.getPartita().setRestarted(false);
                    gameModel.getPartita().restartGame();
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }
                if (gameModel.getPartita().getWin().getbMenuIniziale().getBounds().contains(e.getX(), e.getY())){
                    stateManager.getLoginManager().saveUsers();
                    gameModel.getPartita().setRestarted(true);
                    gameModel.getPartita().restartGame();
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
            case GAME_OVER -> {
                if (gameModel.getPartita().getGameOver().getbRetry().getBounds().contains(e.getX(), e.getY())){
                    stateManager.getLoginManager().getAccounts().getActiveUser().setGamePlayed();
                    stateManager.getLoginManager().saveUsers();
                    gameModel.getPartita().restartGame();
                    gameModel.setStatoAttuale(Stati.PARTITA);
                    stateManager.changeState(Stati.PARTITA);
                }
                if (gameModel.getPartita().getGameOver().getbQuit().getBounds().contains(e.getX(), e.getY())){
                    stateManager.getLoginManager().saveUsers();
                    gameModel.getPartita().restartGame();
                    gameModel.setStatoAttuale(Stati.MENU);
                    stateManager.changeState(Stati.MENU);
                }
            }
            case COMMAND_INFO -> {
                if(gameModel.getCommandInfo().getbBack().getBounds().contains(e.getX(), e.getY())){
                    gameModel.setStatoAttuale(Stati.SETTINGS);
                    stateManager.changeState(Stati.SETTINGS);
                }
            }
        }

    }

    /**
     * It manages the events that occur when the mouse is released
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (gameModel.getStatoAttuale()){
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

    /**
     * It manages mouse movement
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (gameModel.getStatoAttuale()){
            case MENU -> {
                gameModel.getMenu().getbPlay().setMousePressed(gameModel.getMenu().getbPlay().getBounds().contains(e.getX(), e.getY()));
                gameModel.getMenu().getbSettings().setMousePressed(gameModel.getMenu().getbSettings().getBounds().contains(e.getX(), e.getY()));
                gameModel.getMenu().getbStats().setMousePressed(gameModel.getMenu().getbStats().getBounds().contains(e.getX(), e.getY()));
                gameModel.getMenu().getbExit().setMousePressed(gameModel.getMenu().getbExit().getBounds().contains(e.getX(), e.getY()));
            }
            case PAUSE -> {
                gameModel.getPause().getbResume().setMousePressed(gameModel.getPause().getbResume().getBounds().contains(e.getX(), e.getY()));
                gameModel.getPause().getbClose().setMousePressed(gameModel.getPause().getbClose().getBounds().contains(e.getX(), e.getY()));
                gameModel.getPause().getbQuit().setMousePressed(gameModel.getPause().getbQuit().getBounds().contains(e.getX(), e.getY()));
            }
            case SETTINGS -> {
                gameModel.getSettings().getbAudio().setMousePressed(gameModel.getSettings().getbAudio().getBounds().contains(e.getX(), e.getY()));
                gameModel.getSettings().getbComandi().setMousePressed(gameModel.getSettings().getbComandi().getBounds().contains(e.getX(), e.getY()));
                gameModel.getSettings().getbStartPage().setMousePressed(gameModel.getSettings().getbStartPage().getBounds().contains(e.getX(), e.getY()));
            }
            case STATS -> {
                gameModel.getStatsMenu().getbBack().setMousePressed(gameModel.getStatsMenu().getbBack().getBounds().contains(e.getX(), e.getY()));
            }
            case WIN -> {
                gameModel.getPartita().getWin().getbNewGame().setMousePressed(gameModel.getPartita().getWin().getbNewGame().getBounds().contains(e.getX(), e.getY()));
                gameModel.getPartita().getWin().getbMenuIniziale().setMousePressed(gameModel.getPartita().getWin().getbMenuIniziale().getBounds().contains(e.getX(), e.getY()));
            }
            case GAME_OVER -> {
                gameModel.getPartita().getGameOver().getbRetry().setMousePressed(gameModel.getPartita().getGameOver().getbRetry().getBounds().contains(e.getX(), e.getY()));
                gameModel.getPartita().getGameOver().getbQuit().setMousePressed(gameModel.getPartita().getGameOver().getbQuit().getBounds().contains(e.getX(), e.getY()));
            }
            case COMMAND_INFO -> {
                gameModel.getCommandInfo().getbBack().setMousePressed(gameModel.getStatsMenu().getbBack().getBounds().contains(e.getX(), e.getY()));
            }
        }
    }
}
