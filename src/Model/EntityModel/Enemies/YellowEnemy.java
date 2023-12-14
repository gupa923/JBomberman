package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;

import java.awt.geom.Rectangle2D;

import static Model.EntityModel.Player.BOMBS;

public class YellowEnemy extends Enemy{
    private int HP = 2;
    private int updateTick;
    private boolean moving = true;
    private boolean immortality = false;
    private int immortalityTick;

    public YellowEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        score = 300;
        type = 2;
        initHitbox();
    }

    @Override
    public void playerHit(Player player) {
        Hitbox pHitbox = player.getHitbox();
        //   boolean a = bounds.contains(pHitbox.x, pHitbox.y);
        // boolean b = bounds.contains(p.)
        if (bounds.intersects(new Rectangle2D.Float(pHitbox.x, pHitbox.y, pHitbox.w, pHitbox.h))){
            player.hit();
        }
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y + 8, 16, 16);
        bounds = new Rectangle2D.Float(x, y + 8, 16, 16);
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
                            defaultDirection = "RIGHT";
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = "RIGHT";
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
                            defaultDirection = "UP";
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = "UP";
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
                            defaultDirection = "DOWN";
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = "DOWN";
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
                            defaultDirection = "LEFT";
                            sendMessage("STAY");
                            moving = false;
                        }
                    } else {
                        defaultDirection = "LEFT";
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

    private boolean intersect(String dir) {
        for (Bomb b : BOMBS){
            if (b.intersect(this, dir)){
                return true;
            }
        }
        return false;
    }

    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof YellowEnemy){
            YellowEnemy e = (YellowEnemy) obj;
            return e.x == this.x && e.y == this.y;
        }
        return false;
    }

    @Override
    public void hit(){
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
}
