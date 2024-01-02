package Controller;

import Model.GameModel;
import Model.StateModels.*;
import Model.Stati;
import View.GamePanel;
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
    private CommandInfo commandInfo;
    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private PauseGraphics pauseGraphics;
    private SettingsGraphics settingsGraphics;
    private CommandInfoGraphics commandInfoGraphics;
    private GamePanel gamePanel;
    private LoginManager loginManager;
    private static StateManager instance;

    private StateManager(){
        gameModel = GameModel.getInstance();
        partita = gameModel.getPartita();
        menu = gameModel.getMenu();
        pause = gameModel.getPause();
        settings = gameModel.getSettings();
        commandInfo = gameModel.getCommandInfo();
        menuGraphics = new MenuGraphics();
        menuGraphics.setbExit(menu.getbExit());
        menuGraphics.setbPlay(menu.getbPlay());
        menuGraphics.setbSetting(menu.getbSettings());
        menuGraphics.setbLogin(menu.getbLogin());
        matchGraphics = new MatchGraphics();
        pauseGraphics = new PauseGraphics();
        settingsGraphics = new SettingsGraphics();
        commandInfoGraphics = new CommandInfoGraphics();
        partita.addObserver(matchGraphics);
        menu.addObserver(menuGraphics);
        pause.addObserver(pauseGraphics);
        settings.addObserver(settingsGraphics);
        commandInfo.addObserver(commandInfoGraphics);
        gameModel.getPartita().getWin().addObserver(matchGraphics.getWinGraphics());
        gameModel.getPartita().getGameOver().addObserver(matchGraphics.getGameOverScreen());


    }

    /**
     * cambia la view e il model il base allo stato che deve essere impostato come stato attivo.
     *
     * @param nextState: in base a valore di nextState imposta actualState che verrà disegnato nella view
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
        loginManager = new LoginManager(gamePanel);
    }

    public void removeLoginPanel() {
        gamePanel.remove(loginManager.getLoginPanel());
    }

    public static StateManager getInstance() {
        if (instance == null){
            instance = new StateManager();
        }
        return instance;
    }
}
