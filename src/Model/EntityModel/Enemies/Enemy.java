package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Entity;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import static Model.EntityModel.Player.BOMBS;

/**
 * This class contains all the information common to all enemies. Extends the Entity class. Each enemy has an x, a y, a width, a height and a type. Furthermore, the enemy has two states that cannot be equal at the same time: that is, alive and dying.
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class Enemy extends Entity {
    protected boolean immortality = false;
    protected int immortalityTick;
    protected Rectangle2D.Float bounds;
    protected String defaultDirection = "LEFT";
    protected String[] dirs = new String[] {"LEFT", "RIGHT", "UP", "DOWN"};
    protected Random r = new Random();
    protected int score;
    protected int type;
    protected boolean alive = true;
    protected boolean dying = false;
    protected int dynigTick;
    protected int sx, sy;
    protected int HP, defaultHP;

    /**
     * Constructs an enemy from four wholes and initializes the hitbox
     * @param x: abscissa spawn point
     * @param y: ordinate spawn point
     * @param w: lenght
     * @param h: height
     */
    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * returns an array of integers containing the enemy representation in int[]. This array contains the abscissa, ordinate, width, height and type.
     * @return: the representation of the enemy in the form of an array of integers
     */
    public int[] toArr(){
        return new int[] {x, y, w, h, type};
    }

    /**
     * Control the enemy's intersection with bombs
     * @param dir: the direction in which the enemy is moving
     * @return: returns true if the enemy collided with a bomb.
     */
    protected boolean intersect(String dir) {
        for (Bomb b : BOMBS){
            if (b.intersect(this, dir)){
                return true;
            }
        }
        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * the method manages the collision between the enemy and the player
     * @param player: il player
     */
    public void playerHit(Player player){
        Hitbox pHitbox = player.getHitbox();
        if (bounds.intersects(new Rectangle2D.Float(pHitbox.x, pHitbox.y, pHitbox.w, pHitbox.h))){
            player.hit();
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * resets the enemy's position and hitbox to the starting position
     */
    public void resetPos() {
        this.x = sx;
        this.y = sy;
        dying = false;
        hitbox.x = x;
        hitbox.y = y + 8;
        bounds.x = x;
        bounds.y = y + 8;
    }

    public Rectangle2D.Float getBounds() {
        return bounds;
    }

    public void resetHP() {
        HP = defaultHP;
    }
}
