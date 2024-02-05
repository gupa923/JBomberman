package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class manages the graphical representation of obstacles. There are two types of obstacles available
 * @see View.EntitiesGraphics.EntityGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class ObstacleGraphics extends EntityGraphics {
    private final BufferedImage sprite;
    private final BufferedImage explosionSprite;
    private BufferedImage t1Sprite;
    private boolean exploading = false;
    private BufferedImage[] sprites, explosionSprites, t1ExplosionSprites;
    private int animationIndex, animationTick, animatioSpeed = 20;
    private final int type;

    /**
     * Class constructor
     * @param x: x coordinate of the obstacle
     * @param y: y coordinate of the obstacle
     * @param type: the type of obstacle
     */
    public ObstacleGraphics(int x, int y, int type){
        super(x, y, 16, 16);
        this.type = type;
        sprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Ostacolo.png");
        explosionSprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Esplosione_Ostacolo.png");
        loadAnimations();
    }

    /**
     * Draw an instance of the ObstacleGraphics class on the screen
     * @param g:instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (type == 0) {
            if (!exploading)
                g.drawImage(sprites[animationIndex], x * 3, y * 3, h * 3, w * 3, null);
            else {
                g.drawImage(explosionSprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            }
        }else {
            if (!exploading){
                g.drawImage(t1Sprite, x*3, y*3, w*3, h*3, null);
            }else{
                g.drawImage(t1ExplosionSprites[animationIndex], x*3, y*3, w*3, h*3, null);
            }
        }
    }

    /**
     * Loads all images involved in the obstacle animation. Obstacle death animations are also loaded
     */
    @Override
    public void loadAnimations() {
        sprites = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            sprites[i] = sprite.getSubimage(i*16, 0, 16, 16);
        }
        explosionSprites = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            explosionSprites[i] = explosionSprite.getSubimage(i*16, 0, 16,16);
        }
        t1Sprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Tronco.png").getSubimage(19, 0, 16, 16);
        t1ExplosionSprites = new BufferedImage[6];
        BufferedImage t = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Buco.png");
        for (int i = 0; i < 6; i++) {
            t1ExplosionSprites[i] = t.getSubimage(i*16+ i, 0, 16,16);
        }
    }

    /**
     * Update the obstacle animation
     */
    @Override
    public void updateAnimation() {
        if (!exploading){
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= sprites.length){
                animationIndex = 0;
            }
        }else{
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= explosionSprites.length){
                animationIndex = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * This method causes the obstacle to explode
     * @param exploading: true if the obstacle should explode
     */
    public void setExploading(boolean exploading) {
        this.exploading = exploading;
        animatioSpeed = 11;
        animationTick = 0;
        animationIndex = 0;
    }

    /**
     * Causes obstacles to be drawn, but prevents them from updating
     * @param g: instance of the Graphics class
     */
    public void freeze(Graphics g) {
        if (type == 0) {
            if (!exploading)
                g.drawImage(sprites[animationIndex], x * 3, y * 3, h * 3, w * 3, null);
            else {
                g.drawImage(explosionSprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            }
        }else {
            if (!exploading){
                g.drawImage(t1Sprite, x*3, y*3, w*3, h*3, null);
            }else{
                g.drawImage(t1ExplosionSprites[animationIndex], x*3, y*3, w*3, h*3, null);
            }
        }
    }

}
