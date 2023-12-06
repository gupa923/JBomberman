package View.EntitiesGraphics;


import java.awt.*;
import java.awt.image.BufferedImage;

public class RedEnemyGraphics extends EnemyGraphics {
    private BufferedImage sprite;
    public RedEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/entitySprites/enemySprite/Nemico_Rosso_Down.png");

        sprite = temp.getSubimage(0, 0, 16, 24);
    }

    @Override
    public void updateAnimation() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, x*3, y*3, w*3, h*3, null);
    }
}
