package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * gestisce la rappresentazione grafica di una bomba
 */
public class BombGraphics extends EntityGraphics {
    private BufferedImage[] imgs, explosionImgs;
    private BufferedImage explosionImg;
    private int animationIndex;
    private int numFrames;
    private int animationSpeed = 12;
    private boolean exploding;
    private boolean canDraw = true;
    private int[][] explosion;

    public BombGraphics(int x, int y){
        super (x*16, y*16, 16, 16);
        loadAnimations();
    }


    @Override
    public void loadAnimations(){
        BufferedImage temp = loadImg("/entitySprites/bombSprites/bomb.png");
        explosionImg = loadImg("/entitySprites/bombSprites/esplosione0.png");
        BufferedImage temp1 = loadImg("/provaSpritesTemp.png");

        imgs = new BufferedImage[3];
        imgs[0] = temp.getSubimage(0, 0, 16, 16);
        imgs[1] = temp.getSubimage(16, 0, 16, 16);
        imgs[2] = temp.getSubimage(32, 0, 16, 16);

        explosionImgs = new BufferedImage[5];
        explosionImgs[0] = temp1.getSubimage(4*16 + 5, 5*16 + 5, 16, 16);
        explosionImgs[1] = temp1.getSubimage(7*16 + 8, 4*16 + 4, 16, 16);
        explosionImgs[2] = temp1.getSubimage(6*16 + 7, 7*16 + 7, 16, 16);
        explosionImgs[3] = temp1.getSubimage(3*16 + 4, 3*16 + 3, 16, 16);
        explosionImgs[4] = temp1.getSubimage(1*16 + 2, 6*16 + 6, 16, 16);
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

                    if (ty == 0 && tx == 0){
                        continue;
                    }
                    //g.setColor(Color.RED);
                    //g.fillRect(tx *3, ty *3, w*3, h*3);
                    g.drawImage(explosionImgs[c], tx*3, ty*3, w*3, h*3, null);
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
