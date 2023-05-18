package Model;

import java.util.Observable;

public class Prova extends Observable {
    private int x, y, width,heigth;
    private boolean moving;
    private String direction;

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
                    x --;
                    sendMessage(direction);
                }
                case "RIGHT" ->{
                    x++;
                    sendMessage(direction);
                }
                case "UP" -> {
                    y--;
                    sendMessage(direction);
                }
                case "DOWN" -> {
                    y++;
                    sendMessage(direction);
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
