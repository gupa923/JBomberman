package View;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * contiene l'immagine del livello attuale e la disegna con il metodo draw
 *
 * @see ImgImporter
 * @author gupa9
 */
public class LevelGraphics implements ImgImporter, Drawable {
    private BufferedImage lvl1Bg;

    public LevelGraphics(String filename) {
        this.lvl1Bg = loadImg(filename);
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(lvl1Bg, 0,0, 272 * 3, 208*3, null);
    }
}
