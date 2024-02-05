package View.EntitiesGraphics.EnemyGraphics;

import Model.EntityModel.Hitbox;
import View.AudioPlayer;
import View.EntitiesGraphics.EntityGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observer;

/**
 * This class manages the graphical representation of enemies
 * @see View.EntitiesGraphics.EntityGraphics
 * @see java.util.Observer
 */
public abstract class EnemyGraphics extends EntityGraphics implements Observer {
    protected Hitbox hitbox;
    protected BufferedImage[] deathAnimation;
    protected boolean death;
    protected int deathIndex, deathTick, deathSpeed =  10;
    protected int sx, sy;
    protected AudioPlayer audioPlayer;

    /**
     * Class constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the Enemy
     * @param h: the height of the Enemy
     */
    public EnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        audioPlayer = new AudioPlayer();
        this.sx= x;
        this.sy = y;
        loadDeathAnimation();
    }

    /**
     * Upload the images involved in the animation of the enemy's death
     */
    private void loadDeathAnimation() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/morte_nemici/Morte_Nemici_DOWN.png");
        BufferedImage temp1 = loadImg("/Imgs/entitySprites/enemySprite/morte_nemici/Morte_Nemici_UP.png");

        deathAnimation = new BufferedImage[8];

        deathAnimation[0] = temp1.getSubimage(0,0, 14, 32);
        deathAnimation[1] = temp1.getSubimage(14+1,0, 14, 32);
        deathAnimation[2] = temp1.getSubimage(14+1+15,0, 14, 32);
        deathAnimation[3] = temp.getSubimage(0,0, 22, 32);
        deathAnimation[4] = temp.getSubimage(22,0, 22, 32);
        deathAnimation[5] = temp.getSubimage(43,0, 22, 32);
        deathAnimation[6] = temp.getSubimage(64,0, 18, 32);
        deathAnimation[7] = temp.getSubimage(81,0, 16, 32);

    }

    /**
     * Resets the Enemy's position
     */
    public void resetPos() {
        x = sx;
        y = sy;
    }

    /**
     * This method handles the Enemy's death animation
     * @param death: true if the enemy is dying
     */
    public void setDeath(boolean death) {
        this.death = death;
        deathIndex = 0;
        deathTick = 0;
    }

    /**
     * This method draws enemies on screen, but disables updates
     * @param g: instance of the Graphics class
     */
    public abstract void freeze(Graphics g);
}
