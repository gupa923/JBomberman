package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * This class contains information regarding the game commands
 * Questo stato contiene le informazioni riguardanti i comandi del gioco
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class CommandInfo extends Stato{
    private final Button bBack;

    /**
     * Class oonstructor
     * @param gameModel: GameModel's instance
     */
    public CommandInfo(GameModel gameModel) {
        super(gameModel);
        bBack = new Button(20, 20,192, 45, "BACK");
        bBack.setStato(this);
    }

    /**
     * Update button's states
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
