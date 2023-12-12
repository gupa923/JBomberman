package Model.EntityModel;

import java.awt.geom.Rectangle2D;

public abstract class Enemy extends Entity{
    protected Rectangle2D.Float bounds;
    protected String defaultDirection = "LEFT";
    protected int score;
    protected int type;
    protected boolean alive = true;
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
}
