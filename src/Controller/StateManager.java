package Controller;

import Model.*;
import Model.StateModels.Menu;
import Model.StateModels.Partita;
import Model.StateModels.Pause;
import Model.StateModels.Settings;
import View.*;
import View.StatesGraphics.*;

/**
 * Questa classe gestisce il passaggio tra uno stato all'altro fancendo cambiare sia il model che la view
 *
 *
 *
 */
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
    private CommandInfoGraphics commandInfoGraphics;
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
        commandInfoGraphics = new CommandInfoGraphics();
    }

    /**
     * cambia la view e il model il base allo stato che deve essere impostato come stato attivo.
     *
     * @param nextState
     */
    public void changeState(Stati nextState){
        switch(nextState){
            case MENU -> {
                gamePanel.setActiveState(menuGraphics);
            }
            case PARTITA ->{
                gamePanel.setActiveState(matchGraphics);
            }
            case PAUSE -> {
                gamePanel.setActiveState(pauseGraphics);
            }
            case SETTINGS -> {
                gamePanel.setActiveState(settingsGraphics);
            }
            case COMMAND_INFO -> {
                gamePanel.setActiveState(commandInfoGraphics);
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
