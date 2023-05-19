package GarbageCollector;

import java.util.Observable;

/**
 *
 * le classi in GarbageCollector sono classi di prova che non devono restare nel progetto
 * una volta terminato.
 *
 *
 *
 */

public class Prova extends Observable {
    private int x, y, width,heigth;
    private boolean moving;
    private String direction;
    private int speed = 1;

    public Prova(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
    }

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
                    if (y+speed+heigth<= 208)
                    {
                        y += speed;
                        sendMessage(direction);
                    }
                }
            }
        }
    }

    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
