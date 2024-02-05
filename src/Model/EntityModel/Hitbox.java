package Model.EntityModel;

import Model.Level;

/**
 * The Hitbox is a class that is associated with each entity in the game and manages collisions with the map
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Hitbox {
    public int x, y, w, h;
    private Level level;
    private int[][] data;
    private boolean walkOver;

    /**
     * Class Constructor
     * @param x: the x of the hitbox
     * @param y: the y of the hitbox
     * @param w: the width of the hitbox
     * @param h: the length of the hitbox
     */
    public Hitbox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Update hitbox position
     * @param dx: the value of how much to increase the x of the hitbox
     * @param dy: the value of how much to increase the y of the hitbox
     */
    public void update(int dx, int dy){
        x += dx;
        y += dy;
    }

    /**
     * this method handles collisions with map walls and obstacles
     * @param x: x coordinate of the player
     * @param y: y coordinate of the player
     * @return boolean: returns true if the player can move x, y
     */
    public boolean checkCollision(int x, int y){
        int nx = x / 16;
        int ny = y / 16;

        if (data[ny][nx] == 1){
            return false;
        }
        else if (data[ny][nx] == 3 || data[ny][nx] == 2 || data[ny][nx] == 4){
            if (walkOver)
                return true;
            for (Obstacle o: level.getObstacles()){
                if (o.getX() == nx*16 && o.getY() == ny*16)
                        return false;
            }
            return true;
        }else{
            return true;
        }
    }

    public int[][] getData() {
        return data;
    }

    public void setLevel(Level level) {
        this.level = level;
        data = this.level.getData();
    }

    public Level getLevel() {
        return level;
    }

    public void setWalkOver(boolean walkOver) {
        this.walkOver = walkOver;
    }
}
