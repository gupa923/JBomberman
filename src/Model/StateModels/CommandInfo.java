package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * Questo stato contiene le informazioni riguardanti i comandi del gioco
 * @see Model.StateModels.Stato
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class CommandInfo extends Stato{
    private final Button bBack;

    /**
     * Costruttore della classe
     * @param gameModel: istanza del gameModel
     */
    public CommandInfo(GameModel gameModel) {
        super(gameModel);
        bBack = new Button(20, 20,192, 45, "BACK");
        bBack.setStato(this);
    }

    /**
     * Aggiorna lo stato del bottone
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
