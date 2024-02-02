package Model.EntityModel.Enemies;

import Model.EntityModel.Hitbox;

import java.awt.geom.Rectangle2D;

import static Model.EntityModel.Player.BOMBS;

/**
 * Questa classe gestisce il BlueEnemy, la cui caratteristica è quella di mangiare le bombe quando ci passa sopra.
 * @author Guido Paluzzi, Matteo Santucci
 */
public class BlueEnemy extends Enemy{
    private int updateTick, immortalityTick;
    private boolean immortality = true, moving = true;

    /**
     * Costruisce il nemico a partire da quattro interi e inizializza la hitbox
     *      * @param x: ascissa punto di spawn
     *      * @param y: ordinata del punto di spawn
     *      * @param w: larghezza
     *      * @param h: altezza
     */
    public BlueEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        score = 500;
        type = 4;
        HP = 2;
        defaultHP = HP;

        initHitbox();
    }

    /**
     * Inizializza le hitbox
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y , 16, 16);
        bounds = new Rectangle2D.Float(x, y , 16, 16);
    }

    /**
     * Aggiorna lo stato del nemico e notifica gli observer di conseguenza.
     */
    @Override
    public void update() {
        if (dying){
            dynigTick++;
            if (dynigTick >= 160){
                dynigTick = 0;
                dying = false;
                alive = false;
            }
            return;
        }
        if (updateTick %5 == 0) {
            switch (defaultDirection) {
                case "LEFT" -> {
                    if ((hitbox.checkCollision(hitbox.x - 1, hitbox.y) && hitbox.checkCollision(hitbox.x - 1, hitbox.y + hitbox.h - 1))) {
                        x -= 1;
                        hitbox.update(-1, 0);
                        bounds.x --;
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("LEFT")) {
                            x += 1;
                            hitbox.update(+1, 0);
                            bounds.x++;
                            defaultDirection = dirs[r.nextInt(4)];
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = dirs[r.nextInt(4)];
                        sendMessage("STAY");
                        moving = false;
                    }
                }
                case "RIGHT" -> {
                    if (hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h - 1)) {
                        x += 1;
                        hitbox.update(1, 0);
                        bounds.x++;
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("RIGHT")) {
                            x -= 1;
                            hitbox.update(-1, 0);
                            bounds.x--;
                            defaultDirection = dirs[r.nextInt(4)];
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = dirs[r.nextInt(4)];
                        sendMessage("STAY");
                        moving = false;
                    }
                }
                case "UP" -> {
                    if (hitbox.checkCollision(hitbox.x, hitbox.y - 1) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y - 1)) {
                        y -= 1;
                        hitbox.update(0, -1);
                        bounds.y --;
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("UP")) {
                            y += 1;
                            hitbox.update(0, 1);
                            bounds.y++;
                            defaultDirection = dirs[r.nextInt(4)];
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = dirs[r.nextInt(4)];
                        sendMessage("STAY");
                        moving = false;
                    }
                }
                case "DOWN" -> {
                    if (hitbox.checkCollision(hitbox.x, hitbox.y + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h)) {
                        y += 1;
                        hitbox.update(0, 1);
                        bounds.y++;
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("DOWN")) {
                            y -= 1;
                            hitbox.update(0, -1);
                            bounds.y--;
                            defaultDirection = dirs[r.nextInt(4)];
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = dirs[r.nextInt(4)];
                        sendMessage("STAY");
                        moving = false;
                    }
                }
            }
            if (moving) {
                sendMessage(defaultDirection);
            }
        }
        if (immortality){
            immortalityTick++;
            if (immortalityTick >= 120){
                immortality = false;
                immortalityTick = 0;
            }
        }
        updateTick++;
    }

    /**
     * Questo metodo controlla la collisione tra il BlueEnemy e le bombe.
     * @param dir: la direzione verso cui si sta muovendo il nemico
     * @return: true se il nemico è entrato in collisione con una bomba che sta esplodendo
     */
    @Override
    protected boolean intersect(String dir) {
        for (int i = 0; i < BOMBS.size(); i++){
            if (BOMBS.get(i).intersect(this, dir)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gestisce il danno subito dal blueenemy e anche la morte dello stesso
     */
    @Override
    public void hit() {
        if (!immortality) {
            HP--;
            if (HP == 0) {
                dying = true;
                sendMessage("DYING");
            }
            immortality = true;
            immortalityTick = 0;
        }
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof BlueEnemy e){
            return e.x == this.x && e.y == this.y;
        }
        return false;
    }
}
