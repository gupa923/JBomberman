package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

public class Win extends Stato{
    private Button bNewGame, bMenuIniziale;
    public Win(GameModel gameModel) {
        super(gameModel);
        bNewGame = new Button(275, 304 + 41, 272, 64, "NEW GAME");
        bMenuIniziale = new Button(275, 531, 272, 64, "MENU INIZIALE");
        bNewGame.setStato(this);
        bMenuIniziale.setStato(this);
    }

    @Override
    public void update() {
        System.out.println(countObservers());
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
