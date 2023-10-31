package Model.StateModels;

import Model.GameModel;

import java.util.Observable;

public abstract class Stato extends Observable {
    private GameModel gameModel;

    public Stato(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public abstract void update();
}
