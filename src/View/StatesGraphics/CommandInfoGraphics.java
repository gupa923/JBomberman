package View.StatesGraphics;

import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the command menu
 * @see View.StatesGraphics.StateGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class CommandInfoGraphics extends StateGraphics{

    private BufferedImage[] imgs;
    private int imgIndex;

    /**
     * Class constructor
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
     * Draw the elements of the state
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    /**
     * Updates the state of this class based on notifications received from the Observable
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
