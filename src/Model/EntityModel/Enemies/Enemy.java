package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Entity;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import static Model.EntityModel.Player.BOMBS;

/**
 * Questa classe contiene tutte le informazioni comuni a tutti i nemici. Estende la classe Entity. Ogni nemico ha una x, una y, una larghezza, un'altezza e un type. Inoltre il nemico a due stati che non possono essere uguali allo stesso momento: cioè alive e dying.
 * @see Model.EntityModel.Entity
 */
public abstract class Enemy extends Entity {
    protected boolean immortality = false;
    protected int immortalityTick;
    protected Rectangle2D.Float bounds;
    protected String defaultDirection = "LEFT";
    protected String[] dirs = new String[] {"LEFT", "RIGHT", "UP", "DOWN"};
    protected Random r = new Random();
    protected int score;
    protected int type;
    protected boolean alive = true;
    protected boolean dying = false;
    protected int dynigTick;
    protected int sx, sy;
    protected int HP, defaultHP;

    /**
     * Costruisce un nemico a partire da quattro interi e inizializza la hitbox
     * @param x: ascissa punto di spawn
     * @param y: ordinata del punto di spawn
     * @param w: larghezza
     * @param h: altezza
     */
    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * restituisce un array di interi contenente la rappresentazione del nemico in int[]. Questo array contiene l'ascissa, l'ordinata, la larghezza, l'altezza e il tipo.
     * @return: la rappresentazione del nemico sotto forma di matrice di interi
     */
    public int[] toArr(){
        return new int[] {x, y, w, h, type};
    }

    protected boolean intersect(String dir) {
        for (Bomb b : BOMBS){
            if (b.intersect(this, dir)){
                return true;
            }
        }
        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * il metodo gestisce la collisione tra il nemico e il player
     * @param player: il player
     */
    public void playerHit(Player player){
        Hitbox pHitbox = player.getHitbox();
        if (bounds.intersects(new Rectangle2D.Float(pHitbox.x, pHitbox.y, pHitbox.w, pHitbox.h))){
            player.hit();
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * resetta la posizione e la hitbox del nemico alla posizione iniziale
     */
    public void resetPos() {
        this.x = sx;
        this.y = sy;
        dying = false;
        hitbox.x = x;
        hitbox.y = y + 8;
        bounds.x = x;
        bounds.y = y + 8;
    }

    public Rectangle2D.Float getBounds() {
        return bounds;
    }

    public void resetHP() {
        HP = defaultHP;
    }
}
