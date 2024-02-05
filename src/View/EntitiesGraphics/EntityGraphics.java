package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

/**
 * This class manages the graphical representation of game entities. Contains all the elements common to all the classes that manage the graphical representation of the entity.
 * @see View.UtilityInterfaces.Animatable
 * @see View.UtilityInterfaces.Drawable
 * @see View.UtilityInterfaces.ImgImporter
 * @autor Guido Paluzzi, Matteo Santucci
 */
public abstract class EntityGraphics implements ImgImporter, Drawable, Animatable {
    protected int x, y, w, h;

    /**
     * Class builder
     * @param x: x coordinate of the spawn point
     * @param y: y coordinate of the spawn point
     * @param w: width of the Entity
     * @param h: height of the Entity
     */
    public EntityGraphics(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

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
}
