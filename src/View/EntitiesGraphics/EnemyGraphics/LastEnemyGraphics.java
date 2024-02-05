package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the LastEnemy
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class LastEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation;
    private int animationIndexUpdate;
    private int animationIndex;
    private final int animationSpeed = 10;
    private boolean moving = true;

    /**
     *
     *Class constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the FinalBossGraphics
     * @param h: the height of the FinalBossGraphics
     */
    public LastEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Loads all images involved in the animations
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde_DX.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde_SX.png");

        BufferedImage[] up, down, sx, dx;
        up = new BufferedImage[3];
        up[0] = temp.getSubimage(49, 0, 16, 22);
        up[1] = temp.getSubimage(65, 0, 16, 22);
        up[2] = temp.getSubimage(81, 0, 16, 22);

        down = new BufferedImage[3];
        down[0] = temp.getSubimage(16, 0, 16, 22);
        down[1] = temp.getSubimage(0, 0, 16, 22);
        down[2] = temp.getSubimage(112, 0, 16, 22);

        sx = new BufferedImage[3];
        for (int i = 0; i < 3;i++){
            sx[i] = temp3.getSubimage(16 + i*16, 0, 16, 22);
        }
        dx = new BufferedImage[3];
        dx[0] = temp2.getSubimage(0, 0, 16, 22);
        dx[1] = temp2.getSubimage(15, 0, 16, 22);
        dx[2] = temp2.getSubimage(30, 0, 16, 22);

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
     * Draw an instance of this class
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
     * Draw an instance of this class, without updating its animation
     * @param g:instance of the Graphics class
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
        if (arg instanceof String dir) {
            switch (dir) {
                case "LEFT" -> {
                    moving = true;
                    x -= 1;
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
        if (obj instanceof LastEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
