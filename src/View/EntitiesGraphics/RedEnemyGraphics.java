package View.EntitiesGraphics;


import Model.EntityModel.Hitbox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

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
        g.drawRect(hitbox.x*3, hitbox.y*3, hitbox.w*3, hitbox.h*3);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String dir = (String) arg;
            switch (dir){
                case "LEFT" -> {
                    x-= 1;
                }
                case "RIGHT" -> {
                    x += 1;
                }
                case "UP" -> {
                    y -= 1;
                }
                case "DOWN" -> {
                    y += 1;
                }
            }
        }

    }


}
