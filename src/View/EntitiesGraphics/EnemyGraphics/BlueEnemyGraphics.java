package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class BlueEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation, animationIndexUpdate, animationIndex, animationSpeed = 10;
    private boolean moving = true;
    public BlueEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_UP.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_SX.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DX.png");
        BufferedImage temp4 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DOWN.png");

        BufferedImage[] up = new BufferedImage[4];
        for (int i = 0; i < 4;i++){
            up[i] = temp.getSubimage(i*16 + 1*i, 0, 16, 16);
        }

        BufferedImage[] sx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            sx[i] = temp2.getSubimage(i*16 + 1*i, 0, 16, 16);
        }

        BufferedImage[] dx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            dx[i] = temp3.getSubimage(i*16 + 1*i, 0, 16, 16);
        }

        BufferedImage[] down = new BufferedImage[10];
        for (int i = 0; i < 10;i++){
            down[i] = temp4.getSubimage(i*16 + 1*i, 0, 16, 16);
        }

        sprites = new BufferedImage[][]{up, sx, dx, down};
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
        if (obj instanceof BlueEnemyGraphics){
            BlueEnemyGraphics r = (BlueEnemyGraphics) obj;
            return r.x == x && r.y == y;
        }
        return false;
    }
}
