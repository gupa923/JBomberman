package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * Questa classe gestisce lo stato Settings cioè le impostazioni del gioco
 * @see Model.StateModels.Stato
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class Settings extends Stato{
    private final Button bComandi;
    private final Button bAudio;
    private final Button bStartPage;

    /**
     * Costruttore della classe
     * @param gameModel: L'istanza del GameModel
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
     * Aggiorna gli elementi della classe
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
