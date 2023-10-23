package Model.StateModels;

import Model.GameModel;

public abstract class Stato {
    private GameModel gameModel;

    public Stato(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public abstract void update();
}
