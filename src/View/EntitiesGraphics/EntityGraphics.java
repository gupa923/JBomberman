package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

public abstract class EntityGraphics implements ImgImporter, Drawable, Animatable {
    protected int x, y, w, h;

    public EntityGraphics(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
