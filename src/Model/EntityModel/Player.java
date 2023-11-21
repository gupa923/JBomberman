package Model.EntityModel;

import java.util.ArrayList;

/**
 *
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
    private ArrayList<Bomb> bombs;
    private int speed = 1;
    private boolean moving;
    public static int MAX_BOMB_NUMS = 3;
    private boolean alive = true;
    public static int HP = 1;
    private boolean immortality;
    private int immortalityTick;
    public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
        bombs = new ArrayList<>();
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
     * questo metodo update permette di controllare il numero di notifiche mandate agli observer
     * il metodo controlla se il campo moving è true, quindi se il player si sta muovendo, poi in base al
     * valore del campo direction, in primo luogo controlla se il player può muoversi in quella tile, in caso affermativo cambia le coordinate e manda una notifica agli osservatori
     * se non si sta muovendo si dice agli osservatori che il giocatore si sta muovendo.
     */
    //TODO invece che usare +1 e -1 salvare tale valore in un campo speed, in modo da poterla aumentare con i power up
    @Override
    public void update() {
        if (moving) {
            switch (action) {
                case "LEFT" -> {
                    if ((hitbox.checkCollision(hitbox.x - speed, hitbox.y) && hitbox.checkCollision(hitbox.x - speed, hitbox.y + hitbox.h - 1))) {
                        x -= speed;
                        hitbox.update(-speed, 0);
                        if (bombs.size() == 0 || !intersect( "LEFT"))
                            sendMessage(action);
                        else{
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
                    if (hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h -1 )) {
                        x += speed;
                        hitbox.update(speed, 0);
                        if (bombs.size() == 0 || !intersect("RIGHT"))
                            sendMessage(action);
                        else{
                            x -= speed;
                            hitbox.update(-speed, 0);
                            sendMessage("STAY");
                        }
                    }else {
                        moving = false;
                        sendMessage("STAY");
                    }
                }
                case "UP" -> {
                    if(hitbox.checkCollision(hitbox.x, hitbox.y - speed) && hitbox.checkCollision(hitbox.x + hitbox.w -1, hitbox.y- speed)){
                        y -= speed;
                        hitbox.update(0, -speed);
                        if (bombs.size() == 0 || !intersect( "UP"))
                            sendMessage(action);
                        else{
                            y += speed;
                            hitbox.update(0, +speed);
                            sendMessage("STAY");
                        }
                    }else {
                        moving = false;
                        sendMessage("STAY");
                    }
                }
                case "DOWN" -> {
                    if ( hitbox.checkCollision(hitbox.x, hitbox.y  + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h )){
                        y += speed;
                        hitbox.update(0, speed);
                        if (bombs.size() == 0 || !intersect( "DOWN"))
                            sendMessage(action);
                        else{
                            y -= speed;
                            hitbox.update(0, -speed);
                            sendMessage("STAY");
                        }
                    }else {
                        moving = false;
                        sendMessage("STAY");
                    }
                }
                default -> sendMessage("STAY");
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
        for (int x = 0; x < bombs.size(); x++){
            bombs.get(x).update();
        }

        if (immortality){
            immortalityTick++;
            if (immortalityTick >= 600){
                immortality = false;
            }
        }
    }

    private boolean intersect(String dir) {
        for (Bomb b : bombs){
            if (b.intersect(dir)){
                return true;
            }
        }
        return false;
    }

    /**
     * controllo inizialmente se il numero di bombe in gioco è minore del numero massimo di bombe consentito.
     * se ciò è vero creo una nuova bomba e mando una notifica all'observer
     */
    private void spawnBomb() {
        if (Bomb.BOMB_COUNTER < MAX_BOMB_NUMS) {
            for (Bomb b : bombs){
                if (b.getX() == (x/16)*16 && b.getY() == ((y+8)/16)*16){
                    return;
                }
            }
            bomb = new Bomb(this,x/16, (y+8)/16);

            bombs.add(bomb);

            sendMessage("BOMB");
            action = "STAY";
        }
    }

    /**
     * crea l'esplosione
     * @param b
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
     * @param b
     */
    public void removeBomb(Bomb b) {
        b.setExploding(false);
        bombs.remove(b);
        String m1 = String.valueOf(b.getX());
        String m2 = String.valueOf(b.getY());
        sendMessage(new String[] {m1, m2});
    }

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
        action = "STAY";
        HP = 1;
        hitbox.x = x;
        hitbox.y = y + 8;
        bombs.clear();
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
    }

    public void addSpeed(int val) {
        speed += val;
        sendMessage("SPEED");
    }

    public void hit(){
        HP--;
        if (HP == 0){
            alive = false;
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
}
