package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the ClownBoss
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class ClownBossGraphics extends EnemyGraphics{
    private BufferedImage temp;
    private BufferedImage[] sprites;
    private boolean moving = true;
    private int animationIndex;
    private int animationIndexUpdate;
    private final int animationSpeed = 15;

    /**
     * Class Constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the ClownBossGraphics
     * @param h: the height of the ClownBossGraphics
     */
    public ClownBossGraphics(int x, int y, int w, int h) {
        super(x-47, y-44, w, h);
        sx = x-47;
        sy = y-44;
        loadAnimations();
    }

    /**
     * Loads all images involved in the animations of this class
     */
    @Override
    public void loadAnimations() {
        temp = loadImg("/Imgs/entitySprites/enemySprite/Boos1.png");
        sprites = new BufferedImage[10];
        sprites[0] = temp.getSubimage(220 + 2, 4, 110, 100);
        sprites[1] = temp.getSubimage(330+3, 0, 110, 100);
        sprites[2] = temp.getSubimage(110 + 1, 113, 110, 105);
        sprites[3] = temp.getSubimage(220 + 2, 104, 110, 105);
        sprites[4] = temp.getSubimage(330 + 3, 100, 110, 105);
        //sprites[5] = temp.getSubimage( 0, 216, 110, 105);
        sprites[5] = temp.getSubimage(330 + 3, 100, 110, 105);
        sprites[6] = temp.getSubimage(220 + 2, 104, 110, 105);
        sprites[7] = temp.getSubimage(110 + 1, 113, 110, 105);
        sprites[8] = temp.getSubimage(330+3, 0, 110, 100);
        sprites[9] = temp.getSubimage(220 + 2, 4, 110, 100);


        deathAnimation = new BufferedImage[2];
        deathAnimation[0] = temp.getSubimage(0, 117, 110,100);
        deathAnimation[1] = temp.getSubimage( 111, 216, 110, 105);

    }

    /**
     * Update the state of the animations
     */
    @Override
    public void updateAnimation() {
        if (death) {
            deathTick++;
            if (deathTick > 30) {
                deathIndex++;
                deathTick = 0;
                if (deathIndex >= 2) {
                    deathIndex = 0;
                }
            }
        }else {
            if (!moving) {
                animationIndex = 0;
                animationIndexUpdate = 0;
            }
            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex++;
                if (animationIndex >= sprites.length)
                    animationIndex = 0;
            }
        }
    }

    /**
     * Draws an instance of the ClownBossGraphics class
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (!death) {
            g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        }else{
            g.drawImage(deathAnimation[deathIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
    }

    /**
     * Draw an instance of this class without updating the animations
     * @param g: instance of the Graphics class
     */
    public void freeze(Graphics g) {
        g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Updates the state of this class based on notifications received from Observables
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String dir){
            switch (dir){
                case "LEFT" -> {
                    moving = true;
                    x-= 1;

                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;

                }
                case "UP" -> {
                    moving = true;
                    y -= 1;

                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;

                }
                case "STAY" -> {
                    moving = false;
                }
                case "DYING" -> {
                    audioPlayer.playEffects(1);
                    death = true;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClownBossGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }

}
