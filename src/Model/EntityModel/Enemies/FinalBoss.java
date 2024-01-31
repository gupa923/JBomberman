package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Rocket;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static Model.EntityModel.Player.BOMBS;

public class FinalBoss extends Enemy{
    private boolean moving = true;
    private int updateTick;
    private ArrayList<Rocket> rockets = new ArrayList<>();
    /**
     * Costruisce un nemico a partire da quattro interi e inizializza la hitbox
     *
     * @param x : ascissa punto di spawn
     * @param y : ordinata del punto di spawn
     * @param w : larghezza
     * @param h : altezza
     */
    public FinalBoss(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        score = 10000;
        type = 10;
        HP = 5;
        defaultHP = HP;

        immortality = false;
        initHitbox();
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
        bounds = new Rectangle2D.Float(x, y, 74, 74);
    }

    @Override
    public void update() {
        if (dying){
            dynigTick++;
            if (dynigTick >= 240){
                dynigTick = 0;
                dying = false;
                alive = false;
            }
            return;
        }
        if (updateTick%360 == 0){
            shootRocket();
        }
        if (updateTick %8 == 0) {

            switch (defaultDirection) {
                case "LEFT" -> {
                    if ((hitbox.checkCollision(hitbox.x - 1, hitbox.y) && hitbox.checkCollision(hitbox.x - 1, hitbox.y + h - 1))) {
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
                    if (hitbox.checkCollision(hitbox.x + w, hitbox.y) && hitbox.checkCollision(hitbox.x + w, hitbox.y + h - 1)) {
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
                    if (hitbox.checkCollision(hitbox.x, hitbox.y - 1) && hitbox.checkCollision(hitbox.x + w - 1, hitbox.y - 1)) {
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
                    if (hitbox.checkCollision(hitbox.x, hitbox.y + h) && hitbox.checkCollision(hitbox.x + w - 1, hitbox.y + h)) {
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

    private void shootRocket() {
        int i = r.nextInt(4);
        rockets.add(new Rocket(x+28, y + 20, 20, 20, dirs[i]));
        setChanged();
        notifyObservers(new int[] {x+28, y+20, 20, 20, i});
    }

//    private void changeDirection() {
//        if ((hitbox.x/16)% 2 == 0 && (hitbox.y/16)%2 == 1){
//            defaultDirection = dirs[r.nextInt(4)];
//        }
//    }

    @Override
    protected boolean intersect(String dir) {
        for (Bomb b : BOMBS){
            if (b.intersect(this, dir)){
                return true;
            }
        }
        return false;
    }

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

    public int getHP() {
        return HP;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof FinalBoss e){
            return e.x == this.x && e.y == this.y;
        }
        return false;
    }
}
