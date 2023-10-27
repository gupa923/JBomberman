package View.EntitiesGraphics;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * gestisce la rappresentazione grafica di una bomba
 */
public class BombGraphics implements ImgImporter, Drawable {
    private int x, y, w, h;
    private BufferedImage[] imgs;
    private int animationIndex;
    private int numFrames;
    private int animationSpeed = 12;


    public BombGraphics(int x, int y){
        this.x = x*16;
        this.y = y*16;
        w = h = 16;
        importImgs();
    }

    private void importImgs(){
        BufferedImage temp = loadImg("/bomb.png");

        imgs = new BufferedImage[3];
        imgs[0] = temp.getSubimage(0, 0, 16, 16);
        imgs[1] = temp.getSubimage(16, 0, 16, 16);
        imgs[2] = temp.getSubimage(32, 0, 16, 16);
    }

    public void updateAnimations(){
        numFrames++;
        if (numFrames >= animationSpeed){
            animationIndex++;
            numFrames = 0;
            if (animationIndex >= imgs.length){
                animationIndex = 0;
            }
        }
    }
    @Override
    public void draw(Graphics g){
        updateAnimations();
        g.drawImage(imgs[animationIndex], x*3, (y)*3, w*3, h*3, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
