package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the BrownEnemy
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class BrownEnemyGraphics extends EnemyGraphics{
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
     * @param w: the width of the BrownEnemyGraphics
     * @param h: the height of the BrownEnemyGraphics
     */
    public BrownEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Loads the images involved in the animations
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/brown_enemy/Nemico_Marrone_DOWN.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/brown_enemy/Nemico_Marrone_UP.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/brown_enemy/Nemico_Marrone_SX.png");
        BufferedImage temp4 = loadImg("/Imgs/entitySprites/enemySprite/brown_enemy/Nemico_Marrone_DX.png");

        BufferedImage[] down = new BufferedImage[9];
        for (int i = 0; i < 9; i++){
            down[i] = temp.getSubimage(i*16, 0, 16, 22);

        }

        BufferedImage[] dx = new BufferedImage[7];
        for (int i = 0; i < 7; i++){
            dx[i] = temp4.getSubimage(i*16, 0, 16, 22);

        }

        BufferedImage[] sx = new BufferedImage[7];
        for (int i = 0; i < 7; i++){
            sx[i] = temp3.getSubimage(i*16, 0, 16, 22);

        }

        BufferedImage[] up = new BufferedImage[9];
        for (int i = 0; i < 9; i++){
            up[i] = temp2.getSubimage(i*16, 0, 16, 22);

        }

        sprites = new BufferedImage[][] {down, up, sx, dx};
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
     * Draw an instance of this class on the screen
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
     * Draw an instance of this class on the screen, stopping the animation
     * @param g: instance of the Graphics class
     */
    public void freeze(Graphics g) {
        g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Updates the state of this class based on notifications received from the Observable
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
                    typeAnimation = 2;
                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;
                    typeAnimation = 3;
                }
                case "UP" -> {
                    moving = true;
                    y -= 1;
                    typeAnimation = 1;
                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;
                    typeAnimation = 0;
                }
                case "STAY" -> {
                    typeAnimation = 0;
                    moving = false;
                }
                case "DYING" -> {
                    death = true;
                    audioPlayer.playEffects(1);
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BrownEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
