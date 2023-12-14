package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

public class GameOver extends Stato{
    private Button bRetry, bQuit;

    public GameOver(GameModel gameModel) {
        super(gameModel);
        bQuit = new Button(275, 304 + 41, 272, 64, "QUIT");
        bRetry = new Button(275, 531, 272, 64, "RETRY");
        bQuit.setStato(this);
        bRetry.setStato(this);
    }

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
