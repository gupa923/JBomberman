package View.StatesGraphics;

import java.awt.*;
import java.util.Observable;

public class SettingsGraphics extends StateGraphics{
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 272*3, 272*3);
        g.setColor(Color.BLACK);
        g.drawString("STOCAZZO", 300, 300);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
