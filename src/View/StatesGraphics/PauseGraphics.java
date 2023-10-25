package View.StatesGraphics;

import java.awt.*;

public class PauseGraphics extends StateGraphics{
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 272*3, 272*3);
        //g.setColor(Color.LIGHT_GRAY);
        //g.drawString("E' PAUSA DIO CANE", 300, 300);
    }
}
