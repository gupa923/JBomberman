package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.Observable;

public class ClownBossGraphics extends EnemyGraphics{
    private BufferedImage temp;
    private boolean moving = true;
    private int typeAnimation;

    public ClownBossGraphics(int x, int y, int w, int h) {
        super(x-47, y-44, w, h);
        sx = x-47;
        sy = y-44;
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        temp = loadImg("/Imgs/entitySprites/enemySprite/World 2 Boss.png").getSubimage(0, 0, 110, 105);
    }

    @Override
    public void updateAnimation() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(temp, x*3, y*3, w*3, h*3, null);
        g.drawRect(hitbox.x*3, hitbox.y*3,hitbox.w*3, hitbox.h*3 );
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String dir = (String) arg;
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
        if (obj instanceof ClownBossGraphics){
            ClownBossGraphics r = (ClownBossGraphics) obj;
            return r.x == x && r.y == y;
        }
        return false;
    }
}
