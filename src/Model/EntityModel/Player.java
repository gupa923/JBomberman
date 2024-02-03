package Model.EntityModel;

import java.util.ArrayList;

/**
 * questa classe rappresenta il giocatore nel gioco. Gestisce le azioni e lo stato del giocatore. Al Player sono associate le bombe in gioco
 * @see Entity
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Player extends Entity{
    /**
     * Contatore che mantiene il numero di ostacoli distrutti
     */
    public static int OBSTACLE_DESTROYED = 0;
    /**
     * contatore che contiene il numero di vite rimanenti al Player
     */
    public static int VITE = 7;
    private String action = "STAY";
    private Bomb bomb;
    /**
     * ArrayList che contiene le bombe attive
     */
    public static ArrayList<Bomb> BOMBS = new ArrayList<>();
    private final int speed = 1;
    private int speedTick, speedClock = 3;

    private boolean moving;
    public static int MAX_BOMB_NUMS = 1;
    private boolean alive = true;
    /**
     * COntiene il numero di HP del Player
     */
    public static int HP = 1;
    private boolean immortality = true;
    private int immortalityTick;
    private boolean walkOver;

    private int tSpeedClock = speedClock;
    private boolean tWalkOver = walkOver;
    private int tMaxBombs = MAX_BOMB_NUMS;
    private int tHP = HP;
    private boolean transition;
    private int transitionTick;
    private boolean dying;
    private int dynigTick;

    /**
     * Costruttore della classe
     * @param x: ascissa del punto di spawn del Player
     * @param y: ordinata del punto di spawn del Player
     * @param w; larghezza del Player
     * @param h: altezza del Player
     */
    public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
        initHitbox();
    }

    /**
     * inizializza la hitbox
     */
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y + 8, 15, 15);
    }

    /**
     * Questo metodo aggiorna lo stato del Player, le azioni dello stesso e invia di conseguenza notifiche agli observer
     */
    @Override
    public void update() {
        for (int x = 0; x < BOMBS.size(); x++){
            BOMBS.get(x).update();
        }
        if (dying){
            dynigTick++;
            if (dynigTick >= 160){
                dynigTick = 0;
                dying = false;
                alive = false;
            }
            return;
        }
        if (transition){
            transitionTick++;
            return;
        }
        if (walkOver)
            hitbox.setWalkOver(true);
        if (moving) {
            if (speedTick % speedClock == 0) {
                switch (action) {
                    case "LEFT" -> {
                        if ((hitbox.checkCollision(hitbox.x - speed, hitbox.y) && hitbox.checkCollision(hitbox.x - speed, hitbox.y + hitbox.h - 1))) {
                            x -= speed;
                            hitbox.update(-speed, 0);
                            if (BOMBS.isEmpty() || !intersect("LEFT"))
                                sendMessage(action);
                            else {
                                x += speed;
                                hitbox.update(+speed, 0);
                                sendMessage("STAY");
                            }
                        } else {
                            moving = false;
                            sendMessage("STAY");
                        }
                    }
                    case "RIGHT" -> {
                        if (hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h - 1)) {
                            x += speed;
                            hitbox.update(speed, 0);
                            if (BOMBS.isEmpty() || !intersect("RIGHT"))
                                sendMessage(action);
                            else {
                                x -= speed;
                                hitbox.update(-speed, 0);
                                sendMessage("STAY");
                            }
                        } else {
                            moving = false;
                            sendMessage("STAY");
                        }
                    }
                    case "UP" -> {
                        if (hitbox.checkCollision(hitbox.x, hitbox.y - speed) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y - speed)) {
                            y -= speed;
                            hitbox.update(0, -speed);
                            if (BOMBS.isEmpty() || !intersect("UP"))
                                sendMessage(action);
                            else {
                                y += speed;
                                hitbox.update(0, +speed);
                                sendMessage("STAY");
                            }
                        } else {
                            moving = false;
                            sendMessage("STAY");
                        }
                    }
                    case "DOWN" -> {
                        if (hitbox.checkCollision(hitbox.x, hitbox.y + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h)) {
                            y += speed;
                            hitbox.update(0, speed);
                            if (BOMBS.isEmpty() || !intersect("DOWN"))
                                sendMessage(action);
                            else {
                                y -= speed;
                                hitbox.update(0, -speed);
                                sendMessage("STAY");
                            }
                        } else {
                            moving = false;
                            sendMessage("STAY");
                        }
                    }
                    default -> sendMessage("STAY");
                }
            }
        }else{
            switch (action){
                case "BOMB" -> {
                    spawnBomb();
                }
                default -> {
                    intersect("STAY");
                    sendMessage("STAY");
                }
            }
        }

        if (immortality){
            immortalityTick++;
            if (immortalityTick >= 1200){
                immortality = false;
                sendMessage("NO IMMORTALITY");
                immortalityTick = 0;
            }
        }
        speedTick++;
    }

    /**
     * Per ogni bomba nel gioco controlla se il Player è entrato in collisione con la bomba.
     * @param dir: direzione verso cui si sta muovendo il giocatore
     * @return: restituisce true se il giocatore colpisce una bomba
     */
    private boolean intersect(String dir) {
        for (Bomb b : BOMBS){
            if (b.intersect(dir)){
                return true;
            }
        }
        return false;
    }

    /**
     * questo metodo crea un bomba. In primo luogo viene controllato se il giocatore ha raggiunto il massimo numero di bombe possibili. A questo punto vede se la posizione in cui si vuole posizionare questa bomba è lecita o meno.
     * Se la posizione è legale allore viene generata una bomba e viene incrementato il valore di BOMB_COUNTER.
     */
    private void spawnBomb() {
        if (Bomb.BOMB_COUNTER < MAX_BOMB_NUMS) {
            for (Bomb b : BOMBS){
                if (b.getX() == (x/16)*16 && b.getY() == ((y+8)/16)*16){
                    return;
                }
            }
            if (hitbox.getData()[(y+8)/16][x/16] != 0 || hitbox.getData()[(y+8)/16][x/16] != 1){
                for (Obstacle o : hitbox.getLevel().getObstacles()){
                    if (o.getX() == (x/16)*16 && o.getY() == ((y+8)/16)*16){
                        return;
                    }
                }
            }
            bomb = new Bomb(this,x/16, (y+8)/16);

            BOMBS.add(bomb);

            sendMessage("BOMB");
            action = "STAY";
        }
    }

    /**
     * Questo metodo fa esplodere la bomba
     * @param b: la bomba che deve esplodere
     */
    public void explodeBomb(Bomb b) {

        Bomb.BOMB_COUNTER --;
        b.setExplosionTiles(ExplosionCreator.CreateExplosionTiles(b));
        b.setExploding(true);
        sendMessage(b.getExplosionTiles());
    }

    /**
     * Dopo che l'esplosione della bomba è finita, questo metodo la rimuove dal gioco
     * @param b: bomba da rimuovere dall'array
     */
    public void removeBomb(Bomb b) {
        b.setExploding(false);
        BOMBS.remove(b);
        String m1 = String.valueOf(b.getX());
        String m2 = String.valueOf(b.getY());
        sendMessage(new String[] {m1, m2});
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * resetta tutti i valori del player ai valori iniziali
     */
    public void reset() {
        transition = false;
        transitionTick = 0;
        x = 32;
        y = 8;
        alive = true;
        immortality = true;
        immortalityTick = 0;
        action = "STAY";
        HP = 1;
        hitbox.x = x;
        speedClock = 3;
        hitbox.y = y + 8;
        BOMBS.clear();
        walkOver = false;
        hitbox.setWalkOver(false);
        Bomb.BOMB_COUNTER = 0;
    }

    private void bufferPowerUp(){
         tSpeedClock = speedClock;
        tWalkOver = walkOver;
         tMaxBombs = MAX_BOMB_NUMS;
         tHP = HP;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * resetta la posizione del player
     */
    public void resetPos() {
        bufferPowerUp();
        transition = false;
        transitionTick = 0;
        x = 32;
        y = 8;
        hitbox.x = x;
        hitbox.y = y + 8;
        alive = true;
        walkOver = false;
        hitbox.setWalkOver(false);
        speedClock = 3;
        immortality = true;
        immortalityTick = 0;
        action = "STAY";
        MAX_BOMB_NUMS = 1;
        HP = 1;
    }

    /**
     * Gestisce il danno subito dal nemico e la sua morte
     */
    @Override
    public void hit(){
        if (!immortality) {
            HP--;
            if (HP == 0) {
                dying = true;
                sendMessage("DYING");
            }
            immortality = true;
            immortalityTick = 0;
            sendMessage("IMMORTALITY");
        }
    }

    /**
     * Aumenta la velocità del player
     */
    public void moreSpeed(){
        speedClock--;
        if (speedClock < 1){
            speedClock = 1;
        }
    }

    public boolean isImmortality() {
        return immortality;
    }

    public void setImmortality(boolean immortality) {
        this.immortality = immortality;
        sendMessage("IMMORTALITY");
    }

    public void resetImmortalityTick(){
        immortalityTick = 0;
        sendMessage("IMMORTALITY");
    }

    public boolean isWalkOver() {
        return walkOver;
    }

    public void setWalkOver(boolean walkOver) {
        this.walkOver = walkOver;
    }

    /**
     * Resetta i valori dei power up a quelli precedenti
     */
    public void loadBufferPUps() {
        HP = tHP;
        MAX_BOMB_NUMS = tMaxBombs;
        walkOver = tWalkOver;
        hitbox.setWalkOver(walkOver);
        speedClock = tSpeedClock;
    }

    public void setTransition(boolean b) {
        transition = b;
    }

    public int getTransitionTick() {
        return transitionTick;
    }
}
