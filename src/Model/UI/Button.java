package Model.UI;

import Model.StateModels.Stato;

import java.awt.geom.Rectangle2D;

/**
 * This class manages the data of a button, therefore its position, the text contained and whether it is pressed or not
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Button {
    public int x, y, w, h;
    private boolean mousePressed;
    private Stato stato;
    private final String text;
    private final Rectangle2D.Float bounds;

    /**
     * Class Constructor
     * @param x: the x coordinate of the button
     * @param y: the y coordinate of the button
     * @param w: the width of the button
     * @param h: the height of the button
     * @param text: the text contained by the button
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
     * Update the button state
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
