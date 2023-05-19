package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observer;

public abstract class EntityGraphics implements Observer {

    protected BufferedImage img;
    protected int x, y, width, height;

    public EntityGraphics(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void loadImg(String filename){
        InputStream is = getClass().getResourceAsStream(filename);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public abstract void draw(Graphics g);

}
