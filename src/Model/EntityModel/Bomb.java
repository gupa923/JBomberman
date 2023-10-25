package Model.EntityModel;

import Model.Hitbox;

public class Bomb extends Entity{
    public static int BOMB_COUNTER = 0;
    private int notCollideTick = 0;
    private final int collideTickLim = 180;
    public Bomb(int x, int y) {
        super(x*16, y*16, 16, 16);
        BOMB_COUNTER++;
        initHitbox();
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
    }

    @Override
    public void update() {
        notCollideTick++;
    }

    public boolean intersect(Player player, String dir){
        Hitbox pHitbox = player.getHitbox();
        if (notCollideTick <= 180)
            return false;
        switch (dir){
            case "LEFT", "UP" -> {
                return checkPoints((hitbox.x) / 16, hitbox.y/16, pHitbox.x/16,pHitbox.y/16);
            }
            case "RIGHT" -> {
                return checkPoints((hitbox.x) / 16, hitbox.y/16, (pHitbox.x + pHitbox.w)/16,pHitbox.y/16);
            }
            case "DOWN" -> {
                return checkPoints((hitbox.x) / 16, (hitbox.y )/16, pHitbox.x/16,(pHitbox.y + pHitbox.h)/16);
            }
        }
        return false;
        //return checkPoints(hitbox.x + hitbox.w, hitbox.y, pHitbox.x, pHitbox.h) || checkPoints(hitbox.x + hitbox.w, hitbox.y+ hitbox.h, pHitbox.x, pHitbox.y+ pHitbox.h);

    }

    private boolean checkPoints(int x1, int y1, int x2, int y2){
        return (x1 == x2) && (y1 == y2);
    }
}
