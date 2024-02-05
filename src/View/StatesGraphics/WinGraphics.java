package View.StatesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * This class manages the graphical representation of the Win state
 */
public class WinGraphics extends StateGraphics{
    private BufferedImage[] imgs;
    private int imgIndex;

    /**
     * Class constructor
     */
    public WinGraphics(){
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        imgs[0] = loadImg("/Imgs/menu_vittoria/Menù_Vittoria.png");
        imgs[1] = loadImg("/Imgs/menu_vittoria/Menù_Vittoria_menù_iniziale_premuto.png");
        imgs[2] = loadImg("/Imgs/menu_vittoria/Menù_Vittoria_new_game_premuto.png");
    }

    /**
     * Draw the elements of this class
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, 272*3, 272*3);
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    /**
     * Updates the class status based on notifications received from Observables
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
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
