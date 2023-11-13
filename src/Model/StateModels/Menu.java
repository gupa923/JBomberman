package Model.StateModels;

import Model.GameModel;
import Model.UI.Button;


public class Menu extends Stato {
    private Button bPlay, bSettings, bLogin, bExit;
    public Menu(GameModel gameModel) {
        super(gameModel);
        bPlay = new Button(272, 104,272, 64, "PLAY");
        bSettings = new Button(272, 272,272, 64, "SETTINGS");
        bLogin = new Button(272, 480, 272, 64, "LOGIN");
        bExit = new Button(272, 648, 272, 64, "EXIT");
        bPlay.setStato(this);
        bSettings.setStato(this);
        bLogin.setStato(this);
        bExit.setStato(this);
    }

    @Override
    public void update() {
        bPlay.update();
        bSettings.update();
        bLogin.update();
        bExit.update();
        if (allNotPressed()){
            sendMessage("NOT PRESSED");
        }
    }

    private boolean allNotPressed() {
        return !bPlay.isMousePressed() && !bSettings.isMousePressed() && !bExit.isMousePressed() && !bLogin.isMousePressed();
    }

    public Button getbExit() {
        return bExit;
    }

    public Button getbPlay() {
        return bPlay;
    }

    public Button getbLogin() {
        return bLogin;
    }

    public Button getbSettings() {
        return bSettings;
    }
}
