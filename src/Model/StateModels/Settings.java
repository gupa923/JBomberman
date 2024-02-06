package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * This class manages the Settings state i.e. the game settings
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Settings extends Stato{
    private final Button bComandi;
    private final Button bAudio;
    private final Button bStartPage;

    /**
     * Class constructor
     * @param gameModel: GameModel Instance
     */
    public Settings(GameModel gameModel) {
        super(gameModel);
        bComandi = new Button(272, 208, 272, 64, "COMANDI");
        bAudio = new Button(272, 376, 272, 64,"AUDIO" );
        bStartPage = new Button(272, 544, 272, 64, "STARTPAGE");
        bComandi.setStato(this);
        bAudio.setStato(this);
        bStartPage.setStato(this);
    }

    /**
     * Update class elements
     */
    @Override
    public void update() {
        bComandi.update();
        bAudio.update();
        bStartPage.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bComandi.isMousePressed() && !bAudio.isMousePressed() && !bStartPage.isMousePressed();
    }

    public Button getbComandi() {
        return bComandi;
    }

    public Button getbAudio() {
        return bAudio;
    }

    public Button getbStartPage() {
        return bStartPage;
    }
}
