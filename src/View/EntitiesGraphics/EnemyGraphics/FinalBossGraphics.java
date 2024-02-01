package View.EntitiesGraphics.EnemyGraphics;

import Model.EntityModel.Rocket;
import View.EntitiesGraphics.RocketGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

import static Model.EntityModel.Enemies.FinalBoss.rockets;

public class FinalBossGraphics extends EnemyGraphics {
    private BufferedImage boss, temp;
    private BufferedImage[] sprites;
    private boolean moving = true;
    private int animationIndex;
    private int animationIndexUpdate;
    private final int animationSpeed = 15;
    private ArrayList<RocketGraphics> rocketGraphics = new ArrayList<>();
    public FinalBossGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    @Override
    public void freeze(Graphics g) {
        g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    @Override
    public void loadAnimations() {
        sprites = new BufferedImage[1];
        temp = loadImg("/Imgs/entitySprites/enemySprite/Boss2.png" );
        sprites[0] = temp.getSubimage(0, 25, 74, 74);
        boss = temp.getSubimage(75,25,18,17);

        deathAnimation = new BufferedImage[2];
        deathAnimation[1] = temp.getSubimage(143, 25, 74,74);
        deathAnimation[0] = sprites[0];
    }

    @Override
    public void updateAnimation() {
        if (death) {
            deathTick++;
            if (deathTick > 30) {
                deathIndex++;
                deathTick = 0;
                if (deathIndex >= 2) {
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
                if (animationIndex >= sprites.length)
                    animationIndex = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (!death) {
            g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            g.drawImage(boss, (x+29)*3, y*3, 18*3, 17*3,null);
        }else{
            g.drawImage(deathAnimation[deathIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
        for (RocketGraphics r : rocketGraphics){
            r.moveRocket();
            r.draw(g);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String dir){
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
        else if (arg instanceof int[] arr){
            rocketGraphics.add(new RocketGraphics(arr[0], arr[1], arr[2],  arr[3], arr[4]));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FinalBossGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }

}
