package View.UtilityInterfaces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Questa interfaccia permette alle classi che la implementano di usare un metodo default che cosente di importare un immagine
 * @see BufferedImage
 * @author Guido Paluzzi, Matteo Santucci
 */
public interface ImgImporter {

    /**
     * Questo metodo, dato un path ad un'immagine png, consente di importarla come BufferedImage
     * @param filename: il percorso dell'immagine che si vuole importare
     * @return: l'immagine al dato path restituita come BufferedImage
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
