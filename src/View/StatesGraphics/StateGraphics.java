package View.StatesGraphics;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.util.Observer;

public abstract class StateGraphics implements Drawable, Observer, ImgImporter {

    private Boolean active;


    public boolean isActive(){return active;}

    public void setActive(Boolean active) {
        this.active = active;
    }
}
