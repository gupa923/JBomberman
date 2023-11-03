package View;

import Model.EntityModel.Obstacle;
import View.EntitiesGraphics.ObstacleGraphics;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * contiene l'immagine del livello attuale e la disegna con il metodo draw
 *
 * @see ImgImporter
 * @author gupa9
 */
public class LevelGraphics implements ImgImporter, Drawable {
    private BufferedImage lvl1Bg;
    private ArrayList<ObstacleGraphics> obstacleGraphics;

    public LevelGraphics(String filename) {
        this.lvl1Bg = loadImg(filename);
        obstacleGraphics = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(lvl1Bg, 0,0, 272 * 3, 208*3, null);
        for (ObstacleGraphics o: obstacleGraphics){
            o.draw(g);
        }
    }

    public void initObstacleGraphics(int[][] pos) {
        for (int y = 0; y < pos.length; y ++){
            obstacleGraphics.add(new ObstacleGraphics(pos[y][0], pos[y][1]));
        }

    }
}
