package View;

import javax.swing.*;
import java.awt.*;

import static View.ScreenConstants.MapSize.MAP_X;
import static View.ScreenConstants.MapSize.MAP_Y;

public class GamePanel extends JPanel {
    private ProvaG provaG;
    public GamePanel(){
        setPreferredSize(new Dimension(MAP_X, MAP_Y));

        provaG = new ProvaG(0, 0, 16, 24);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        provaG.draw(g);

    }

    public ProvaG getProvaG() {
        return provaG;
    }
}
