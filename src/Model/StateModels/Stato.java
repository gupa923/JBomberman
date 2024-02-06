package Model.StateModels;

import Model.GameModel;

import java.util.Observable;

/**
 *This class contains all the characteristics common to all game states
 * @see java.util.Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class Stato extends Observable {
    protected GameModel gameModel;

    /**
     * Class constructor
     * @param gameModel: GameModel instance
     */
    public Stato(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Update status items
     */
    public abstract void update();

    /**
     * Notify Observers
     * @param arg: the arguments to send to the observer
     */
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
}
