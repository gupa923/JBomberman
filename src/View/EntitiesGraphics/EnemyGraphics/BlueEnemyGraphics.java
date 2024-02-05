package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the blue enemy
 * @see View.EntitiesGraphics.EnemyGraphics;
 * @author Guido Paluzzi, Matteo Santucci
 */
public class BlueEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation;
    private int animationIndexUpdate;
    private int animationIndex;
    private final int animationSpeed = 10;
    private boolean moving = true;

    /**
     * Class Constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the BlueEnemy
     * @param h: the height of the BlueEnemy
     */
    public BlueEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Loads all images involved in the animation of this class
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_UP.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_SX.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DX.png");
        BufferedImage temp4 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DOWN.png");

        BufferedImage[] up = new BufferedImage[4];
        for (int i = 0; i < 4;i++){
            up[i] = temp.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] sx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            sx[i] = temp2.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] dx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            dx[i] = temp3.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] down = new BufferedImage[10];
        for (int i = 0; i < 10;i++){
            down[i] = temp4.getSubimage(i*16 + i, 0, 16, 16);
        }

        sprites = new BufferedImage[][]{up, sx, dx, down};
    }

    /**
     * Update the state of the animations
     */
    @Override
    public void updateAnimation() {
        if (death){
            deathTick++;
            if (deathTick > deathSpeed){
                deathIndex ++;
                deathTick = 0;
                if (deathIndex >= 8){
                    deathIndex = 0;
                }
            }
        }else {
            if (!moving) {
                animationIndex = 0;
                animationIndexUpdate = 0;
            }
            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex++;
                if (animationIndex >= sprites[typeAnimation].length)
                    animationIndex = 0;
            }
        }
    }

    /**
     * Draw an instance of the BlueEnemyGraphics class on the screen
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (death){
            g.drawImage(deathAnimation[deathIndex], x*3, y*3, w*3, h*3, null);
        }
        else {
            g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
    }

    /**
     * Draw the BlueEnemyGraphics by freezing its animation
     * @param g: instance of the Graphics class
     */
    @Override
    public void freeze(Graphics g) {
        g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Updates the state of this class based on notifications sent by the Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String dir){
            switch (dir){
                case "LEFT" -> {
                    moving = true;
                    x-= 1;
                    typeAnimation = 1;
                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;
                    typeAnimation = 2;
                }
                case "UP" -> {
                    moving = true;
                    y -= 1;
                    typeAnimation = 0;
                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;
                    typeAnimation = 3;
                }
                case "STAY" -> {
                    typeAnimation = 0;
                    moving = false;
                }
                case "DYING" -> {
                    audioPlayer.playEffects(1);
                    death = true;
                }
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlueEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
