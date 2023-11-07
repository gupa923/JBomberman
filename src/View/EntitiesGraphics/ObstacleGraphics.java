package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObstacleGraphics extends EntityGraphics {
    private BufferedImage sprite, explosionSprite;
    private boolean exploading = false;

    public ObstacleGraphics(int x, int y){
        super(x, y, 16, 16);
        BufferedImage temp = loadImg("/provaSpritesTemp.png");
        sprite = temp.getSubimage(69, 0, 16, 16);
        explosionSprite = temp.getSubimage(1, 16*8 + 8, 16, 16 );
    }

    @Override
    public void draw(Graphics g) {
        if(!exploading)
            g.drawImage(sprite, x*3, y*3, h*3, w*3, null);
        else{
            g.drawImage(explosionSprite, x*3, y*3, w*3, h*3, null);
        }
    }

    @Override
    public void loadAnimations() {

    }

    @Override
    public void updateAnimation() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setExploading(boolean exploading) {
        this.exploading = exploading;
    }
}
