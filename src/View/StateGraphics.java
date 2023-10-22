package View;

import java.awt.*;

public abstract class StateGraphics {

    private Boolean active;

    public abstract void draw(Graphics g);

    public boolean isActive(){return active;}

    public void setActive(Boolean active) {
        this.active = active;
    }
}
