package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * Manages the mechanics that happen when the player wins a game
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Win extends Stato{
    private final Button bNewGame;
    private final Button bMenuIniziale;

    /**
     * Class Constructor
     * @param gameModel: GameModel Instance
     */
    public Win(GameModel gameModel) {
        super(gameModel);
        bNewGame = new Button(275, 304 + 41, 272, 64, "NEW GAME");
        bMenuIniziale = new Button(275, 531, 272, 64, "MENU INIZIALE");
        bNewGame.setStato(this);
        bMenuIniziale.setStato(this);
    }

    /**
     * Update status items
     */
    @Override
    public void update() {

        bNewGame.update();
        bMenuIniziale.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bNewGame.isMousePressed() && !bMenuIniziale.isMousePressed();
    }

    public Button getbNewGame() {
        return bNewGame;
    }

    public Button getbMenuIniziale() {
        return bMenuIniziale;
    }
}
