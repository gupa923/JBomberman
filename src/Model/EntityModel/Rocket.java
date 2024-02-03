package Model.EntityModel;

import java.awt.geom.Rectangle2D;

/**
 * Questa classe gestisce i razzi sparati dal FinalBoss
 * @see Model.EntityModel.Entity
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class Rocket extends Entity{
    private String dir;
    private Rectangle2D.Float bounds;
    /**
     * Ogni Entity viene creata a partire da 4 interi
     *
     * @param x : ascissa punto di spawn
     * @param y : ordinata del punto di spawn
     * @param w : larghezza
     * @param h : altezza
     */
    public Rocket(int x, int y, int w, int h, String dir) {
        super(x, y, w, h);
        this.dir = dir;
        initHitbox();
    }

    /**
     * Inizializzala hitbox
     */
    @Override
    public void initHitbox() {
        bounds = new Rectangle2D.Float(x, y, 20, 20);
    }

    /**
     * Aggiorna lo stato del Razzo
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
