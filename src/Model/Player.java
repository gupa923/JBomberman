package Model;

/**
 *
 *
 * @see Entity
 * @author gupa9
 */
//TODO improve the movement system fa veramente schifo. Forse si può risolvere diminuendo la hitbox.
//TODO istanziare una nuova hitbox chiamata damageBox per quando gestiremo il danno.
public class Player extends Entity{
    private String direction;
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
        hitbox = new Hitbox(x, y + 8, 16, 16);
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
                    if ((hitbox.checkCollision(hitbox.x - 1, hitbox.y) && hitbox.checkCollision(hitbox.x -1, hitbox.y + hitbox.h - 1))) {
                        x -= 1;
                        hitbox.update(-1, 0);
                        sendMessage(direction);
                    }
                }
                case "RIGHT" -> {
                    if (hitbox.checkCollision(hitbox.x + w, hitbox.y) && hitbox.checkCollision(hitbox.x + hitbox.w, hitbox.y + hitbox.h -1 )) {
                        x += 1;
                        hitbox.update(1, 0);
                        sendMessage(direction);
                    }
                }
                case "UP" -> {
                    if(hitbox.checkCollision(hitbox.x, hitbox.y - 1) && hitbox.checkCollision(hitbox.x + hitbox.w -1, hitbox.y- 1)){
                        y -= 1;
                        hitbox.update(0, -1);
                        sendMessage(direction);
                    }
                }
                case "DOWN" -> {
                    if ( hitbox.checkCollision(hitbox.x, hitbox.y  + hitbox.h) && hitbox.checkCollision(hitbox.x + hitbox.w - 1, hitbox.y + hitbox.h )){
                        y += 1;
                        hitbox.update(0, 1);
                        sendMessage(direction);
                    }
                }
            }
        }else{
            sendMessage("STAY");
        }
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
