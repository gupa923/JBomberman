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

//TODO quando si aggiungeranno più livelli qua bisognerà avere un array di Level non solo un level. probabilmente creerò un'altra classe
public class LevelManager {
    private static LevelManager instance;

    private GameModel gameModel;
    private Level level;
    private ArrayList<Level> levels;
    private LevelGraphics levelGraphics;
    private ArrayList<LevelGraphics> levelGraphicsArrayList;

    private LevelManager(){
        levels = new ArrayList<>();
        levelGraphicsArrayList = new ArrayList<>();
        gameModel = GameModel.getInstance();
        levelGraphics = new LevelGraphics("/livelli/livello1/lvl1.png");
        level = new Level(getLvlData("/livelli/livello1/lvl1Data.png"));
        level.addObserver(levelGraphics);
        levels.add(level);
        levelGraphicsArrayList.add(levelGraphics);
        level = new Level(getLvlData("/livelli/livello2/lvl2Data.png"));
        levelGraphics = new LevelGraphics("/livelli/livello2/lvl2.png");
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

    //TODO da modificare quando avremo più livelli da gestire.
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

    public LevelGraphics getLevelGraphics() {
        return levelGraphics;
    }

    public ArrayList<LevelGraphics> getLevelGraphicsArrayList() {
        return levelGraphicsArrayList;
    }
}
