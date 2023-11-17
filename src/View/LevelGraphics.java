package View;

import Model.EntityModel.Obstacle;
import Model.EntityModel.PowerUpType;
import View.EntitiesGraphics.ObstacleGraphics;
import View.EntitiesGraphics.PowerUpGraphics;
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
    private ArrayList<ObstacleGraphics> obstacleGraphics, exploadingObstacles;
    private ArrayList<PowerUpGraphics> powerUps;

    public LevelGraphics(String filename) {
        this.lvl1Bg = loadImg(filename);
        obstacleGraphics = new ArrayList<>();
        exploadingObstacles = new ArrayList<>();
        powerUps = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(lvl1Bg, 0,0, 272 * 3, 208*3, null);
        for (ObstacleGraphics o: obstacleGraphics){
            o.draw(g);
        }
        for (ObstacleGraphics o : exploadingObstacles){
            o.draw(g);
        }
        for (PowerUpGraphics p : powerUps){
            p.draw(g);
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
            if (temp[0].length == 2) {
                initObstacleGraphics(temp);
            }else{
                System.out.println("STOCAZZO");
                initPowerUpsGraphics(temp);
            }
        }else if (arg instanceof int[]){
            int[] temp2 = (int[]) arg;
            if (temp2.length == 3){
                PowerUpGraphics g = new PowerUpGraphics(temp2[0], temp2[1], temp2[2]);
                for (int i = 0; i < powerUps.size(); i++){
                    if (powerUps.get(i).equals(g)){
                        powerUps.remove(g);
                        break;
                    }
                }
            }else {
                removeObstacle(temp2);
            }
        }
    }

    private void initPowerUpsGraphics(int[][] temp) {
        for (int i = 0; i < temp.length; i++){
            int[] temp2 = temp[i];
            powerUps.add(new PowerUpGraphics(temp2[0],temp2[1], temp2[2]));
        }
        System.out.println("Dio cane");
        System.out.println(powerUps.size());
    }

    private void removeObstacle(int[] temp2) {
        for (int j = 0; j < obstacleGraphics.size(); j++){
            if (obstacleGraphics.get(j).getX() == temp2[0] && obstacleGraphics.get(j).getY() == temp2[1]){
                ObstacleGraphics tempO = obstacleGraphics.get(j);
                obstacleGraphics.remove(tempO);
                tempO.setExploading(true);
                exploadingObstacles.add(tempO);
                return;
            }
        }
        for (int i = 0; i < exploadingObstacles.size(); i++){
            if (exploadingObstacles.get(i).getX() == temp2[0] && exploadingObstacles.get(i).getY() == temp2[1]){
                exploadingObstacles.remove(exploadingObstacles.get(i));
                return;
            }
        }
    }
}
