package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

public class CommandInfo extends Stato{
    private final Button bBack;

    public CommandInfo(GameModel gameModel) {
        super(gameModel);
        bBack = new Button(20, 20,192, 45, "BACK");
        bBack.setStato(this);
    }

    @Override
    public void update() {
        bBack.update();
        if (!bBack.isMousePressed()){
            sendMessage("NOT PRESSED");
        }
    }

    public Button getbBack() {
        return bBack;
    }
}
