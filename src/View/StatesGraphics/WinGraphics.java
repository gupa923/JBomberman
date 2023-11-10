package View.StatesGraphics;

import java.awt.*;
import java.util.Observable;

public class WinGraphics extends StateGraphics{
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 272*3, 272*3);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
