package Model.EntityModel;

import Model.Level;

/**
 * questa classe gestisce le collisioni con le tile non walkabili sulla mappa
 *
 * @author gupa9
 */
//TODO pensare ad un modo per vedere la collisione con un'altra hitbox.
//TODO creare una classe figlia damageBox per la collisione con i nemici e con le esplosioni.
public class Hitbox {
    public int x, y, w, h;
    private Level level;
    private int[][] data;
    private boolean walkOver;

    public Hitbox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * con questo metodo viene aggiornata la posizione della hitbox
     * @param dx
     * @param dy
     */
    public void update(int dx, int dy){
        x += dx;
        y += dy;
    }

    /**
     * avendo data che è una matrice dei interi che rappresenta le diverse tile presenti nella mappa.
     * se il valore è 1 vuol dire che non si può camminare su questa tile.
     * per calcolare ciò viene preso il valore di x e di y e viene effettuata la divisione intera per 16,
     * poi si controlla il valore di data alla posizione della nuova x e della nuova y e se il valore NON è 1
     * restituisce true, cioè che il giocatore può camminare in questa tile.
     *
     * @param x
     * @param y
     * @return boolean
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

    public void setData(int[][] data) {
        this.data = data;
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
