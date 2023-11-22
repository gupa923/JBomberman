package Model.EntityModel;

import Model.StateModels.Partita;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import static Model.Level.pTypes;

public class PowerUp extends Entity{
    public static Player player;
    private Random r = new Random();
    private String name;
    private int val;
    private int id;
    private boolean collision, active = true;
    private boolean over = true;
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
                if (!over) {
                    collision = true;
                }
            if (collision) {
                applyPowerUP();
                collision = false;
                active = false;
            }
        }
    }

    private void applyPowerUP() {
        if (name.equals("RANDOM")){
            PowerUpType p = pTypes[r.nextInt(6)];
            name = p.getName();
            val = p.getVal();
        }
        switch (name){
            case "LIVE_UP" -> {
                Player.VITE += val;
                System.out.println(Player.VITE);
            }
            case "BOMB_UP" -> {
                Player.MAX_BOMB_NUMS += val;
            }
            case "CAKE" -> {
                Partita.SCORE += val;
                System.out.println(Partita.SCORE);
            }
            case "HP_PLUS" -> {
                Player.HP += val;
            }
            case "HP_MEN" -> {
                player.hit();
            }
            case "IMMORTALITY" -> {
                if (player.isImmortality()){
                    player.resetImmortalityTick();
                }else {
                    player.setImmortality(true);
                }
            }case "WALK_OVER" -> {
                player.setWalkOver(true);
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

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
}
