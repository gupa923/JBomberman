package View.UtilityInterfaces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * è un'interfaccia che fornisce un metodo di default che permette alle classi che la implementano di poter importare un immagine
 * @author gupa9
 */
public interface ImgImporter {

    public default BufferedImage loadImg(String filename){
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
