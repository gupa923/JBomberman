package Controller;

import Model.*;
import View.GamePanel;
import View.MatchGraphics;
import View.MenuGraphics;

public class StateManager {

    private GameModel gameModel;
    private Menu menu;
    private Partita partita;
    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private GamePanel gamePanel;

    public StateManager(){
        gameModel = GameModel.getInstance();
        partita = gameModel.getPartita();
        menu = gameModel.getMenu();
        menuGraphics = new MenuGraphics();
        matchGraphics = new MatchGraphics();
        matchGraphics.setActive(false);
        menuGraphics.setActive(true);
    }

    public void changeState(Stati nextState){
        switch(nextState){
            case MENU -> {
                matchGraphics.setActive(false);
                menuGraphics.setActive(true);
            }
            case PARTITA ->{
                matchGraphics.setActive(true);
                menuGraphics.setActive(false);
            }
        }
    }

    public MatchGraphics getMatchGraphics() {
        return matchGraphics;
    }

    public MenuGraphics getMenuGraphics() {
        return menuGraphics;
    }
}
