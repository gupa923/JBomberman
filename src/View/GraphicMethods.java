package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GraphicMethods {

    public static BufferedImage loadImg(String filename) {
        InputStream is = GraphicMethods.class.getResourceAsStream(filename);

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
}
