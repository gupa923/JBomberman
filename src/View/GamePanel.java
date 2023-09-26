package View;

import javax.swing.*;
import java.awt.*;

import static View.ScreenConstants.MapSize.MAP_X;
import static View.ScreenConstants.MapSize.MAP_Y;

public class GamePanel extends JPanel {
    //private ProvaG provaG;
    private PlayerGraphics playerGraphics;
    private LevelGraphics levelGraphics;
    public GamePanel(){
        setPreferredSize(new Dimension(MAP_X, MAP_Y));
        playerGraphics = new PlayerGraphics(32, 8, 16, 24);
        levelGraphics = new LevelGraphics("/level1.png");
        //provaG = new ProvaG(0, 0, 16, 24);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        levelGraphics.draw(g);
        playerGraphics.draw(g);

    }

    //public ProvaG getProvaG() {
       // return provaG;
   // }

    public PlayerGraphics getPlayerGraphics() {
        return playerGraphics;
    }
}
