package Model.UI;

import Model.StateModels.Stato;

import java.awt.geom.Rectangle2D;

/**
 * Questa classe gestisce i dati di un bottone quindi la sua posizione, il testo contenuto e se viene premuto o meno
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Button {
    public int x, y, w, h;
    private boolean mousePressed;
    private Stato stato;
    private final String text;
    private final Rectangle2D.Float bounds;

    /**
     * Costruttore della classe
     * @param x: la coordinata x del bottone
     * @param y: la coordinata y del bottone
     * @param w: la larghezza del bottone
     * @param h: l'altezza del bottone
     * @param text: il testo contenuto dal bottone
     */
    public Button(int x, int y, int w, int h,String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        bounds = new Rectangle2D.Float(this.x, this.y, this.w, this.h);
    }

    /**
     * Aggiorna lo stato del bottone
     */
    public void update(){
        if (mousePressed){
            stato.sendMessage(text + " PRESSED");
        }
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Rectangle2D.Float getBounds() {
        return bounds;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
