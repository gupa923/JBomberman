package Model.EntityModel;

public class Obstacle extends Entity{
    public Obstacle(int x, int y) {
        super(x, y, 16, 16);
        initHitbox();
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, w, h);
    }

    @Override
    public void update() {

    }
}
