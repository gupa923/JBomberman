package Controller;

import Model.*;
import View.GamePanel;
import View.MatchGraphics;
import View.MenuGraphics;
import View.PauseGraphics;

public class StateManager {

    private GameModel gameModel;
    private Menu menu;
    private Partita partita;
    private Pause pause;
    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private PauseGraphics pauseGraphics;
    private GamePanel gamePanel;

    public StateManager(){
        gameModel = GameModel.getInstance();
        partita = gameModel.getPartita();
        menu = gameModel.getMenu();
        pause = gameModel.getPause();
        menuGraphics = new MenuGraphics();
        matchGraphics = new MatchGraphics();
        pauseGraphics = new PauseGraphics();
        //matchGraphics.setActive(false);
        //menuGraphics.setActive(true);
    }

    public void changeState(Stati nextState){
        switch(nextState){
            case MENU -> {
                //matchGraphics.setActive(false);
               // menuGraphics.setActive(true);
                gamePanel.setActiveState(menuGraphics);
            }
            case PARTITA ->{
                //matchGraphics.setActive(true);
                //menuGraphics.setActive(false);
                gamePanel.setActiveState(matchGraphics);
            }
            case PAUSE -> {
                gamePanel.setActiveState(pauseGraphics);
            }
        }
    }

    public MatchGraphics getMatchGraphics() {
        return matchGraphics;
    }

    public MenuGraphics getMenuGraphics() {
        return menuGraphics;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
