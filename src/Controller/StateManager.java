package Controller;

import Model.GameModel;
import Model.StateModels.*;
import Model.Stati;
import View.GamePanel;
import View.StatesGraphics.*;

/**
 * Questa classe crea il model e la view di tutti gli stati del gioco e gestisce il passaggio tra uno stato e un altro
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class StateManager {

    private final GameModel gameModel;
    private final Menu menu;
    private final Partita partita;
    private final Pause pause;
    private final Settings settings;
    private final CommandInfo commandInfo;
    private final StatsMenu statsMenu;
    private final MatchGraphics matchGraphics;
    private final MenuGraphics menuGraphics;
    private final PauseGraphics pauseGraphics;
    private final SettingsGraphics settingsGraphics;
    private final CommandInfoGraphics commandInfoGraphics;
    private GamePanel gamePanel;
    private LoginManager loginManager;
    private final StatsMenuGraphics statsMenuGraphics;
    private static StateManager instance;

    /**
     * Costruttore della classe
     */
    private StateManager(){
        gameModel = GameModel.getInstance();
        partita = gameModel.getPartita();
        menu = gameModel.getMenu();
        pause = gameModel.getPause();
        settings = gameModel.getSettings();
        commandInfo = gameModel.getCommandInfo();
        statsMenu = gameModel.getStatsMenu();
        menuGraphics = new MenuGraphics();
        matchGraphics = new MatchGraphics();
        pauseGraphics = new PauseGraphics();
        settingsGraphics = new SettingsGraphics();
        commandInfoGraphics = new CommandInfoGraphics();
        statsMenuGraphics = new StatsMenuGraphics();
        partita.addObserver(matchGraphics);
        menu.addObserver(menuGraphics);
        pause.addObserver(pauseGraphics);
        settings.addObserver(settingsGraphics);
        commandInfo.addObserver(commandInfoGraphics);
        statsMenu.addObserver(statsMenuGraphics);
        gameModel.getPartita().getWin().addObserver(matchGraphics.getWinGraphics());
        gameModel.getPartita().getGameOver().addObserver(matchGraphics.getGameOverScreen());


    }

    /**
     * Cambia lo stato della view in base a come è cambiato lo stato del model
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
            case STATS -> {
                gamePanel.setActiveState(statsMenuGraphics);
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

    /**
     * Questo metodo rimuove il LoginPanel dal GamePanel.
     */
    public void removeLoginPanel() {
        gameModel.setStatoAttuale(Stati.MENU);
        gamePanel.remove(loginManager.getLoginPanel());
        loginManager.getLoginPanel().getLoginButton().removeActionListener(loginManager.getLoginListener());
        loginManager.getLoginPanel().getRegisterButton().removeActionListener(loginManager.getRegisterListener());
        statsMenuGraphics.setUv(loginManager.getUv());
        menuGraphics.setUv(loginManager.getUv());
        settingsGraphics.setUv(loginManager.getUv());
    }

    public static StateManager getInstance() {
        if (instance == null){
            instance = new StateManager();
        }
        return instance;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }
}
