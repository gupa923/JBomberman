package Model.EntityModel;

import java.awt.geom.Rectangle2D;

public class RedEnemy extends Enemy{

    private int HP = 1;
    public RedEnemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        type = 1;
        initHitbox();
    }

    @Override
    public void update() {
        switch (defaultDirection){
            case "LEFT" -> {
                if ((hitbox.checkCollision(hitbox.x - 1, hitbox.y) && hitbox.checkCollision(hitbox.x - 1, hitbox.y + hitbox.h - 1))) {
                    x -= 1;
                    hitbox.update(-1, 0);
                }else {
                    defaultDirection = "RIGHT";
                }
            }
            case "RIGHT" -> {
                if (hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h -1 )) {
                    x += 1;
                    hitbox.update(1, 0);
                }else {
                    defaultDirection = "UP";
                }
            }
            case "UP" -> {
                if(hitbox.checkCollision(hitbox.x, hitbox.y - 1) && hitbox.checkCollision(hitbox.x + hitbox.w -1, hitbox.y- 1)) {
                    y -= 1;
                    hitbox.update(0, -1);
                }else {
                    System.out.println("L");
                    defaultDirection = "DOWN";
                }
            }case "DOWN" -> {
                if ( hitbox.checkCollision(hitbox.x, hitbox.y  + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h )) {
                    y += 1;
                    hitbox.update(0, 1);
                }else {
                    defaultDirection = "LEFT";
                }
            }
        }

        sendMessage(defaultDirection);
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
    public Hitbox getHitbox() {
        return super.getHitbox();
    }
}
