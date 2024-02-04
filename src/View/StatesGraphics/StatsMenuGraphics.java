package View.StatesGraphics;


import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce il menù delle statistiche
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class StatsMenuGraphics extends StateGraphics {
    private BufferedImage[] imgs;
    private int imgIndex;
    private UserView uv;

    /**
     * Costruttore della classe
     */
    public StatsMenuGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        imgs[0] = loadImg("/Imgs/menu_stats/JBomberman_menù_stats.png");
        imgs[1] = loadImg("/Imgs/menu_stats/JBomberman_menù_stats_back_premuto.png");
    }

    /**
     * Questo metodo disegna gli elementi di questa classe
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
        uv.draw(g);
    }

    /**
     * Aggiorna lo stato del gioco in base alle notifiche ricevute dagli Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
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
