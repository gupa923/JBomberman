package Model.EntityModel;

import Model.Level;

public class Obstacle extends Entity{
    private boolean hit;
    private Level level;
    private int despawnTick = 0;
    private int lim = 120;
    public Obstacle(Level level, int x, int y) {
        super(x, y, 16, 16);
        this.level = level;
        initHitbox();
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, w, h);
    }

    @Override
    public void update() {
        if (hit){
            despawnTick++;
            if (despawnTick >= lim){
                level.removeObstacle(this);
            }
        }
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
