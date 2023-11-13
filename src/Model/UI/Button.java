package Model.UI;

import Model.StateModels.Stato;

import java.awt.geom.Rectangle2D;

public class Button {
    private int x, y, w, h;
    private boolean mousePressed;
    private Stato stato;
    private String text;
    private Rectangle2D.Float bounds;

    public Button(int x, int y, int w, int h,String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        bounds = new Rectangle2D.Float(this.x, this.y, this.w, this.h);
    }

    public void update(){
        if (mousePressed){
            stato.sendMessage(text + " PRESSED");
        } else {
            stato.sendMessage("NOT PRESSED");
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
}
