package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observer;

public abstract class EntityGraphics implements Observer {

    protected BufferedImage[] imgAmount;
    protected BufferedImage[][] movingAnimations;
    protected int x, y, width, height;

    public EntityGraphics(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BufferedImage loadImg(String filename) {
        InputStream is = getClass().getResourceAsStream(filename);

        BufferedImage temp;
        try {
            temp = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    public abstract void draw(Graphics g);

}
