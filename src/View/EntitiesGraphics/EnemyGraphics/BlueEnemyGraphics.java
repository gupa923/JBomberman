package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica del blue enemy
 * @see View.EntitiesGraphics.EnemyGraphics;
 * @author Guido Paluzzi, Matteo Santucci
 */
public class BlueEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation;
    private int animationIndexUpdate;
    private int animationIndex;
    private final int animationSpeed = 10;
    private boolean moving = true;

    /**
     * Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza del BlueEnemy
     * @param h: l'altezza del BlueEnemy
     */
    public BlueEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Carica tutte le immagini coinvolte nell'animazione dei questa classe
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_UP.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_SX.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DX.png");
        BufferedImage temp4 = loadImg("/Imgs/entitySprites/enemySprite/blue_enemy/Nemico_Blu_DOWN.png");

        BufferedImage[] up = new BufferedImage[4];
        for (int i = 0; i < 4;i++){
            up[i] = temp.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] sx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            sx[i] = temp2.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] dx = new BufferedImage[6];
        for (int i = 0; i < 6;i++){
            dx[i] = temp3.getSubimage(i*16 + i, 0, 16, 16);
        }

        BufferedImage[] down = new BufferedImage[10];
        for (int i = 0; i < 10;i++){
            down[i] = temp4.getSubimage(i*16 + i, 0, 16, 16);
        }

        sprites = new BufferedImage[][]{up, sx, dx, down};
    }

    /**
     * Aggiorna lo stato delle animazioni
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
            if (!moving) {
                animationIndex = 0;
                animationIndexUpdate = 0;
            }
            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex++;
                if (animationIndex >= sprites[typeAnimation].length)
                    animationIndex = 0;
            }
        }
    }

    /**
     * Disegna a schermo un'istanza della classe BlueEnemyGraphics
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (death){
            g.drawImage(deathAnimation[deathIndex], x*3, y*3, w*3, h*3, null);
        }
        else {
            g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
    }

    /**
     * Disegna il BlueEnemyGraphics bloccandone l'animazione
     * @param g: istanza della classe Graphics
     */
    @Override
    public void freeze(Graphics g) {
        g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Aggiorna lo stato di questa classe in base alle notifiche inviate dall'Observable
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
                    typeAnimation = 1;
                }
                case "RIGHT" -> {
                    moving = true;
                    x += 1;
                    typeAnimation = 2;
                }
                case "UP" -> {
                    moving = true;
                    y -= 1;
                    typeAnimation = 0;
                }
                case "DOWN" -> {
                    moving = true;
                    y += 1;
                    typeAnimation = 3;
                }
                case "STAY" -> {
                    typeAnimation = 0;
                    moving = false;
                }
                case "DYING" -> {
                    audioPlayer.playEffects(1);
                    death = true;
                }
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlueEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
