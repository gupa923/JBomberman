package Model.StateModels;


import Model.GameModel;
import Model.UI.Button;

/**
 * This class manages the mechanics of the statistics menu
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class StatsMenu extends Stato {
    private final Button bBack;

    /**
     * Class constructor
     * @param gameModel: The GameModel instance
     */
    public StatsMenu(GameModel gameModel) {
        super(gameModel);
        bBack = new Button(20, 20,192, 45, "BACK");
        bBack.setStato(this);
    }

    /**
     * Update status items
     */
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
