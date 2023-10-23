package Model.StateModels;

import Model.GameModel;

/**
 * questa classe gestisce la logica dello stato di pausa: la partita viene interrotta manenendo la situazione corrente
 *
 */
public class Pause extends Stato{
    public Pause(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void update() {

    }
}
