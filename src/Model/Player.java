package Model;

/**
 * questa classe è il modello del player. Estende la classe Entity.
 * Oltre agli attributi della superclasse ha come campi speed, cioè la velocità di
 * movimento, direction che indica la direzione in cui il player si sposta, mooving che è un boolean che è true
 * quando il Player si sta muovendo.
 * @see java.util.Observable
 * @see Model.Entity
 * @author gupa9
 */
public class Player extends Entity{
    private int speed= 1;
    private String direction = "STAY";
    private boolean moving;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * se la variabile moving è vera il Player si muoverà in base alla direzione contenuta nella variabile
     * direction e in seguito manderà una notifica agli osservatori.
     * questo metodo viene chiamato nella classe GameModel in modo così da controllare il numero di notifiche mandate agli osservatori.
     *
     *
     */
    public void updatePos(){

        if (moving){
            switch (direction){
                case "LEFT" -> {
                    if (x-speed>= 0)
                    {
                        x -= speed;
                        sendMessage(direction);
                    }
                }
                case "RIGHT" ->{
                    if (x+speed+width <= 272)
                    {
                        x += speed;
                        sendMessage(direction);
                    }
                }
                case "UP" -> {
                    if (y-speed>= 0)
                    {
                        y -= speed;
                        sendMessage(direction);
                    }
                }
                case "DOWN" -> {
                    if (y+speed+height<= 208)
                    {
                        y += speed;
                        sendMessage(direction);
                    }
                }

            }
        } else {
            if (direction.equals("STAY"))
                sendMessage(direction);
        }
    }

    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
