package View.UtilityInterfaces;

import java.awt.*;

/**
 * This functional interface allows the classes that implement it to draw on the screen.
 * @see Graphics
 * @autor Guido Paluzzi, Matteo Santucci
 */
@FunctionalInterface
public interface Drawable {
    /**
     * Allows you to draw elements on the screen
     * @param g: instance of the Graphics class
     */
    void draw(Graphics g);
}
