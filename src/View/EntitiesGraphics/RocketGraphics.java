package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RocketGraphics extends EntityGraphics {
    private BufferedImage temp, tempFlip;
    private BufferedImage[][] sprites;
    int dir;
    public RocketGraphics(int x, int y, int w, int h, int dir) {
        super(x, y, w, h);
        this.dir = dir;
        loadAnimations();
    }

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

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprites[dir][0], x*3, y*3, w*3, h*3, null);
    }


    public void moveRocket() {
        if (dir == 0){
            x -= 3;
        }else if (dir == 1){
            x += 3;
        }else if (dir == 2){
            y -= 3;
        }else if (dir == 3){
            y += 3;
        }
    }
}
