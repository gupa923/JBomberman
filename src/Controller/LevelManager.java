package Controller;

import Model.GameModel;
import Model.Level;
import View.LevelGraphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
    private LevelGraphics levelGraphics;

    private LevelManager(){
        gameModel = GameModel.getInstance();
        levelGraphics = new LevelGraphics("/lvl1.png");
        level = new Level(getLvlData());
        gameModel.getPartita().setLevel(level);
    }


    /**
     * dall'immagine lvlData nel res folder crea una matrice di interi i cui valori dipendono
     * dal colore del pixel corrispondente.
     *
     * @return
     */

    //TODO da modificare quando avremo più livelli da gestire.
    private int[][] getLvlData(){
        InputStream is = LevelManager.class.getResourceAsStream("/lvl1Data.png");

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
}
