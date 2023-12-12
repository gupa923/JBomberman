package View.EntitiesGraphics;

import Model.EntityModel.Hitbox;

import java.awt.*;
import java.util.Observer;

public abstract class EnemyGraphics extends EntityGraphics implements Observer {
    protected Hitbox hitbox;
    protected int sx, sy;
    public EnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.sx= x;
        this.sy = y;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public void resetPos() {
        x = sx;
        y = sy;
    }
}
