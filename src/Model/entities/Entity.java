package Model.entities;

import java.util.Observable;

public abstract class Entity extends Observable {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}
