package Model;

import java.util.Observable;

/**
 * questa è la superclasse di tutti i modelli delle entita del gioco, cioè i powerup, gli ostacoli distruttibili,
 * la bomba, le esplosioni, il giocatore, i nemici e i boss.
 * qui vengono salvati i dati comuni a tutte le entità cioè le coordinate e i colpi necessari per distruggere
 * l'entita stessa
 *
 * @see java.util.Observable
 * @author gupa9
 */
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
