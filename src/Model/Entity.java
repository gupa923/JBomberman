package Model;

import java.util.Observable;

/**
 * questa classe contiene tutte le informazioni comuni a tutte le entità presenti nel gioco.
 * per chiarezza è considerata entita qualunque oggetto di gioco che può essere distrutto quindi gli ostacoli e i power up sono entità
 * le informazioni comuni ha tutte le entità sono una posizione cioè una coppia di coordinate (x,y), una altezza e una larghezza cioè h e w.
 * ogni entità inoltre avrà una hitbox.
 *
 * @see Hitbox
 * @see Observable
 * @author gupa9
 */
public abstract class Entity extends Observable {

    protected int x, y, w, h;
    protected Hitbox hitbox;

    public Entity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void initHitbox();
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
}
