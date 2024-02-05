package Model.EntityModel;
import Model.Level;
/**
 * This class handles the game's obstacles. Each obstacle is associated with a level
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Obstacle extends Entity{
    private boolean hit;
    private final Level level;
    private int despawnTick = 0;
    private final int lim = 120;

    /**
     * Class Constructor
     * @param level: the level at which the obstacle is associated
     * @param x: the x coordinate of the obstacle
     * @param y: the y coordinate of the obstacle
     */
    public Obstacle(Level level, int x, int y) {
        super(x, y, 16, 16);
        this.level = level;
        initHitbox();
    }

    /**
     * initializes the hitbox
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, w, h);
    }

    /**
     * Update the obstacle status
     */
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
