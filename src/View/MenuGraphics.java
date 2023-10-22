package View;



import java.awt.*;

public class MenuGraphics extends StateGraphics{

    public MenuGraphics(){

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("PRESS ENTER TO START THE GAME", 100,100);
    }
}
