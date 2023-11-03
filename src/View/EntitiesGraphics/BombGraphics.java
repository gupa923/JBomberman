package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * gestisce la rappresentazione grafica di una bomba
 */
public class BombGraphics implements ImgImporter, Drawable, Animatable {
    private int x, y, w, h;
    private BufferedImage[] imgs;
    private int animationIndex;
    private int numFrames;
    private int animationSpeed = 12;
    private boolean exploding;
    private boolean canDraw = true;
    private int[][] explosion;

    public BombGraphics(int x, int y){
        this.x = x*16;
        this.y = y*16;
        w = h = 16;
        loadAnimations();
    }


    @Override
    public void loadAnimations(){
        BufferedImage temp = loadImg("/playerSprites/bomb.png");

        imgs = new BufferedImage[3];
        imgs[0] = temp.getSubimage(0, 0, 16, 16);
        imgs[1] = temp.getSubimage(16, 0, 16, 16);
        imgs[2] = temp.getSubimage(32, 0, 16, 16);
    }

    @Override
    public void updateAnimation() {
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
        if (canDraw) {
            if (!exploding) {
                updateAnimation();
                g.drawImage(imgs[animationIndex], x * 3, (y) * 3, w * 3, h * 3, null);
            } else {
                for (int c = 0; c < explosion.length; c++){

                    int tx = explosion[c][0];
                    int ty = explosion[c][1];
                    g.setColor(Color.RED);
                    g.fillRect(tx *3, ty *3, w*3, h*3);
                }
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isExploding() {
        return exploding;
    }

    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }

    public boolean isCanDraw() {
        return canDraw;
    }

    public void setExplosion(int[][] explosion) {
        this.explosion = explosion;
    }
}
