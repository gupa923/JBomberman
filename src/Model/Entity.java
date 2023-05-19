package Model;

import java.util.Observable;

public abstract class Entity extends Observable {
    protected int x, y, width, height;
    protected int life;


    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
