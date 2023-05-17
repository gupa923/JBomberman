package Model.entities;

public class Player extends Entity{
    private int playerSpeed = 3;
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void changeX(int direction){
        if (direction == -1){
            x -= playerSpeed;
            notifyObservers("go Left");
        }
        else if (direction == 1){
            x += playerSpeed;
            notifyObservers("go Right");
        }
    }

    public void changeY(int direction){
        if (direction == -1){
            y -= playerSpeed;
            notifyObservers("go Up");
        }
        else if (direction == 1){
            y += playerSpeed;
            notifyObservers("go Down");
        }
    }
}
