package View.StatesGraphics;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class MenuGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex = 0;

    public MenuGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[5];
        imgs[0] = loadImg("/JBomberman_menù_iniziale_1.png");
        imgs[1] = loadImg("/JBomberman_menù_iniziale_exit_premuto.png");
        imgs[2] = loadImg("/JBomberman_menù_iniziale_login_premuto.png");
        imgs[3] = loadImg("/JBomberman_menù_iniziale_settings_premuto.png");
        imgs[4] = loadImg("/JBomberman_menù_iniziale_play_premuto.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String message = (String) arg;
            if (message.equals("PLAY PRESSED")){
                imgIndex = 4;
            }else if (message.equals("SETTINGS PRESSED")){
                imgIndex = 3;
            }else if (message.equals("EXIT PRESSED")){
                imgIndex = 1;
            }else if (message.equals("LOGIN PRESSED")){
                imgIndex = 2;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }
}
