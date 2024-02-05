package View;

import View.EntitiesGraphics.EnemyGraphics.EnemyGraphicsSpawner;
import View.EntitiesGraphics.ObstacleGraphics;
import View.EntitiesGraphics.PowerUpGraphics;
import View.StatesGraphics.MatchGraphics;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Manages the graphic representation of the individual levels and all the elements associated with it
 * @see ImgImporter
 * @author Guido Paluzzi, Matteo Santucci
 */
public class LevelGraphics implements ImgImporter, Drawable, Observer {
    private final BufferedImage lvl1Bg;
    private final ArrayList<ObstacleGraphics> obstacleGraphics;
    private final ArrayList<ObstacleGraphics> exploadingObstacles;
    private final ArrayList<PowerUpGraphics> powerUps;
    private EnemyGraphicsSpawner enemyGraphicsSpawner;
    private int type;
    private final AudioPlayer audioPlayer;

    /**
     * Class constructor, creates the class starting from the image at the filename path
     * @param filename: the path to the layer's background image
     */
    public LevelGraphics(String filename) {
        if (filename.equals("/Imgs/livelli/livello2/Stage2.png")){
            type = 1;
        }
        audioPlayer = new AudioPlayer();
        this.lvl1Bg = loadImg(filename);
        obstacleGraphics = new ArrayList<>();
        exploadingObstacles = new ArrayList<>();
        powerUps = new ArrayList<>();
    }

    /**
     * Draws the background image of the layer and all the entities associated with the layer itself
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g){
        g.drawImage(lvl1Bg, 0,0, 272 * 3, 208*3, null);
        for (PowerUpGraphics p : powerUps){
            p.draw(g);
        }
        for (ObstacleGraphics o: obstacleGraphics){
            o.draw(g);
        }
        for (ObstacleGraphics o : exploadingObstacles){
            o.draw(g);
        }
        if (enemyGraphicsSpawner != null){
            enemyGraphicsSpawner.draw(g);
        }
    }

    /**
     * Creates all instances of the ObstacleGraphics class associated with this layer
     * @param pos: matrix containing all the coordinates of the obstacle spawn points
     */
    public void initObstacleGraphics(int[][] pos) {
        obstacleGraphics.clear();
        exploadingObstacles.clear();
        for (int y = 0; y < pos.length; y ++){
            obstacleGraphics.add(new ObstacleGraphics(pos[y][0], pos[y][1], type));
        }

    }

    /**
     * Updates the state of the elements of this class based on the notifications that the Observables send
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof int[][]){
            try {
                int[][] temp = (int[][]) arg;
                if (temp[0].length == 2) {
                    initObstacleGraphics(temp);
                    audioPlayer.playEffects(5);
                } else {
                    initPowerUpsGraphics(temp);
                }
            }catch (ArrayIndexOutOfBoundsException e ){
            }
        }else if (arg instanceof int[] temp2){
            if (temp2.length == 3){
                PowerUpGraphics g = new PowerUpGraphics(temp2[0], temp2[1], temp2[2]);
                for (int i = 0; i < powerUps.size(); i++){
                    if (powerUps.get(i).equals(g)){
                        audioPlayer.playEffects(4);
                        powerUps.remove(g);
                        break;
                    }
                }
            }else {
                removeObstacle(temp2);
            }
        } else if (arg instanceof Integer) {
            int i = (Integer) arg;
            MatchGraphics.SCORE_VIEW += i;

        }
    }

    private void initPowerUpsGraphics(int[][] temp) {
        powerUps.clear();
        for (int i = 0; i < temp.length; i++){
            int[] temp2 = temp[i];
            powerUps.add(new PowerUpGraphics(temp2[0],temp2[1], temp2[2]));
        }
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

    public void setEnemyGraphicsSpawner(EnemyGraphicsSpawner enemyGraphicsSpawner) {
        this.enemyGraphicsSpawner = enemyGraphicsSpawner;
    }

    /**
     * Calls the freeze method of all elements associated with the layer
     * @param g: instance of the Graphics class
     */
    public void freeze(Graphics g) {
        g.drawImage(lvl1Bg, 0,0, 272 * 3, 208*3, null);
        for (PowerUpGraphics p : powerUps){
            p.freeze(g);
        }
        for (ObstacleGraphics o: obstacleGraphics){
            o.freeze(g);
        }
        for (ObstacleGraphics o : exploadingObstacles){
            o.freeze(g);
        }
        if (enemyGraphicsSpawner != null){
            enemyGraphicsSpawner.freeze(g);
        }
    }
}
