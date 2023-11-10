package View.StatesGraphics;

import java.awt.*;
import java.util.Observable;

public class CommandInfoGraphics extends StateGraphics{

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 816, 816);
        g.setColor(Color.BLACK);
        g.drawString("COMMAND MENU'", 350, 350);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
