package View.EntitiesGraphics.EnemyGraphics;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class RedEnemyGraphics extends EnemyGraphics {
    private BufferedImage sprite;
    private BufferedImage[][] sprites;
    private int typeAnimation, animationIndexUpdate, animationIndex, animationSpeed = 10;
    private boolean moving = true;

    public RedEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/entitySprites/enemySprite/red_enemy/Nemico_Rosso_Down.png");
        BufferedImage temp2 = loadImg("/entitySprites/enemySprite/red_enemy/Nemico_Rosso_Up.png");
        BufferedImage temp3 = loadImg("/entitySprites/enemySprite/red_enemy/Nemico_Rosso_SX.png");
        BufferedImage temp4 = loadImg("/entitySprites/enemySprite/red_enemy/Nemico_Rosso_DX.png");

        BufferedImage[] left = new BufferedImage[4];
        for (int i = 0; i < 4; i++){
            if (i == 3) {
                left[i] = temp3.getSubimage(i*16 + 1, 0, 16, 24);
            }else {
                left[i] = temp3.getSubimage(i*16, 0, 16, 24);

            }
        }

        BufferedImage[] up = new BufferedImage[4];
        for (int i = 0; i < 4; i++){
            if (i == 3) {
                up[i] = temp2.getSubimage(i*16 , 0, 15, 24);
            }else {
                up[i] = temp2.getSubimage(i*16, 0, 16, 24);

            }
        }

        BufferedImage[] down = new BufferedImage[4];
        for (int i = 0; i < 4; i++){
            if (i == 3) {
                down[i] = temp.getSubimage(i*16, 0, 15, 24);
            }else {
                down[i] = temp.getSubimage(i*16, 0, 16, 24);

            }
        }

        BufferedImage[] right = new BufferedImage[4];
        for (int i = 0; i < 4; i++){
            if (i == 0) {
                right[i] = temp4.getSubimage(i*16, 0, 16, 24);
            }else {
                right[i] = temp4.getSubimage(i*16 + 1, 0, 16, 24);

            }
        }
        sprites = new BufferedImage[][] {down, up, left, right};

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
        if (obj instanceof RedEnemyGraphics){
            RedEnemyGraphics r = (RedEnemyGraphics) obj;
            return r.x == x && r.y == y;
        }
        return false;
    }
}
