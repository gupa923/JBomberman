package Model.EntityModel.Enemies;

import Model.EntityModel.Bomb;
import Model.EntityModel.Hitbox;
import Model.EntityModel.Player;
import Model.EntityModel.Rocket;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static Model.EntityModel.Player.BOMBS;

/**
 * This class manages the final boss of the game, its characteristic is to launch rockets.
 * @see Model.EntityModel.Enemies.Enemy
 * @author Guido Paluzzi, Matteo Santucci
 */
public class FinalBoss extends Enemy{
    private boolean moving = true;
    private int updateTick;
    private ArrayList<Rocket> rockets = new ArrayList<>();
    /**
     * Constructs an enemy from four wholes and initializes the hitbox
     *
     * @param x : abscissa spawn point
     * @param y : ordinate spawn point
     * @param w : lenght
     * @param h : height
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

    /**
     * Starts the hitboxes
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
        bounds = new Rectangle2D.Float(x, y, 74, 74);
    }

    /**
     * Updates the game state and notifies its observer accordingly
     */
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

        for (int i = 0; i < rockets.size(); i++){
            Rocket t = rockets.get(i);
            t.update();
            if (checkRocketOutOfBoards(t)){
                rockets.remove(t);
            }
        }
    }

    /**
     * Check if the rocket is outside the edge of the map
     * @param rocket: the Rocket on which to carry out the check
     * @return: true if the rocket is off the map
     */
    private boolean checkRocketOutOfBoards(Rocket rocket) {
        if (rocket.getX()+rocket.getW() < 0){
            return true;
        }else if (rocket.getX() > 272){
            return true;
        }else if (rocket.getY() + rocket.getH() < 0){
            return true;
        }else if (rocket.getY() > 208){
            return true;
        }
        return false;
    }

    /**
     * Check if the boss or one of the rockets hit the Player
     * @param player: the player
     */
    @Override
    public void playerHit(Player player){
        Hitbox pHitbox = player.getHitbox();
        if (bounds.intersects(new Rectangle2D.Float(pHitbox.x, pHitbox.y, pHitbox.w, pHitbox.h))){
            player.hit();
        }
        for (Rocket r : rockets){
            if (r.getBounds().intersects(new Rectangle2D.Float(pHitbox.x, pHitbox.y, pHitbox.w, pHitbox.h))){
                player.hit();
                return;
            }
        }
    }

    /**
     * This method creates a rocket and adds it to the list of rockets in the game
     */
    private void shootRocket() {
        int i = r.nextInt(4);
        rockets.add(new Rocket(x+28, y + 20, 20, 20, dirs[i]));
        setChanged();
        notifyObservers(new int[] {x+28, y+20, 20, 20, i});
    }

    /**
     * Check if the Boss collides with a bomb
     * @param dir: the direction in which the enemy is moving
     * @return: true if the boss collides with a bomb
     */
    @Override
    protected boolean intersect(String dir) {
        for (Bomb b : BOMBS) {
            if (b.intersect(this, dir)) {
                return true;
            }
        }
        return false;
    }

    /**
     * It manages the damage suffered by the boss and also the death of the same
     */
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
