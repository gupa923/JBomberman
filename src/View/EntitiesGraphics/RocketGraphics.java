package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Questa classe gestisce la rappresentazione grafica dei razzi
 * @see View.EntitiesGraphics.EntityGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class RocketGraphics extends EntityGraphics {
    private BufferedImage temp, tempFlip;
    private BufferedImage[][] sprites;
    int dir;

    /**
     * Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza del razzo
     * @param h: l'altezza del razzo
     * @param dir: la direzione verso cui si muove il razzo
     */
    public RocketGraphics(int x, int y, int w, int h, int dir) {
        super(x, y, w, h);
        this.dir = dir;
        loadAnimations();
    }

    /**
     * Carica le immagine della classe rocket
     */
    @Override
    public void loadAnimations() {
        temp = loadImg("/Imgs/entitySprites/enemySprite/Boss2.png");
        tempFlip = loadImg("/Imgs/entitySprites/enemySprite/Boss2Flip.png");
        sprites = new BufferedImage[4][1];
        sprites[0][0] = temp.getSubimage(246,24, 32,22);
        sprites[1][0] = tempFlip.getSubimage(92,24, 32,22);
        sprites[3][0] = temp.getSubimage(300,16, 20,32);
        sprites[2][0] = temp.getSubimage(342,16, 20,32);
    }

    @Override
    public void updateAnimation() {

    }

    /**
     * Disegna a schermo un'istanza della classe RocketGraphics
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(sprites[dir][0], x * 3, y * 3, 32 * 3, 22 * 3, null);
        }else if (dir == 1){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 32 * 3, 22 * 3, null);
        }else if (dir == 2){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 20 * 3, 32 * 3, null);
        }else if (dir == 3){
            g.drawImage(sprites[dir][0], x * 3, y * 3, 20 * 3, 32 * 3, null);
        }
    }


    /**
     * Aggiorna la posizione dell'istanza di questa classe
     */
    public void moveRocket() {
        if (dir == 0){
            x -= 2;
        }else if (dir == 1){
            x += 2;
        }else if (dir == 2){
            y -= 2;
        }else if (dir == 3){
            y += 2;
        }
    }
}
