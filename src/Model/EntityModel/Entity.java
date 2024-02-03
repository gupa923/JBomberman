package Model.EntityModel;

import java.util.Observable;

/**
 * questa classe contiene tutte le informazioni comuni a tutte le entità presenti nel gioco.
 * per chiarezza è considerata entita qualunque oggetto di gioco che può essere distrutto quindi gli ostacoli e i power up sono entità
 * le informazioni comuni ha tutte le entità sono una posizione cioè una coppia di coordinate (x,y), una altezza e una larghezza cioè h e w.
 * ogni entità inoltre avrà una hitbox.
 *
 * @see Hitbox
 * @see Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class Entity extends Observable {

    protected int x, y, w, h;
    protected Hitbox hitbox;

    /**
     * Ogni Entity viene creata a partire da 4 interi
     * @param x: ascissa punto di spawn
     * @param y: ordinata del punto di spawn
     * @param w: larghezza
     * @param h: altezza
     */
    public Entity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * inizializza la hitbox dell'Entity
     */
    public abstract void initHitbox();

    /**
     * Aggiorna i campi dell'Entity ogni volta che viene chiamato
     */
    public abstract void update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Genstisce il danno subito dalle Enity
     */
    public void hit() {

    }

    /**
     * invia una notifica all'observer
     * @param arg:argomento da mandare come notifica all'observer
     */
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
}
