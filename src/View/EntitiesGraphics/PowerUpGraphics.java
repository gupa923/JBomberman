package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUpGraphics extends EntityGraphics{
    private int id;
    private BufferedImage[][] imgs;
    private int animationTick, animationIndex, maxTick = 20;

    public PowerUpGraphics(int x, int y, int id) {
        super(x, y, 16, 16);
        this.id= id;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = loadImg("/power_up.png");
        imgs = new BufferedImage[8][2];
        imgs[0][0] = temp.getSubimage(0*16,0*16, 16, 16);
        imgs[0][1] = temp.getSubimage(0*16,1*16, 16, 16);
        imgs[1][0] = temp.getSubimage(6*16, 2*16, 16, 16);
        imgs[1][1] = temp.getSubimage(6*16, 3*16, 16, 16);
        imgs[2][0] = temp.getSubimage(1*16, 4*16, 16, 16);
        imgs[2][1] = temp.getSubimage(1*16, 5*16, 16, 16);
        imgs[3][0] = temp.getSubimage(0*16, 4*16, 16, 16);
        imgs[3][1] = temp.getSubimage(0*16, 5*16, 16, 16);
        imgs[4][0] = temp.getSubimage(5*16, 0*16, 16, 16);
        imgs[4][1] = temp.getSubimage(5*16, 1*16, 16, 16);
        imgs[5][0] = temp.getSubimage(6*16, 0*16, 16, 16);
        imgs[5][1] = temp.getSubimage(6*16, 1*16, 16, 16);
        imgs[6][0] = temp.getSubimage(0*16, 2*16, 16, 16);
        imgs[6][1] = temp.getSubimage(0*16, 3*16, 16, 16);
        imgs[7][0] = temp.getSubimage(1*16, 2*16, 16, 16);
        imgs[7][1] = temp.getSubimage(1*16,3*16, 16, 16);
    }

    @Override
    public void loadAnimations() {

    }

    @Override
    public void updateAnimation() {
        animationTick++;
        if (animationTick >= maxTick) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= 2)
                animationIndex = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        g.drawImage(imgs[id][animationIndex], x*3, y*3, w*3, h*3, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PowerUpGraphics){
            PowerUpGraphics op = (PowerUpGraphics) obj;
            return this.x == op.x && this.y == op.y && this.id == op.id;
        }
        return false;
    }
}
