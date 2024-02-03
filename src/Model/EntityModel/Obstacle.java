package Model.EntityModel;
import Model.Level;
/**
 * Questa classe gestisce gli ostacoli del gioco. Ogni ostacolo è associato ad un livello
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Obstacle extends Entity{
    private boolean hit;
    private final Level level;
    private int despawnTick = 0;
    private final int lim = 120;

    /**
     * Costruttore della classe
     * @param level: il livello a cui è associato l'ostacolo
     * @param x: la coordinata x dell'ostacolo
     * @param y: la coordinata y dell'ostacolo
     */
    public Obstacle(Level level, int x, int y) {
        super(x, y, 16, 16);
        this.level = level;
        initHitbox();
    }

    /**
     * inizializza la hitbox
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, w, h);
    }

    /**
     * Aggiorna lo stato dell'ostacolo
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
