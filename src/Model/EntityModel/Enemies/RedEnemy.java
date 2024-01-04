package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;

import java.awt.geom.Rectangle2D;

import static Model.EntityModel.Player.BOMBS;

public class RedEnemy extends Enemy {

    private boolean moving = true;
    private int updateTick;
    public RedEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        score = 150;
        type = 1;
        HP = 1;
        defaultHP = HP;

        initHitbox();
    }

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
        if (updateTick %4 == 0) {
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

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y + 8, 16, 16);
        bounds = new Rectangle2D.Float(x, y + 8, 16, 16);
    }

    @Override
    public void hit() {
        System.out.println(immortality);
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
    public Hitbox getHitbox() {
        return super.getHitbox();
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof RedEnemy){
            RedEnemy e = (RedEnemy) obj;
            return e.x == this.x && e.y == this.y;
        }
        return false;
    }
}
