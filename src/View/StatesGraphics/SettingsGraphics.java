package View.StatesGraphics;

import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica del menù delle impostazioni
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class SettingsGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex;
    private UserView uv;

    /**
     * Costruttore della classe
     */
    public SettingsGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[4];
        imgs[0] = loadImg("/Imgs/settings_menu/JBomberman_menù_impostazioni_audio_premuto.png");
        imgs[1] = loadImg("/Imgs/settings_menu/JBomberman_menù_impostazioni_comandi_premuto.png");
        imgs[2] = loadImg("/Imgs/settings_menu/JBomberman_menù_impostazioni_non_premuti.png");
        imgs[3] = loadImg("/Imgs/settings_menu/JBomberman_menù_impostazioni_start_page_premuto.png");
    }

    /**
     * Disegna gli elementi di questa classe
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
        g.setColor(new Color(255,207, 151));
        g.fillRect(0, 816-64, 64, 64);
        g.drawImage(uv.getAvatar(), 0, 816-64, 64, 64, null);
    }

    /**
     * Aggiorna lo stato di questa classe in basse alle notifiche ricevute dagli Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("STARTPAGE PRESSED")){
                imgIndex = 3;
            }else if (message.equals("COMANDI PRESSED")){
                imgIndex = 1;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 2;
            }else if (message.equals("AUDIO PRESSED")){
                imgIndex = 0;
            }
        }
    }

    public void setUv(UserView uv) {
        this.uv = uv;
    }
}
