package Model.EntityModel;

import Model.EntityModel.Enemies.*;

/**
 * Questa classe gestisce la bomba, la sua esplosione e le collissioni tra essa e le altre entitò del gioco
 * @see Model.EntityModel.Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class
Bomb extends Entity {
    /**
     * Indica il numero di bombe non esplose presenti nel gioco
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
     * Costruttore della classe
     * @param player: Il Player che ha piazzato la bomba
     * @param x: la x su cui piazzare la bomba
     * @param y: la y su cui piazzare la bomba
     */
    public Bomb(Player player, int x, int y) {
        super(x*16, y*16, 16, 16);
        this.player = player;
        BOMB_COUNTER++;
        initHitbox();
    }

    /**
     * inizializza la hitbox della bomba
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
        hitbox.setLevel(player.hitbox.getLevel());

    }

    /**
     * Aggiorna lo stato della bomba
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
     * Gestisce la collisione tra il Player e la bomba
     *
     * @param dir: la direzione in cui il player si sta muovendo
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
     * Gestisce la collisione tra il blue enemy e una bomba. Se la bomba non sta esplodendo allora viene rimossa.
     * @param enemy: Un'istanza della classe BlueEnemy
     * @param dir: la direzione verso cui si sta muovendo
     * @return: true se il nemico entra in collisione con una bomba
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
     * Gestisce la collisione tra la bomba e il nemico
     * @param enemy: Un'istanza della classe LastEnemy
     * @param dir: la direzione verso cui si muove il nemico
     * @return: true se il nemico collide con la bomba
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
     * Gestisce la collisione tra il ClownBoss e la bomba
     * @param enemy: Un'istanza della classe ClownBoss
     * @param dir: la direzione verso cui si sta muovendo il boss
     * @return: true se collide con una bomba
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
     * @param enemy: Un'Istanza della classe FinalBoss
     * @param dir: la direzione verso la quale si muove il boss
     * @return: true se il boss entra in collisione con una bomba
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
     * Gestisce la collisione tra un Enemy e una bomba
     * @param enemy: Un'istanza della classe Enemy
     * @param dir: la direzione verso cui l'enemy si sta muovendo
     * @return: true se l'enemy entra in collisione con una bomba
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
     * è un metodo che controlla se due punti sono uguali
     * @param x1: la x del primo punto
     * @param y1: la y del primo punto
     * @param x2: la x del secondo punto
     * @param y2: la y del secondo punto
     * @return: restituisce true se x1 è uguale a x2 e y1 è uguale a y2
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
