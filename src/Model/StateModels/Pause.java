package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * questa classe gestisce la logica dello stato di pausa: la partita viene interrotta manenendo la situazione corrente
 *
 */
public class Pause extends Stato{
    private Button bResume, bQuit, bClose;
    public Pause(GameModel gameModel) {
        super(gameModel);
        bResume = new Button(272, 208, 272, 64, "RESUME");
        bQuit = new Button(272, 376, 272, 64,"QUIT" );
        bClose = new Button(272, 544, 272, 64, "CLOSE");
        bResume.setStato(this);
        bQuit.setStato(this);
        bClose.setStato(this);
    }

    @Override
    public void update() {
        bResume.update();
        bQuit.update();
        bClose.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bQuit.isMousePressed() && !bClose.isMousePressed() && !bResume.isMousePressed();
    }

    public Button getbClose() {
        return bClose;
    }

    public Button getbQuit() {
        return bQuit;
    }

    public Button getbResume() {
        return bResume;
    }
}
