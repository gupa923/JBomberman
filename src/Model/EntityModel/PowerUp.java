package Model.EntityModel;

import Model.StateModels.Partita;

import java.awt.geom.Rectangle2D;

/**
 * Questa classe gestisce i power up del gioco. I PowerUp possono essere di diversi tipi ed hanno effetti diversi in base ad esso
 * @see Model.EntityModel.Entity
 * @see PowerUpType
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class PowerUp extends Entity{
    public static Player player;
    private final String name;
    private final int val;
    private final int id;
    private boolean collision, active = true;
    private boolean over = true;
    private final Rectangle2D.Float bounds;

    /**
     * Costruttore della classe
     * @param x: coordinata x del PowerUp
     * @param y: coordinata y del PowerUp
     * @param pType: il tipo del PowerUp
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
     * Aggiorna lo stato del PowerUp, controlla quindi se è stato preso o meno dal Player
     */
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

    /**
     * Applica l'effetto del PowerUp
     */
    private void applyPowerUP() {
        switch (name){
            case "LIVE_UP" -> {
                Player.VITE += val;
                player.sendMessage(+1);
            }
            case "BOMB_UP" -> {
                Player.MAX_BOMB_NUMS += val;
            }
            case "CAKE" -> {
                Partita.SCORE += val;
                player.sendMessage(val);
            }
            case "HP_PLUS" -> {
                Player.HP += val;
                player.sendMessage("IMMORTALITY");
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
            case "SPEED" -> {
                player.moreSpeed();
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
