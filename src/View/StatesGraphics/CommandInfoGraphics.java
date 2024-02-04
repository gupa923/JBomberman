package View.StatesGraphics;

import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica del menù dei comandi
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class CommandInfoGraphics extends StateGraphics{

    private BufferedImage[] imgs;
    private int imgIndex;

    /**
     * Costruttore della classe
     */
    public CommandInfoGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        imgs[0] = loadImg("/Imgs/command_menu/JBomberman_menù_lista_comandi.png");
        imgs[1] = loadImg("/Imgs/command_menu/JBomberman_menù_lista_comandi_back_premuto.png");
    }

    /**
     * Disegna gli elementi dello stato
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    /**
     * Aggiorna lo stato di questa classe in base alle notifiche ricevute dall'Observable
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


}
