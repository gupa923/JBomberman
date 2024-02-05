package Model.EntityModel;

import java.util.Observable;

/**
 * this class contains all the information common to all entities in the game.
 * for clarity, any game object that can be destroyed is considered an entity, therefore obstacles and power ups are entities
 * the common information all entities have is a position i.e. a pair of coordinates (x,y), a height and a width i.e. h and w.
 * each entity will also have a hitbox
 *
 * @see Hitbox
 * @see Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class Entity extends Observable {

    protected int x, y, w, h;
    protected Hitbox hitbox;

    /**
     * Each Entity is created from 4 integers
     * @param x: abscissa spawn point
     * @param y: ordinate spawn point
     * @param w: length
     * @param h: height
     */
    public Entity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * initializes the Entity's hitbox
     */
    public abstract void initHitbox();

    /**
     * Updates the Entity's fields every time it is called
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
     * Manages damage taken by Enities
     */
    public void hit() {

    }

    /**
     * sends a notification to the observer
     * @param arg: topic to send as notification to the observer
     */
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
}
