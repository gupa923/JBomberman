package Model;

public class Player extends Entity{
    private int speed= 1;
    private String direction;
    private boolean moving;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
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
                    if (y+speed+height<= 208)
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
