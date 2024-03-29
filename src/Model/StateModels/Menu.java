package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * This class handles the mechanics of the start menu
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Menu extends Stato {
    private final Button bPlay;
    private final Button bSettings;
    private final Button bStats;
    private final Button bExit;

    /**
     * Class constructor
     * @param gameModel: l'istanza del GameModel
     */
    public Menu(GameModel gameModel) {
        super(gameModel);
        bPlay = new Button(272, 104,272, 64, "PLAY");
        bSettings = new Button(272, 272,272, 64, "SETTINGS");
        bStats = new Button(272, 480, 272, 64, "STATS");
        bExit = new Button(272, 648, 272, 64, "EXIT");
        bPlay.setStato(this);
        bSettings.setStato(this);
        bStats.setStato(this);
        bExit.setStato(this);
    }

    /**
     * Update the elements of the class, especially the buttons inside it
     */
    @Override
    public void update() {
        bPlay.update();
        bSettings.update();
        bStats.update();
        bExit.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bPlay.isMousePressed() && !bSettings.isMousePressed() && !bExit.isMousePressed() && !bStats.isMousePressed();
    }

    public Button getbExit() {
        return bExit;
    }

    public Button getbPlay() {
        return bPlay;
    }

    public Button getbStats() {
        return bStats;
    }

    public Button getbSettings() {
        return bSettings;
    }
}
