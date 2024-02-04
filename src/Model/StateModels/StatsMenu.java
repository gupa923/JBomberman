package Model.StateModels;


import Model.GameModel;
import Model.UI.Button;

/**
 * Questa classe gestisce le meccanice del menu delle statitiche
 * @see Model.StateModels.Stato
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class StatsMenu extends Stato {
    private final Button bBack;

    /**
     * Costruttore della classe
     * @param gameModel: L'istanza del GameModel
     */
    public StatsMenu(GameModel gameModel) {
        super(gameModel);
        bBack = new Button(20, 20,192, 45, "BACK");
        bBack.setStato(this);
    }

    /**
     * Aggiorna gli elementi dello stato
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
