package Model.EntityModel;

import java.awt.geom.Rectangle2D;

import static Model.EntityModel.Player.BOMBS;

public class RedEnemy extends Enemy{

    private boolean moving = true;

    private int updateTick;
    private int HP = 1;
    public RedEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        type = 1;
        initHitbox();
    }

    @Override
    public void update() {
        if (updateTick %2 == 0) {
            switch (defaultDirection) {
                case "LEFT" -> {
                    if ((hitbox.checkCollision(hitbox.x - 1, hitbox.y) && hitbox.checkCollision(hitbox.x - 1, hitbox.y + hitbox.h - 1))) {
                        x -= 1;
                        hitbox.update(-1, 0);
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("LEFT")) {
                            x += 1;
                            hitbox.update(+1, 0);
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
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("RIGHT")) {
                            x -= 1;
                            hitbox.update(-1, 0);
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
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("UP")) {
                            y += 1;
                            hitbox.update(0, 1);
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
                        moving = true;
                        if (!BOMBS.isEmpty() && intersect("DOWN")) {
                            y -= 1;
                            hitbox.update(0, -1);
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
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y + 8, 16, 16);
        bounds = new Rectangle2D.Float(x, y + 8, 16, 16);
    }
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
    @Override
    public void hit(){
        HP--;
        if (HP <= 0){
            alive = false;
        }
    }
    @Override
    public Hitbox getHitbox() {
        return super.getHitbox();
    }


}
