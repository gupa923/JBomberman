package Model.EntityModel;

import Model.Level;

/**
 * L'Hitbox è una classe che viene associata ad ogni entità del gioco e gestisce le collisioni con la mappa
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Hitbox {
    public int x, y, w, h;
    private Level level;
    private int[][] data;
    private boolean walkOver;

    /**
     * Costruttore della classe
     * @param x: la x della hitbox
     * @param y: la y della hitbox
     * @param w: la larghezza della hitbox
     * @param h: la lunghezza della hitbox
     */
    public Hitbox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Aggiorna la posizione della hitbox
     * @param dx: il valore di quanto aumentare la x della hitbox
     * @param dy: il valore di quanto aumentare la y della hitbox
     */
    public void update(int dx, int dy){
        x += dx;
        y += dy;
    }

    /**
     * questo metodo gestisce la collisione con i muri della mappa e con gli ostacoli
     * @param x: coordinata x del player
     * @param y: coordinata y del player
     * @return boolean: restituisce true se il giocatore può spostarsi su x, y
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
