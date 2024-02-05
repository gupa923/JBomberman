package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class manages the graphical representation of rockets
 * @see View.EntitiesGraphics.EntityGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class RocketGraphics extends EntityGraphics {
    private BufferedImage temp, tempFlip;
    private BufferedImage[][] sprites;
    int dir;

    /**
     * Class Constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the rocket
     * @param h: the height of the rocket
     * @param dir: the direction in which the rocket is moving
     */
    public RocketGraphics(int x, int y, int w, int h, int dir) {
        super(x, y, w, h);
        this.dir = dir;
        loadAnimations();
    }

    /**
     * Load images of the rocket class
     */
    @Override
    public void loadAnimations() {
        temp = loadImg("/Imgs/entitySprites/enemySprite/Boss2.png");
        tempFlip = loadImg("/Imgs/entitySprites/enemySprite/Boss2Flip.png");
        sprites = new BufferedImage[4][1];
        sprites[0][0] = temp.getSubimage(246,24, 32,22);
        sprites[1][0] = tempFlip.getSubimage(92,24, 32,22);
        sprites[3][0] = temp.getSubimage(300,16, 20,32);
        sprites[2][0] = temp.getSubimage(342,16, 20,32);
    }

    @Override
    public void updateAnimation() {

    }

    /**
     * Draw an instance of the RocketGraphics class on the screen
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(sprites[dir][0], x * 3, y * 3, 32 * 3, 22 * 3, null);
        }else if (dir == 1){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 32 * 3, 22 * 3, null);
        }else if (dir == 2){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 20 * 3, 32 * 3, null);
        }else if (dir == 3){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 20 * 3, 32 * 3, null);
        }
    }


    /**
     * Update the location of the instance of this class
     */
    public void moveRocket() {
        if (dir == 0){
            x -= 2;
        }else if (dir == 1){
            x += 2;
        }else if (dir == 2){
            y -= 2;
        }else if (dir == 3){
            y += 2;
        }
    }
}
