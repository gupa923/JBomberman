package View.StatesGraphics;


import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class StatsMenuGraphics extends StateGraphics {
    private BufferedImage[] imgs;
    private int imgIndex;
    private UserView uv;
    public StatsMenuGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        imgs[0] = loadImg("/Imgs/menu_stats/JBomberman_menù_stats.png");
        imgs[1] = loadImg("/Imgs/menu_stats/JBomberman_menù_stats_back_premuto.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
        uv.draw(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("BACK PRESSED")){
                imgIndex = 1;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }

    public void setUv(UserView uv) {
        this.uv = uv;
    }
}
