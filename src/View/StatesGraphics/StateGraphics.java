package View.StatesGraphics;

import View.UtilityInterfaces.Drawable;

public abstract class StateGraphics implements Drawable {

    private Boolean active;


    public boolean isActive(){return active;}

    public void setActive(Boolean active) {
        this.active = active;
    }
}
