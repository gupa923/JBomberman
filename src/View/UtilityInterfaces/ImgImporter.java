package View.UtilityInterfaces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * This interface allows the classes that implement it to use a default method that allows you to import an image
 * @see BufferedImage
 * @author Guido Paluzzi, Matteo Santucci
 */
public interface ImgImporter {

    /**
     * This method, given a path to a png image, allows you to import it as a BufferedImage
     * @param filename: the path of the image you want to import
     * @return the image at the given path returned as BufferedImage
     */
    default BufferedImage loadImg(String filename){
        InputStream is = ImgImporter.class.getResourceAsStream(filename);

        BufferedImage temp = null;
        try {
            temp = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
