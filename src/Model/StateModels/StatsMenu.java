package Model.StateModels;


import Model.GameModel;
import Model.UI.Button;

public class StatsMenu extends Stato {
    private Button bBack;

    public StatsMenu(GameModel gameModel) {
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
