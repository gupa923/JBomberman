package Model.EntityModel.Enemies;

import Model.EntityModel.Entity;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;

import java.awt.geom.Rectangle2D;

public abstract class Enemy extends Entity {
    protected Rectangle2D.Float bounds;
    protected String defaultDirection = "LEFT";
    protected int score;
    protected int type;
    protected boolean alive = true;
    protected boolean dying = false;
    protected int dynigTick;
    protected int sx, sy;
    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public int[] toArr(){
        return new int[] {x, y, w, h, type};
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void playerHit(Player player);

    public int getScore() {
        return score;
    }

    public void resetPos() {
        this.x = sx;
        this.y = sy;
        hitbox.x = x;
        hitbox.y = y + 8;
        bounds.x = x;
        bounds.y = y + 8;
    }
}
