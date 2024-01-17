package Model.EntityModel;
import Model.Level;
/**
 * questa classe rappresenta gli ostacoli. Estende Entity. gestisce gli ostacoli e la loro distruzione.
 * @see Model.EntityModel.Entity
 * @author gupa9
 */
public class Obstacle extends Entity{
    private boolean hit;
    private final Level level;
    private int despawnTick = 0;
    private final int lim = 120;
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

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
