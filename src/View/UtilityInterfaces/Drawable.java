package View.UtilityInterfaces;

import java.awt.*;

/**
 * Questa interfaccia funzionale consente alle classi che la implementano di disegnare a schermo.
 * @see Graphics
 * @autor Guido Paluzzi, Matteo Santucci
 */
@FunctionalInterface
public interface Drawable {
    /**
     * Consente di disegnare elementi a schermo
     * @param g: istanza della classe Graphics
     */
    void draw(Graphics g);
}
