package View;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Observable;

import static View.GraphicMethods.loadImg;
import static View.ScreenConstants.GAME_SCALE;

public class PlayerGraphics extends EntityGraphics{
    private BufferedImage left, down, up, right;
    private int animationIndex, animationIndexUpdate, typeAnimation, animationSpeed = 10;
    private int speed = 1;
    private String direction;
    private boolean moving;


    public PlayerGraphics(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImgs();
        loadAnimations();
    }

    private void loadAnimations() {
        movingAnimations = new BufferedImage[4][3];
        for (int y = 0; y < movingAnimations.length; y++){
            for (int x = 0; x<movingAnimations[y].length; x++){
                BufferedImage temp = imgAmount[y].getSubimage(x*16, 0, width, height);
                movingAnimations[y][x] = temp;
            }
        }
    }

    private void loadImgs() {
        left = loadImg("/left.png");
        down = loadImg("/down.png");
        up = loadImg("/up.png");
        right = loadImg("/right.png");
        imgAmount = new BufferedImage[] { down, left, up, right};
    }




    private void updateAnimation(){
        if (!moving){
            animationIndex = 1;
        }
        else {
            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex++;
                if (animationIndex >= movingAnimations[typeAnimation].length)
                    animationIndex = 0;
            }
        }

    }

    @Override
    public void draw(Graphics g) {

        updateAnimation();
        //setTypeAnimation();

        g.drawImage(movingAnimations[typeAnimation][animationIndex], (int) (x*GAME_SCALE), (int) (y*GAME_SCALE), (int) (width*GAME_SCALE), (int) (height*GAME_SCALE), null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            direction = (String) arg;
            switch (direction){
                case "LEFT" -> {
                    x -= speed;
                    moving = true;
                    typeAnimation = 1;
                }
                case "RIGHT" -> {
                    x += speed;
                    moving = true;
                    typeAnimation = 3;
                }
                case "UP" -> {
                    y -= speed;
                    moving = true;
                    typeAnimation = 2;
                }
                case "DOWN" -> {
                    y += speed;
                    moving = true;
                    typeAnimation = 0;
                }
                case "STAY" -> {
                    moving = false;
                }
            }
        }
    }


}
