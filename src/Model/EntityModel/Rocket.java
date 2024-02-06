package Model.EntityModel;

import java.awt.geom.Rectangle2D;

/**
 * This class handles the rockets fired by the FinalBoss
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Rocket extends Entity{
    private String dir;
    private Rectangle2D.Float bounds;
    /**
     * Each Entity is created from 4 integers
     *
     * @param x : abscissa spawn point
     * @param y : spawn point ordinate
     * @param w : length
     * @param h : height
     */
    public Rocket(int x, int y, int w, int h, String dir) {
        super(x, y, w, h);
        this.dir = dir;
        initHitbox();
    }

    /**
     * Initialize the hitbox
     */
    @Override
    public void initHitbox() {
        bounds = new Rectangle2D.Float(x, y, 20, 20);
    }

    /**
     * Update the Rocket's status
     */
    @Override
    public void update() {
        switch (dir){
            case "LEFT" -> {
                x -= 1;
                bounds.x -= 1;
            }case "RIGHT" -> {
                x += 1;
                bounds.x += 1;
            }case "UP" -> {
                y -= 1;
                bounds.y -= 1;
            }case "DOWN" -> {
                y += 1;
                bounds.y += 1;
            }
        }
    }

    public Rectangle2D.Float getBounds() {
        return bounds;
    }
}
