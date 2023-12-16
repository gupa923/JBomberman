package View.StatesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class WinGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex;
    public WinGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        imgs[0] = loadImg("/menu_vittoria/Menù_Vittoria.png");
        imgs[1] = loadImg("/menu_vittoria/Menù_Vittoria_menù_iniziale_premuto.png");
        imgs[2] = loadImg("/menu_vittoria/Menù_Vittoria_new_game_premuto.png");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, 272*3, 272*3);
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String message = (String) arg;
            if (message.equals("NEW GAME PRESSED")){
                imgIndex = 2;
            }else if (message.equals("MENU INIZIALE PRESSED")){
                imgIndex = 1;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }


}
