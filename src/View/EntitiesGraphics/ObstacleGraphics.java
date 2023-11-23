package View.EntitiesGraphics;

import View.UtilityInterfaces.Animatable;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObstacleGraphics extends EntityGraphics {
    private BufferedImage sprite, explosionSprite;
    private boolean exploading = false;
    private BufferedImage[] sprites, explosionSprites;
    private int animationIndex, animationTick, animatioSpeed = 20;


    public ObstacleGraphics(int x, int y){
        super(x, y, 16, 16);
        sprite = loadImg("/entitySprites/obstacleSprite/Sprite_Ostacolo.png");
        explosionSprite = loadImg("/entitySprites/obstacleSprite/Sprite_Esplosione_Ostacolo.png");
        loadAnimations();
    }

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if(!exploading)
            g.drawImage(sprites[animationIndex], x*3, y*3, h*3, w*3, null);
        else{
            g.drawImage(explosionSprites[animationIndex], x*3, y*3, w*3, h*3, null);
        }
    }

    @Override
    public void loadAnimations() {
        sprites = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            sprites[i] = sprite.getSubimage(i*16, 0, 16, 16);
        }
        explosionSprites = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            explosionSprites[i] = explosionSprite.getSubimage(i*16, 0, 16,16);
        }
    }

    @Override
    public void updateAnimation() {
        if (!exploading){
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= sprites.length){
                animationIndex = 0;
            }
        }else{
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= explosionSprites.length){
                animationIndex = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setExploading(boolean exploading) {
        this.exploading = exploading;
        animatioSpeed = 10;
        animationTick = 0;
        animationIndex = 0;
    }
}
