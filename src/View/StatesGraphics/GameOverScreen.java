package View.StatesGraphics;

import java.awt.*;

public class GameOverScreen extends StateGraphics{

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,272*3, 272*3);
    }
}
