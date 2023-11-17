package Model.EntityModel;

import java.awt.geom.Rectangle2D;

public class PowerUp extends Entity{
    public static Player player;
    private String name;
    private int val;
    private int id;
    private boolean collision, active = true;
    private Rectangle2D.Float bounds;
    public PowerUp(int x, int y, PowerUpType pType) {
        super(x, y, 16, 16);
        name = pType.getName();
        id = pType.getId();
        val = pType.getVal();
        bounds = new Rectangle2D.Float(this.x, this.y, this.w, this.h);
    }

    @Override
    public void initHitbox() {

    }

    @Override
    public void update() {
        if (active) {
            if (bounds.contains(player.getHitbox().x, player.getHitbox().y))
                collision = true;
            if (collision) {
                applyPowerUP();
                collision = false;
                active = false;
            }
        }
    }

    private void applyPowerUP() {
        switch (name){
            case "LIVE_UP" -> {
                Player.VITE += val;
                System.out.println(Player.VITE);
            }
            case "BOMB_UP" -> {
                Player.MAX_BOMB_NUMS += val;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }
    public int[] toArr(){
        return new int[]{x, y, id};
    }
}
