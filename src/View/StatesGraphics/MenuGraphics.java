package View.StatesGraphics;


import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the initial menu
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class MenuGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex = 0;
    private UserView uv;

    /**
     * Class constructor
     */
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

    /**
     * Draw the elements of this class
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
        g.setColor(new Color(255,207, 151));
        g.fillRect(0, 816-64, 64, 64);
        g.drawImage(uv.getAvatar(), 0, 816-64, 64, 64, null);

    }

    /**
     * Updates the state of this class based on notifications received from Observables
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
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
