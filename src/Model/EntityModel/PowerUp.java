package Model.EntityModel;

import Model.StateModels.Partita;

import java.awt.geom.Rectangle2D;

/**
 * This class handles the game's power ups. PowerUps can be of different types and have different effects based on it
 * @see Model.EntityModel.Entity
 * @see PowerUpType
 * @author Guido Paluzzi, Matteo Santucci
 */
public class PowerUp extends Entity{
    public static Player PLAYER;
    private final String name;
    private final int val;
    private final int id;
    private boolean collision, active = true;
    private boolean over = true;
    private final Rectangle2D.Float bounds;

    /**
     * Class Constructor
     * @param x: x coordinate of the PowerUp
     * @param y: y coordinate of the PowerUp
     * @param pType: the PowerUp type
     */
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

    /**
     * Update the status of the PowerUp, then check whether it has been taken by the Player or not
     */
    @Override
    public void update() {
        if (active) {
        if (bounds.contains(PLAYER.getHitbox().x, PLAYER.getHitbox().y))
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

    /**
     * Apply the PowerUp effect
     */
    private void applyPowerUP() {
        switch (name){
            case "LIVE_UP" -> {
                Player.VITE += val;
                PLAYER.sendMessage(+1);
            }
            case "BOMB_UP" -> {
                Player.MAX_BOMB_NUMS += val;
            }
            case "CAKE" -> {
                Partita.SCORE += val;
                PLAYER.sendMessage(val);
            }
            case "HP_PLUS" -> {
                Player.HP += val;
                PLAYER.sendMessage("IMMORTALITY");
            }
            case "HP_MEN" -> {
                PLAYER.hit();

            }
            case "IMMORTALITY" -> {
                if (PLAYER.isImmortality()){
                    PLAYER.resetImmortalityTick();
                }else {
                    PLAYER.setImmortality(true);
                }
            }case "WALK_OVER" -> {
                PLAYER.setWalkOver(true);
            }
            case "SPEED" -> {
                PLAYER.moreSpeed();
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
    public void setOver(boolean over) {
        this.over = over;
    }
}
