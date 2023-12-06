package View.EntitiesGraphics;

import Model.EntityModel.Hitbox;

import java.awt.*;
import java.util.Observer;

public abstract class EnemyGraphics extends EntityGraphics implements Observer {
    protected Hitbox hitbox;
    public EnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}
