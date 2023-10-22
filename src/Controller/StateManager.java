package Controller;

import Model.*;
import View.*;

public class StateManager {

    private GameModel gameModel;
    private Menu menu;
    private Partita partita;
    private Pause pause;
    private Settings settings;
    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private PauseGraphics pauseGraphics;
    private SettingsGraphics settingsGraphics;
    private GamePanel gamePanel;

    public StateManager(){
        gameModel = GameModel.getInstance();
        partita = gameModel.getPartita();
        menu = gameModel.getMenu();
        pause = gameModel.getPause();
        menuGraphics = new MenuGraphics();
        matchGraphics = new MatchGraphics();
        pauseGraphics = new PauseGraphics();
        settingsGraphics = new SettingsGraphics();
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
            case SETTINGS -> {
                gamePanel.setActiveState(settingsGraphics);
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
