package Model.EntityModel;

import Model.EntityModel.Enemies.*;

/**
 * This class manages the bomb, its explosion and the collisions between it and the other entities in the game
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class
Bomb extends Entity {
    /**
     * Indicates the number of unexploded bombs in the game
     */
    public static int BOMB_COUNTER = 0;
    private int notCollideTick = 0;
    private final Player player;
    private final int collideTickLim = 180;
    private final int explosionTick = 480;
    private final int explosionEnd = 600;
    private int[][] explosionTiles;
    private boolean exploding;

    /**
     * Class constructor
     * @param player: The Player who planted the bomb
     * @param x: the x on which to place the bomb
     * @param y: the y on which to place the bomb
     */
    public Bomb(Player player, int x, int y) {
        super(x*16, y*16, 16, 16);
        this.player = player;
        BOMB_COUNTER++;
        initHitbox();
    }

    /**
     * initializes the bomb's hitbox
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
        hitbox.setLevel(player.hitbox.getLevel());

    }

    /**
     * Update bomb status
     */
    @Override
    public void update() {
        notCollideTick++;
        if (notCollideTick == explosionTick) {
            player.explodeBomb(this);
        } else if (notCollideTick == explosionEnd){
            player.removeBomb(this);
        }
    }

    /**
     * Manages the collision between the Player and the bomb
     *
     * @param dir: the direction in which the player is moving
     * @return
     */
    public boolean intersect( String dir){
        Hitbox pHitbox = player.getHitbox();
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            if (!player.isWalkOver()) {
                switch (dir) {
                    case "LEFT", "UP" -> {
                        return checkPoints((hitbox.x) / 16, hitbox.y / 16, pHitbox.x / 16, pHitbox.y / 16);
                    }
                    case "RIGHT" -> {
                        return checkPoints((hitbox.x) / 16, hitbox.y / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16);
                    }
                    case "DOWN" -> {
                        return checkPoints((hitbox.x) / 16, (hitbox.y) / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16);
                    }
                }
            }
            return false;
        }else{
            if (!player.isImmortality()) {
                switch (dir) {
                    case "LEFT", "UP" -> {
                        for (int[] p : explosionTiles) {
                            if (checkPoints((p[0]) / 16, p[1] / 16, pHitbox.x / 16, pHitbox.y / 16)) {
                                player.hit();
                                break;
                            }
                        }
                    }
                    case "RIGHT" -> {
                        for (int[] p : explosionTiles) {
                            if (checkPoints(p[0] / 16, p[1] / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)) {
                                player.hit();
                                break;
                            }
                        }
                    }
                    case "DOWN" -> {
                        for (int[] p : explosionTiles) {
                            if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                                player.hit();
                                break;
                            }
                        }
                    }
                    case "STAY" -> {
                        if (explosionTiles != null) {
                            for (int[] p : explosionTiles) {
                                if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                                    player.hit();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * Manages the collision between the blue enemy and a bomb. If the bomb is not exploding then it is removed.
     * @param enemy: An instance of the BlueEnemy class
     * @param dir: the direction in which it is moving
     * @return: true if the enemy collides with a bomb
     */
    public boolean intersect(BlueEnemy enemy, String dir){
        Hitbox pHitbox = enemy.getHitbox();
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            switch (dir) {
                case "LEFT", "UP" -> {
                    if( checkPoints((hitbox.x) / 16, hitbox.y / 16, pHitbox.x / 16, pHitbox.y / 16)){
                        player.removeBomb(this);
                        BOMB_COUNTER--;
                        return false;
                    }
                }
                case "RIGHT" -> {
                    if( checkPoints((hitbox.x) / 16, hitbox.y / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)){
                        player.removeBomb(this);
                        BOMB_COUNTER--;
                        return false;
                    }
                }
                case "DOWN" -> {
                    if( checkPoints((hitbox.x) / 16, (hitbox.y) / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)){
                        player.removeBomb(this);
                        BOMB_COUNTER--;
                        return false;
                    }
                }
            }
            return false;
        }else{
            switch (dir) {
                case "LEFT", "UP" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints((p[0]) / 16, p[1] / 16, pHitbox.x / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "RIGHT" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "DOWN" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "STAY" -> {
                    if (explosionTiles != null) {
                        for (int[] p : explosionTiles) {
                            if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                                enemy.hit();
                                break;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * Manages the collision between the bomb and the enemy
     * @param enemy: An instance of the LastEnemy class
     * @param dir: the direction in which the enemy is moving
     * @return: true if the enemy collides with the bomb
     */
    public boolean intersect(LastEnemy enemy, String dir){
        Hitbox pHitbox = enemy.getHitbox();
        if (exploding){
            switch (dir) {
                case "LEFT", "UP" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints((p[0]) / 16, p[1] / 16, pHitbox.x / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "RIGHT" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "DOWN" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "STAY" -> {
                    if (explosionTiles != null) {
                        for (int[] p : explosionTiles) {
                            if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                                enemy.hit();
                                break;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }


    /**
     * Handles the collision between the ClownBoss and the bomb
     * @param enemy: An instance of the ClownBoss class
     * @param dir: the direction the boss is moving in
     * @return: true if it collides with a bomb
     */
    public boolean intersect(ClownBoss enemy, String dir){
        //Hitbox pHitbox = enemy.getDamageBox();
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            return false;
        }else{
            for (int[] p: explosionTiles) {
                if (enemy.getBounds().contains(p[0], p[1])){
                    enemy.hit();
                    break;
                }
            }
            return false;
        }
    }

    /**
     *
     * @param enemy: An Instance of the FinalBoss class
     * @param dir: the direction in which the boss moves
     * @return: true if the boss collides with a bomb
     */
    public boolean intersect(FinalBoss enemy, String dir){
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            return false;
        }else{
            for (int[] p: explosionTiles) {
                if (enemy.getBounds().contains(p[0], p[1])){
                    enemy.hit();
                    break;
                }
            }
            return false;
        }
    }


    /**
     * Handles the collision between an Enemy and a bomb
     * @param enemy: An instance of the Enemy class
     * @param dir: the direction in which the enemy is moving
     * @return: true if the enemy collides with a bomb
     */
    public boolean intersect(Enemy enemy, String dir){
        Hitbox pHitbox = enemy.getHitbox();
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            switch (dir) {
                case "LEFT", "UP" -> {
                    return checkPoints((hitbox.x) / 16, hitbox.y / 16, pHitbox.x / 16, pHitbox.y / 16);
                }
                case "RIGHT" -> {
                    return checkPoints((hitbox.x) / 16, hitbox.y / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16);
                }
                case "DOWN" -> {
                    return checkPoints((hitbox.x) / 16, (hitbox.y) / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16);
                }
            }
            return false;
        }else{
            switch (dir) {
                case "LEFT", "UP" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints((p[0]) / 16, p[1] / 16, pHitbox.x / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "RIGHT" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "DOWN" -> {
                    for (int[] p : explosionTiles) {
                        if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                            enemy.hit();
                            break;
                        }
                    }
                }
                case "STAY" -> {
                    if (explosionTiles != null) {
                        for (int[] p : explosionTiles) {
                            if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)) {
                                enemy.hit();
                                break;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
    /**
     * It is a method that checks whether two points are equal
     * @param x1: the x of the first point
     * @param y1: the y of the first point
     * @param x2: the x of the second point
     * @param y2: the y of the second point
     * @return: returns true if x1 equals x2 and y1 equals y2
     */
    private boolean checkPoints(int x1, int y1, int x2, int y2){
        return (x1 == x2) && (y1 == y2);
    }


    @Override
    public boolean equals(Object o){
        if (o instanceof Bomb b){
            return this.x == b.x && this.y == b.y;
        }
        return false;
    }

    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    public void setExplosionTiles(int[][] explosionTiles) {
        this.explosionTiles = explosionTiles;
    }

    public int[][] getExplosionTiles() {
        return explosionTiles;
    }
}
