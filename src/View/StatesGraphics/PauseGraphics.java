package View.StatesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * questa classe gestisce la rappresentazione grafica dello stato di pausa
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class PauseGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex;

    /**
     * Costruttore della classe
     */
    public PauseGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[4];
        imgs[0] = loadImg("/Imgs/pause_menu/JBomberman_menù_pausa.png");
        imgs[1] = loadImg("/Imgs/pause_menu/JBomberman_menù_pausa_close_premuto.png");
        imgs[2] = loadImg("/Imgs/pause_menu/JBomberman_menù_pausa_quit_premuto.png");
        imgs[3] = loadImg("/Imgs/pause_menu/JBomberman_menù_pausa_resume_premuto.png");
    }

    /**
     * Disegna gli elementi di questa classe
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 272*3, 272*3);
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    /**
     * Aggiorna lo stato di questa classe in base alle notifiche ricevute dagli Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("RESUME PRESSED")){
                imgIndex = 3;
            }else if (message.equals("CLOSE PRESSED")){
                imgIndex = 1;
            }else if (message.equals("QUIT PRESSED")){
                imgIndex = 2;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }
}
