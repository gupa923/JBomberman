package Controller;

import Model.GameModel;
import Model.Level;
import View.LevelGraphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * gestisce il model del level e la view del level
 *
 * @author gupa9
 */
public class LevelManager {
    private static LevelManager instance;

    private final GameModel gameModel;
    private Level level;
    private final ArrayList<Level> levels;
    private LevelGraphics levelGraphics;
    private final ArrayList<LevelGraphics> levelGraphicsArrayList;

    private LevelManager(){
        levels = new ArrayList<>();
        levelGraphicsArrayList = new ArrayList<>();
        gameModel = GameModel.getInstance();
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello1/Stage1.png");
        level = new Level(getLvlData("/Imgs/livelli/livello1/lvl1Data.png"));
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/Imgs/livelli/livello1/lvl2Data.png"));
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello1/Stage1.png");
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/Imgs/livelli/livello1/lvl3Data.png"));
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello1/Stage1.png");
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/Imgs/livelli/livello1/bossLvl1.png"));
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello1/stageBoss1.png");
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/Imgs/livelli/livello2/lvl4Data.png"));
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello2/Stage2.png");
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/Imgs/livelli/livello2/lvl5Data.png"));
        levelGraphics = new LevelGraphics("/Imgs/livelli/livello2/Stage2.png");
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        gameModel.getPartita().setLevels(levels);
    }


    /**
     * dall'immagine lvlData nel res folder crea una matrice di interi i cui valori dipendono
     * dal colore del pixel corrispondente.
     *
     * @return
     */
    private int[][] getLvlData(String name){
        InputStream is = LevelManager.class.getResourceAsStream(name);

        BufferedImage lvlDataPng = null;
        try {
            lvlDataPng = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] lvlData = new int[lvlDataPng.getHeight()][lvlDataPng.getWidth()];
        for (int y = 0; y < lvlDataPng.getHeight(); y++){
            for (int x = 0; x < lvlDataPng.getWidth(); x++){
                Color color = new Color(lvlDataPng.getRGB(x, y));
                if (color.equals(Color.BLACK))
                    lvlData[y][x] = 1;
                else if (color.equals(Color.BLUE))
                    lvlData[y][x] = 2;
                else if (color.equals(Color.RED))
                    lvlData[y][x] = 3;
                else if (color.equals(Color.YELLOW))
                    lvlData[y][x] = 4;
                else if (color.equals(Color.GREEN))
                    lvlData[y][x] = 5;
                else if (color.equals(new Color(255, 0, 255)))
                    lvlData[y][x] = 6;
                else if (color.equals(new Color(255, 125,0))){
                    lvlData[y][x] = 7;
                }else if (color.equals(new Color(125,125, 125))){
                    lvlData[y][x] = 8;
                }else if (color.equals(new Color(0, 255, 255))){
                    lvlData[y][x] = 66;
                }else if (color.equals(new Color(255, 125, 125))){
                    lvlData[y][x] = 9;
                }
                else
                    lvlData[y][x] = 0;
            }
        }
        return lvlData;
    }

    public static LevelManager getInstance() {
        if (instance == null)
            instance = new LevelManager();
        return instance;
    }

    public ArrayList<LevelGraphics> getLevelGraphicsArrayList() {
        return levelGraphicsArrayList;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
}
