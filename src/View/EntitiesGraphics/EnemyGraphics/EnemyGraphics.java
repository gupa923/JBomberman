package View.EntitiesGraphics.EnemyGraphics;

import Model.EntityModel.Hitbox;
import View.AudioPlayer;
import View.EntitiesGraphics.EntityGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observer;

/**
 * Questa classe gestisce la rappresentazione grafica dei nemici
 * @see View.EntitiesGraphics.EntityGraphics
 * @see java.util.Observer
 */
public abstract class EnemyGraphics extends EntityGraphics implements Observer {
    protected Hitbox hitbox;
    protected BufferedImage[] deathAnimation;
    protected boolean death;
    protected int deathIndex, deathTick, deathSpeed =  10;
    protected int sx, sy;
    protected AudioPlayer audioPlayer;

    /**
     * Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza dell'Enemy
     * @param h: l'altezza dell'Enemy
     */
    public EnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        audioPlayer = new AudioPlayer();
        this.sx= x;
        this.sy = y;
        loadDeathAnimation();
    }

    /**
     * Carica le immagini coinvolte nell'animazione della morte del nemico
     */
    private void loadDeathAnimation() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/morte_nemici/Morte_Nemici_DOWN.png");
        BufferedImage temp1 = loadImg("/Imgs/entitySprites/enemySprite/morte_nemici/Morte_Nemici_UP.png");

        deathAnimation = new BufferedImage[8];

        deathAnimation[0] = temp1.getSubimage(0,0, 14, 32);
        deathAnimation[1] = temp1.getSubimage(14+1,0, 14, 32);
        deathAnimation[2] = temp1.getSubimage(14+1+15,0, 14, 32);
        deathAnimation[3] = temp.getSubimage(0,0, 22, 32);
        deathAnimation[4] = temp.getSubimage(22,0, 22, 32);
        deathAnimation[5] = temp.getSubimage(43,0, 22, 32);
        deathAnimation[6] = temp.getSubimage(64,0, 18, 32);
        deathAnimation[7] = temp.getSubimage(81,0, 16, 32);

    }

    /**
     * Resetta la posizione dell'Enemy
     */
    public void resetPos() {
        x = sx;
        y = sy;
    }

    /**
     * Questo metodo gestisce l'animazione della morte dell'Enemy
     * @param death: true se il nemico sta morendo
     */
    public void setDeath(boolean death) {
        this.death = death;
        deathIndex = 0;
        deathTick = 0;
    }

    /**
     * Questo metodo disegna i nemici a schermo, ma disattiva gli aggiornamenti
     * @param g: istanza della classe Graphics
     */
    public abstract void freeze(Graphics g);
}
