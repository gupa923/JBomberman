package Model.EntityModel;

import java.awt.geom.Rectangle2D;

public abstract class Enemy extends Entity{
    protected Rectangle2D.Float bounds;
    protected String defaultDirection = "LEFT";
    protected int type;
    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public int[] toArr(){
        return new int[] {x, y, w, h, type};
    }
}
