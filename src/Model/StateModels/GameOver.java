package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * Questo stato gestisce le meccaniche che avvengono dopo che il giocatore ha perso la partita
 * @see Model.StateModels.Stato
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class GameOver extends Stato{
    private final Button bRetry;
    private final Button bQuit;

    /**
     * Costruttore della classe
     * @param gameModel: l'istanza del GameModel
     */
    public GameOver(GameModel gameModel) {
        super(gameModel);
        bQuit = new Button(275, 304 + 41, 272, 64, "QUIT");
        bRetry = new Button(275, 531, 272, 64, "RETRY");
        bQuit.setStato(this);
        bRetry.setStato(this);
    }

    /**
     * Aggiorna lo stato dei suoi bottoni
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
