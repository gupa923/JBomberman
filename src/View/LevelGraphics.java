package View;

import java.awt.*;
import java.awt.image.BufferedImage;

import static View.GraphicMethods.loadImg;
import static View.ScreenConstants.MapSize.MAP_X;
import static View.ScreenConstants.MapSize.MAP_Y;

public class LevelGraphics {

    private BufferedImage bgImg;

    public LevelGraphics(String filename){
        bgImg = loadImg(filename);
    }

    public void draw(Graphics g){
        g.drawImage(bgImg, 0, 0, MAP_X, MAP_Y, null);
    }
}
