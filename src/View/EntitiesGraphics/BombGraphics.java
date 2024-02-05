package View.EntitiesGraphics;

import View.AudioPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * manages the graphical representation of a bomb
 * @see View.EntitiesGraphics.EntityGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class BombGraphics extends EntityGraphics {
    private BufferedImage[] imgs, explosionImgs;
    private int animationIndex;
    private int numFrames;
    private final int animationSpeed = 12;
    private boolean exploding;
    private final boolean canDraw = true;
    private int[][] explosion;
    private final AudioPlayer audioPlayer;

    /**
     * Class constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     */
    public BombGraphics(int x, int y){
        super (x*16, y*16, 16, 16);
        audioPlayer = new AudioPlayer();
        loadAnimations();
    }


    /**
     * Loads all images involved in the bomb and explosion animation
     */
    @Override
    public void loadAnimations(){
        BufferedImage temp = loadImg("/Imgs/entitySprites/bombSprites/bomb.png");
        BufferedImage temp1 = loadImg("/Imgs/provaSpritesTemp.png");

        imgs = new BufferedImage[3];
        imgs[0] = temp.getSubimage(0, 0, 16, 16);
        imgs[1] = temp.getSubimage(16, 0, 16, 16);
        imgs[2] = temp.getSubimage(32, 0, 16, 16);

        explosionImgs = new BufferedImage[5];
        explosionImgs[0] = temp1.getSubimage(4*16 + 5, 5*16 + 5, 16, 16);
        explosionImgs[1] = temp1.getSubimage(7*16 + 8, 4*16 + 4, 16, 16);
        explosionImgs[2] = temp1.getSubimage(6*16 + 7, 7*16 + 7, 16, 16);
        explosionImgs[3] = temp1.getSubimage(3*16 + 4, 3*16 + 3, 16, 16);
        explosionImgs[4] = temp1.getSubimage(16 + 2, 6*16 + 6, 16, 16);
    }

    /**
     * Update the animation
     */
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

    /**
     * Draw the Bomb class instance on the screen
     * @param g: instance of the Graphics class
     */
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
    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }
    public void setExplosion(int[][] explosion) {
        this.explosion = explosion;
    }

    public void playExplosion(){
        audioPlayer.playEffects(2);
    }

    public void playSpawn(){
        audioPlayer.playEffects(3);
    }

    /**
     * Draws the Bomb class instance, but disables its updating
     * @param g: an instance of the Graphics class
     */
    public void freeze(Graphics g) {
        if (!exploding) {
            g.drawImage(imgs[animationIndex], x * 3, (y) * 3, w * 3, h * 3, null);
        } else {
            for (int c = 0; c < explosion.length; c++){

                int tx = explosion[c][0];
                int ty = explosion[c][1];

                if (ty == 0 && tx == 0){
                    continue;
                }
                g.drawImage(explosionImgs[c], tx*3, ty*3, w*3, h*3, null);
            }
        }
    }
}
