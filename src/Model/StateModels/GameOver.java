package Model.StateModels;

import Model.GameModel;

public class GameOver extends Stato{
    private String tastoPremuto;
    public GameOver(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void update() {

    }
}
