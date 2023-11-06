package View;

import Model.EntityModel.Obstacle;
import View.EntitiesGraphics.ObstacleGraphics;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * contiene l'immagine del livello attuale e la disegna con il metodo draw
 *
 * @see ImgImporter
 * @author gupa9
 */
public class LevelGraphics implements ImgImporter, Drawable, Observer {
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof int[][]){
            int[][] temp = (int[][]) arg;
            initObstacleGraphics(temp);
        }else if (arg instanceof int[]){
            int[] temp2 = (int[]) arg;
            removeObstacle(temp2);
        }
    }

    private void removeObstacle(int[] temp2) {
        for (int j = 0; j < obstacleGraphics.size(); j++){
            if (obstacleGraphics.get(j).getX() == temp2[0] && obstacleGraphics.get(j).getY() == temp2[1]){
                obstacleGraphics.remove(obstacleGraphics.get(j));
                return;
            }
        }
    }
}
