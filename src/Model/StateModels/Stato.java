package Model.StateModels;

import Model.GameModel;

import java.util.Observable;

/**
 * Questa classe racchiude tutte le caratteristiche comunui a tutti gli stati della partita
 * @see java.util.Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class Stato extends Observable {
    protected GameModel gameModel;

    /**
     * Costruttore della classe
     * @param gameModel: istanza del GameModel
     */
    public Stato(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Aggiorna gli elementi dello stato
     */
    public abstract void update();

    /**
     * Notifica gli Observer
     * @param arg: gli argomenti da inviare all'observer
     */
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
}
