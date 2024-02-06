package Model;

import Model.StateModels.*;

/**
 * This class handles the logic of switching between game states
 * @author Guido Paluzzi, Matteo Santucci
 */
public class GameModel{
    private static GameModel instance;
    private Stati statoAttuale;
    private final Menu menu;
    private final Partita partita;
    private final Pause pause;
    private final Settings settings;
    private final CommandInfo commandInfo;
    private final StatsMenu statsMenu;
    public static User USER;


    /**
     * Class constructor. Instantiate all states of the game
     */
    private GameModel(){
        this.statoAttuale = Stati.LOGIN;
        partita = new Partita(this);
        menu = new Menu(this);
        pause = new Pause(this);
        settings = new Settings(this);
        commandInfo = new CommandInfo(this);
        statsMenu = new StatsMenu(this);
    }

    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Based on the current status, update the relevant class
     */
    public void update(){
        switch(statoAttuale){
            case MENU -> menu.update();
            case PARTITA, WIN, GAME_OVER -> partita.update();
            case PAUSE -> pause.update();
            case SETTINGS -> settings.update();
            case COMMAND_INFO -> commandInfo.update();
            case STATS -> statsMenu.update();
        }
    }


    public Partita getPartita() {
        return partita;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setStatoAttuale(Stati statoAttuale) {
        this.statoAttuale = statoAttuale;
    }

    public Stati getStatoAttuale() {
        return statoAttuale;
    }

    public Pause getPause() {
        return pause;
    }

    public Settings getSettings() {
        return settings;
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public StatsMenu getStatsMenu() {
        return statsMenu;
    }
}
