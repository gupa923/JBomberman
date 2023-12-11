package Model.EntityModel;

import java.util.ArrayList;

/**
 * questa classe rappresenta il giocatore nel gioco. Estende la classe Entity. Gestisce le azioni e lo stato del giocatore
 *
 * @see Entity
 * @author gupa9
 */
//TODO improve the movement system fa veramente schifo. Forse si può risolvere diminuendo la hitbox.
//TODO istanziare una nuova hitbox chiamata damageBox per quando gestiremo il danno.
public class Player extends Entity{
    public static int OBSTACLE_DESTROYED = 0;
    public static int VITE = 4;
    private String action = "STAY";
    private Bomb bomb;
    public static ArrayList<Bomb> BOMBS = new ArrayList<>();
    private int speed = 1;
    private int speedTick, speedClock = 3;

    private boolean moving;
    public static int MAX_BOMB_NUMS = 1;
    private boolean alive = true;
    public static int HP = 1;
    private boolean immortality = true;
    private int immortalityTick;
    private boolean walkOver;

    public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
        initHitbox();
    }

    /**
     * inizializza la hitbox
     *
     */
    //TODO prova a cambiare i valori di h e w per vedere se il movimento migliora.
    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y + 8, 15, 15);
    }

    /**
     * questo metodo gestisce le azioni del giocatore. in particolare il movimento e la creazione delle bombe.
     */
    //TODO invece che usare +1 e -1 salvare tale valore in un campo speed, in modo da poterla aumentare con i power up
    @Override
    public void update() {
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
        for (int x = 0; x < BOMBS.size(); x++){
            BOMBS.get(x).update();
        }

        if (immortality){
            immortalityTick++;
            if (immortalityTick >= 1200){
                immortality = false;
                immortalityTick = 0;
            }
        }
        speedTick++;
    }

    /**
     * itera sull'array di bomb e restituisce true se il giocatore collide con una bomba
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
     * questo metodo permette al giocatore di generare una bomba
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
     * cerca la bomba all'interno dell'array e la fa esplodere
     * @param b: la bomba che deve esplodere
     */
    public void explodeBomb(Bomb b) {

        Bomb.BOMB_COUNTER --;
        b.setExplosionTiles(ExplosionCreator.CreateExplosionTiles(b));
        b.setExploding(true);
        sendMessage(b.getExplosionTiles());
    }

    /**
     * rimuove la bomba dall'array
     *
     * @param b: bomba da rimuovere dall'array
     */
    public void removeBomb(Bomb b) {
        b.setExploding(false);
        BOMBS.remove(b);
        String m1 = String.valueOf(b.getX());
        String m2 = String.valueOf(b.getY());
        sendMessage(new String[] {m1, m2});
    }

    /**
     * invia una notifica all'observer
     * @param arg:argomento da mandare come notifica all'observer
     */
    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
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
        x = 32;
        y = 8;
        alive = true;
        immortality = true;
        immortalityTick = 0;
        action = "STAY";
        HP = 1;
        hitbox.x = x;
        hitbox.y = y + 8;
        BOMBS.clear();
        walkOver = false;
        hitbox.setWalkOver(false);
        Bomb.BOMB_COUNTER = 0;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void resetPos() {
        x = 32;
        y = 8;
        hitbox.x = x;
        hitbox.y = y + 8;
        alive = true;
        walkOver = false;
        immortality = true;
        immortalityTick = 0;
        action = "STAY";
        MAX_BOMB_NUMS = 1;
        HP = 1;
    }

    @Override
    public void hit(){
        if (!immortality) {
            HP--;
            if (HP == 0) {
                alive = false;
                walkOver = false;
            }
            immortality = true;
            immortalityTick = 0;
        }
    }
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
    }

    public void resetImmortalityTick(){
        immortalityTick = 0;
    }

    public boolean isWalkOver() {
        return walkOver;
    }

    public void setWalkOver(boolean walkOver) {
        this.walkOver = walkOver;
    }
}
