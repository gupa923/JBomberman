package View.EntitiesGraphics.EnemyGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica del LastEnemy
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class LastEnemyGraphics extends EnemyGraphics{
    private BufferedImage[][] sprites;
    private int typeAnimation;
    private int animationIndexUpdate;
    private int animationIndex;
    private final int animationSpeed = 10;
    private boolean moving = true;

    /**
     *
     *Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza del FinalBossGraphics
     * @param h: l'altezza del FinalBossGraphics
     */
    public LastEnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Carica tutte le immagini coinvolte nelle animazioni
     */
    @Override
    public void loadAnimations() {
        BufferedImage temp = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde.png");
        BufferedImage temp2 = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde_DX.png");
        BufferedImage temp3 = loadImg("/Imgs/entitySprites/enemySprite/last_enemy/Nemico_verde_SX.png");

        BufferedImage[] up, down, sx, dx;
        up = new BufferedImage[3];
        up[0] = temp.getSubimage(49, 0, 16, 22);
        up[1] = temp.getSubimage(65, 0, 16, 22);
        up[2] = temp.getSubimage(81, 0, 16, 22);

        down = new BufferedImage[3];
        down[0] = temp.getSubimage(16, 0, 16, 22);
        down[1] = temp.getSubimage(0, 0, 16, 22);
        down[2] = temp.getSubimage(112, 0, 16, 22);

        sx = new BufferedImage[3];
        for (int i = 0; i < 3;i++){
            sx[i] = temp3.getSubimage(16 + i*16, 0, 16, 22);
        }
        dx = new BufferedImage[3];
        dx[0] = temp2.getSubimage(0, 0, 16, 22);
        dx[1] = temp2.getSubimage(15, 0, 16, 22);
        dx[2] = temp2.getSubimage(30, 0, 16, 22);

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
     * Disegna un instanza di questa classe
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
     * Disegna un'istanza di questa classe, senza aggiornarne l'animazione
     * @param g: istanza della classe Graphics
     */
    public void freeze(Graphics g) {
        g.drawImage(sprites[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
    }

    /**
     * Aggiorna lo stato di questa classe in base alle notifiche ricevute dall'Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String dir) {
            switch (dir) {
                case "LEFT" -> {
                    moving = true;
                    x -= 1;
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
        if (obj instanceof LastEnemyGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }
}
