package View;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static View.ScreenConstants.GAME_SCALE;

public class ProvaG implements Observer {

    private int x, y, width, height;
    private int speed = (int)(0.5f * GAME_SCALE);

    public ProvaG(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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




    public void draw(Graphics g){
        g.fillRect((int) (x*GAME_SCALE), (int) (y*GAME_SCALE), (int) (width*GAME_SCALE), (int) (height*GAME_SCALE));

    }
}
