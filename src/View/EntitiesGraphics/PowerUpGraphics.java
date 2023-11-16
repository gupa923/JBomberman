package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUpGraphics extends EntityGraphics{
    private int id;
    private BufferedImage[] imgs;

    public PowerUpGraphics(int x, int y, int id) {
        super(x, y, 16, 16);
        this.id= id;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = loadImg("/power_up.png");
        imgs = new BufferedImage[2];
        imgs[0] = temp.getSubimage(0*16,1*16, 16, 16);
        imgs[1] = temp.getSubimage(6*16, 3*16, 16, 16);
    }

    @Override
    public void loadAnimations() {

    }

    @Override
    public void updateAnimation() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[id], x*3, y*3, w*3, h*3, null);
    }
}
