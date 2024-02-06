package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;

/**
 * This class handles the machanics of the pause state
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Pause extends Stato{
    private final Button bResume;
    private final Button bQuit;
    private final Button bClose;

    /**
     * Class oonstructor
     * @param gameModel: GameModel's instance
     */
    public Pause(GameModel gameModel) {
        super(gameModel);
        bResume = new Button(272, 208, 272, 64, "RESUME");
        bQuit = new Button(272, 376, 272, 64,"QUIT" );
        bClose = new Button(272, 544, 272, 64, "CLOSE");
        bResume.setStato(this);
        bQuit.setStato(this);
        bClose.setStato(this);
    }

    /**
     * Update the elements of this class
     */
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
