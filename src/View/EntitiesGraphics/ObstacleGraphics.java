package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObstacleGraphics implements Drawable, ImgImporter, Animatable {
    private int x, y, w, h;
    private BufferedImage sprite;

    public ObstacleGraphics(int x, int y){
        this.x = x;
        this.y = y;
        w = h = 16;
        BufferedImage temp = loadImg("/provaSpritesTemp.png");
        sprite = temp.getSubimage(69, 0, 16, 16);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, x*3, y*3, 16*3, 16*3, null);
    }

    @Override
    public void loadAnimations() {

    }

    @Override
    public void updateAnimation() {

    }
}
