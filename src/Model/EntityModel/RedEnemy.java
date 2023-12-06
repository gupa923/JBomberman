package Model.EntityModel;

import java.awt.geom.Rectangle2D;

public class RedEnemy extends Enemy{

    private int HP = 1;
    public RedEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        type = 1;
    }

    @Override
    public void update() {

    }
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y - 8, 16, 16);
        bounds = new Rectangle2D.Float(x, y - 8, 16, 16);
    }

}
