package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

/**
 * Questa classe gestisce la rappresentazione grafica delle entità del gioco. Contiene tutti gli elementi comuni a tutte le classi che gestiscono la rappresentazione grafica dell'entità.
 * @see View.UtilityInterfaces.Animatable
 * @see View.UtilityInterfaces.Drawable
 * @see View.UtilityInterfaces.ImgImporter
 * @autor Guido Paluzzi, Matteo Santucci
 */
public abstract class EntityGraphics implements ImgImporter, Drawable, Animatable {
    protected int x, y, w, h;

    /**
     * Costruttore della class
     * @param x: coordinata x del punto di spawn
     * @param y: coordinata y del punto di spawn
     * @param w: larghezza dell'Entity
     * @param h: altezza dell'Entity
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
