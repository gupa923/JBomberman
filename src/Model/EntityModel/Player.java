package Model.EntityModel;

import Model.EntityModel.Entity;
import Model.Hitbox;

/**
 *
 *
 * @see Entity
 * @author gupa9
 */
//TODO improve the movement system fa veramente schifo. Forse si può risolvere diminuendo la hitbox.
//TODO istanziare una nuova hitbox chiamata damageBox per quando gestiremo il danno.
public class Player extends Entity{
    private String direction = "STAY";
    private Bomb bomb;
    private int speed = 1;
    private boolean moving;
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
     * questo metodo update permette di controllare il numero di notifiche mandate agli observer
     * il metodo controlla se il campo moving è true, quindi se il player si sta muovendo, poi in base al
     * valore del campo direction, in primo luogo controlla se il player può muoversi in quella tile, in caso affermativo cambia le coordinate e manda una notifica agli osservatori
     * se non si sta muovendo si dice agli osservatori che il giocatore si sta muovendo.
     */
    //TODO invece che usare +1 e -1 salvare tale valore in un campo speed, in modo da poterla aumentare con i power up
    @Override
    public void update() {
        if (moving) {
            switch (direction) {
                case "LEFT" -> {
                    if ((hitbox.checkCollision(hitbox.x - speed, hitbox.y) && hitbox.checkCollision(hitbox.x - speed, hitbox.y + hitbox.h - 1))) {
                        x -= speed;
                        hitbox.update(-speed, 0);
                        sendMessage(direction);
                    } else {
                        sendMessage("STAY");
                    }
                }
                case "RIGHT" -> {
                    if (hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h -1 )) {
                        x += speed;
                        hitbox.update(speed, 0);
                        sendMessage(direction);
                    }else {
                        sendMessage("STAY");
                    }
                }
                case "UP" -> {
                    if(hitbox.checkCollision(hitbox.x, hitbox.y - speed) && hitbox.checkCollision(hitbox.x + hitbox.w -1, hitbox.y- speed)){
                        y -= speed;
                        hitbox.update(0, -speed);
                        sendMessage(direction);
                    }else {
                        sendMessage("STAY");
                    }
                }
                case "DOWN" -> {
                    if ( hitbox.checkCollision(hitbox.x, hitbox.y  + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h )){
                        y += speed;
                        hitbox.update(0, speed);
                        sendMessage(direction);
                    }else {
                        sendMessage("STAY");
                    }
                }
                default -> sendMessage("STAY");
            }
        }else{
            switch (direction){
                case "BOMB" -> {
                    spawnBomb();
                }
                default -> {
                    sendMessage("STAY");

                }
            }
        }
    }

    private void spawnBomb() {
        bomb = new Bomb(x, y);
        sendMessage("BOMB");
    }


    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}