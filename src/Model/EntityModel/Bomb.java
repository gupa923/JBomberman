package Model.EntityModel;

import Model.Hitbox;

public class Bomb extends Entity{
    public Bomb(int x, int y) {
        super(x, y, 16, 16);

    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
    }

    @Override
    public void update() {

    }
}
