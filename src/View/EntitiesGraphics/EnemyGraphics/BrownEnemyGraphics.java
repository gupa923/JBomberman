package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class BrownEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation, animationIndexUpdate, animationIndex, animationSpeed = 10;
    private boolean moving = true;
    public BrownEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/entitySprites/enemySprite/Nemico_Marrone_DOWN.png");
        BufferedImage temp2 = loadImg("/entitySprites/enemySprite/Nemico_Marrone_UP.png");
        BufferedImage temp3 = loadImg("/entitySprites/enemySprite/Nemico_Marrone_SX.png");
        BufferedImage temp4 = loadImg("/entitySprites/enemySprite/Nemico_Marrone_DX.png");

        BufferedImage[] down = new BufferedImage[9];
        down[0] = temp.getSubimage(0, 0, 10, 22);
        down[1] = temp.getSubimage(10, 0, 10, 22);
        down[2] = temp.getSubimage(21, 0, 10, 22);
        down[3] = temp.getSubimage(32, 0, 14, 22);
        down[4] = temp.getSubimage(47, 0, 16, 22);
        down[5] = temp.getSubimage(63, 0, 16, 22);
        down[6] = temp.getSubimage(79, 0, 16, 22);
        down[7] = temp.getSubimage(94, 0, 14, 22);
        down[8] = temp.getSubimage(106, 0, 12, 22);

        BufferedImage[] dx = new BufferedImage[7];
        dx[0] = temp4.getSubimage(0, 0, 10, 22);
        dx[1] = temp4.getSubimage(11, 0, 10, 22);
        dx[2] = temp4.getSubimage(21, 0, 10, 22);
        dx[3] = temp4.getSubimage(32, 0, 11, 22);
        dx[4] = temp4.getSubimage(44, 0, 11, 22);
        dx[5] = temp4.getSubimage(56, 0, 11, 22);
        dx[6] = temp4.getSubimage(66, 0, 10, 22);

        BufferedImage[] sx = new BufferedImage[7];
        sx[0] = temp3.getSubimage(0, 0, 11, 22);
        sx[1] = temp3.getSubimage(11, 0, 11, 22);
        sx[2] = temp3.getSubimage(22, 0, 11, 22);
        sx[3] = temp3.getSubimage(34, 0, 11, 22);
        sx[4] = temp3.getSubimage(45, 0, 11, 22);
        sx[5] = temp3.getSubimage(56, 0, 11, 22);
        sx[6] = temp3.getSubimage(66, 0, 11, 22);

        BufferedImage[] up = new BufferedImage[9];
        up[0] = temp2.getSubimage(0, 0, 10, 22);
        up[1] = temp2.getSubimage(10, 0, 10, 22);
        up[2] = temp2.getSubimage(21, 0, 10, 22);
        up[3] = temp2.getSubimage(32, 0, 14, 22);
        up[4] = temp2.getSubimage(47, 0, 16, 22);
        up[5] = temp2.getSubimage(63, 0, 16, 22);
        up[6] = temp2.getSubimage(79, 0, 16, 22);
        up[7] = temp2.getSubimage(94, 0, 14, 22);
        up[8] = temp2.getSubimage(106, 0, 12, 22);

        sprites = new BufferedImage[][] {down, up, sx, dx};
    }

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

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (death){
            g.drawImage(deathAnimation[deathIndex], x*3, y*3, w*3, h*3, null);
        }
        else {
            g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            g.drawRect(hitbox.x * 3, hitbox.y * 3, hitbox.w * 3, hitbox.h * 3);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String dir = (String) arg;
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
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BrownEnemyGraphics){
            BrownEnemyGraphics r = (BrownEnemyGraphics) obj;
            return r.x == x && r.y == y;
        }
        return false;
    }
}
