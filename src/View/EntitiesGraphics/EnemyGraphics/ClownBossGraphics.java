package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.Observable;

public class ClownBossGraphics extends EnemyGraphics{
    private BufferedImage temp;
    private BufferedImage[] sprites;
    private boolean moving = true;
    private int typeAnimation;
    private Rectangle2D.Float damageBox;
    private int animationIndex;
    private int animationIndexUpdate;
    private int animationSpeed = 10;

    public ClownBossGraphics(int x, int y, int w, int h) {
        super(x-47, y-44, w, h);
        sx = x-47;
        sy = y-44;
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        temp = loadImg("/Imgs/entitySprites/enemySprite/World 2 Boss.png");
        sprites = new BufferedImage[6];
        sprites[0] = temp.getSubimage(220 + 2, 0, 110, 100);
        sprites[1] = temp.getSubimage(330+3, 0, 110, 100);
        sprites[2] = temp.getSubimage(110 + 1, 110, 110, 105);
        sprites[3] = temp.getSubimage(220 + 2, 100, 110, 105);
        sprites[4] = temp.getSubimage(330 + 3, 100, 110, 105);
        sprites[5] = temp.getSubimage( 0, 216, 110, 105);

    }

    @Override
    public void updateAnimation() {
        if (!moving) {
            animationIndex = 0;
            animationIndexUpdate = 0;
        }
        animationIndexUpdate++;
        if (animationIndexUpdate >= animationSpeed) {
            animationIndexUpdate = 0;
            animationIndex++;
            if (animationIndex >= sprites.length)
                animationIndex = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        g.drawImage(sprites[animationIndex], x*3, y*3, w*3, h*3, null);
        g.drawRect(hitbox.x*3, hitbox.y*3,hitbox.w*3, hitbox.h*3 );
        g.drawRect((int) (damageBox.x*3), (int) (damageBox.y*3), (int) (damageBox.width*3), (int) (damageBox.height*3));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String dir = (String) arg;
            switch (dir){
                case "LEFT" -> {
                    moving = true;
                    x-= 1;

                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;

                }
                case "UP" -> {
                    moving = true;
                    y -= 1;

                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;

                }
                case "STAY" -> {
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
        if (obj instanceof ClownBossGraphics){
            ClownBossGraphics r = (ClownBossGraphics) obj;
            return r.x == x && r.y == y;
        }
        return false;
    }

    public void setDamageBox(Rectangle2D.Float damageBox) {
        this.damageBox = damageBox;
    }
}
