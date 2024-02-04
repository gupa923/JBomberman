package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica dello YellowEnemy
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class YellowEnemyGraphics extends EnemyGraphics{
    private BufferedImage[] sprites;
    private int rem = 1;
    private int animationIndexUpdate;
    private int animationIndex;
    private final int animationSpeed = 8;
    private boolean moving = true;

    /**
     * Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza del ClownBossGraphics
     * @param h: l'altezza del ClownBossGraphics
     */
    public YellowEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Carica tutte le immagini coinvolte nelle animazioni di questa classe
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/yellow_enemy/Nemico_Giallo.png");

        sprites = new BufferedImage[6];
        sprites[0] = temp.getSubimage(0, 0, 17, 23);
        sprites[1] = temp.getSubimage(17, 0, 17, 23);
        sprites[2] = temp.getSubimage(33, 0, 16, 23);
        sprites[3] = temp.getSubimage(49, 0, 17, 23);
        sprites[4] = temp.getSubimage(66, 0, 17, 23);
        sprites[5] = temp.getSubimage(82, 0, 17, 23);

    }

    /**
     * Aggiorna lo stato di tutte le animazioni coinvolte
     */
    @Override
    public void updateAnimation() {
        if (death){
            deathTick++;
            if (deathTick > deathSpeed){
                deathIndex ++;
                deathTick = 0;
                if (deathIndex >= 8){
                    deathIndex = 0;
                }
            }
        }else {

            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex += rem;
                if (animationIndex >= sprites.length) {
                    rem = -1;
                    animationIndex += rem;
                } else if (animationIndex < 0) {
                    rem = +1;
                    animationIndex += rem;
                }
            }
        }
    }

    /**
     * Disegna un'istanza di questa classe
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (death){
            g.drawImage(deathAnimation[deathIndex], x*3, y*3, w*3, h*3, null);
        }
        else {
            g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
    }

    /**
     * Disegna un'istanza di questa classe senza aggiornare l'animazione
     * @param g: istanza della classe Graphics
     */
    public void freeze(Graphics g) {
        g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Aggiorna lo stato di questa classe in base alle notifiche ricevute dall'Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String dir){
            switch (dir){
                case "LEFT" -> {
                    moving = true;
                    x-= 1;

                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;

                }
                case "UP" -> {
                    moving = true;
                    y -= 1;

                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;

                }
                case "STAY" -> {
                    moving = false;
                }
                case "DYING" -> {
                    death = true;
                    audioPlayer.playEffects(1);
                }
            }
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof YellowEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
