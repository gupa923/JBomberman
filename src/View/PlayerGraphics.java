package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

import static View.ScreenConstants.GAME_SCALE;

public class PlayerGraphics extends EntityGraphics{
    private int speed = 1;
    public PlayerGraphics(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImg("/Bomberman1.png");
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage sprite = img.getSubimage(0, 1, 16, 24);
        g.drawImage((Image) sprite, (int) (x*GAME_SCALE), (int) (y*GAME_SCALE), (int) (width*GAME_SCALE), (int) (height*GAME_SCALE), null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String direction = (String) arg;
            switch (direction){
                case "LEFT" -> x -= speed;
                case "RIGHT" -> x += speed;
                case "UP" -> y -= speed;
                case "DOWN" -> y += speed;
            }
        }
    }
}
