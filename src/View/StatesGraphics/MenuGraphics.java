package View.StatesGraphics;



import Model.UI.Button;
import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class MenuGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private Model.UI.Button bPlay, bSetting, bLogin, bExit;
    private int imgIndex = 0;
    private UserView uv;

    public MenuGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[5];
        imgs[0] = loadImg("/Imgs/menu_iniziale/JBomberman_menù_iniziale.png");
        imgs[1] = loadImg("/Imgs/menu_iniziale/JBomberman_menù_iniziale_exit_premuto.png");
        imgs[2] = loadImg("/Imgs/menu_iniziale/JBomberman_menù_iniziale_stats_premuto.png");
        imgs[3] = loadImg("/Imgs/menu_iniziale/JBomberman_menù_iniziale_settings_premuto.png");
        imgs[4] = loadImg("/Imgs/menu_iniziale/JBomberman_menù_iniziale_play_premuto.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
        g.drawImage(uv.getAvatar(), 0, 816-64, 64, 64, null);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("PLAY PRESSED")){
                imgIndex = 4;
            }else if (message.equals("SETTINGS PRESSED")){
                imgIndex = 3;
            }else if (message.equals("EXIT PRESSED")){
                imgIndex = 1;
            }else if (message.equals("STATS PRESSED")){
                imgIndex = 2;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }

    public void setUv(UserView uv) {
        this.uv = uv;
    }
}
