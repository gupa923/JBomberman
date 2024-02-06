package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * This class handles the mechanics that happen after the player has lost the game
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class GameOver extends Stato{
    private final Button bRetry;
    private final Button bQuit;

    /**
     * Class oonstructor
     * @param gameModel: GameModel's instance
     */
    public GameOver(GameModel gameModel) {
        super(gameModel);
        bQuit = new Button(275, 304 + 41, 272, 64, "QUIT");
        bRetry = new Button(275, 531, 272, 64, "RETRY");
        bQuit.setStato(this);
        bRetry.setStato(this);
    }

    /**
     * Update the state of the buttons
     */
    @Override
    public void update() {
        bRetry.update();
        bQuit.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bRetry.isMousePressed() && !bQuit.isMousePressed();
    }

    public Button getbQuit() {
        return bQuit;
    }

    public Button getbRetry() {
        return bRetry;
    }
}
