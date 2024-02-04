package View.EntitiesGraphics.EnemyGraphics;

import View.EntitiesGraphics.RocketGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Questa classe gestisce la rappresentazione grafica del FinalBoss
 * @see View.EntitiesGraphics.EnemyGraphics.EnemyGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class FinalBossGraphics extends EnemyGraphics {
    private BufferedImage boss, temp;
    private BufferedImage[] sprites;
    private boolean moving = true;
    private int animationIndex;
    private int animationIndexUpdate;
    private final int animationSpeed = 15;
    private ArrayList<RocketGraphics> rocketGraphics = new ArrayList<>();

    /**
     * Costruttore della classe
     * @param x: la coordinata x del punto di spawn
     * @param y: la coordinata y del punto di spawn
     * @param w: la larghezza del FinalBossGraphics
     * @param h: l'altezza del FinalBossGraphics
     */
    public FinalBossGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        sx = x;
        sy = y;
        loadAnimations();
    }

    /**
     * Disegna un istanza di questa classe, ma disattiva gli aggiornamenti
     * @param g: istanza della classe Graphics
     */
    @Override
    public void freeze(Graphics g) {
        g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        g.drawImage(boss, (x+29)*3, y*3, 18*3, 17*3,null);
        for (RocketGraphics r : rocketGraphics){
            r.draw(g);
        }
    }

    /**
     * Carica tutte le immagini coinvolte nell'animazione
     */
    @Override
    public void loadAnimations() {
        sprites = new BufferedImage[1];
        temp = loadImg("/Imgs/entitySprites/enemySprite/Boss2.png" );
        sprites[0] = temp.getSubimage(0, 25, 74, 74);
        boss = temp.getSubimage(75,25,18,17);

        deathAnimation = new BufferedImage[2];
        deathAnimation[1] = temp.getSubimage(143, 25, 74,74);
        deathAnimation[0] = sprites[0];
    }

    /**
     * Aggiorna lo stato dell'animazione
     */
    @Override
    public void updateAnimation() {
        if (death) {
            deathTick++;
            if (deathTick > 30) {
                deathIndex++;
                deathTick = 0;
                if (deathIndex >= 2) {
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
                if (animationIndex >= sprites.length)
                    animationIndex = 0;
            }
        }
    }

    /**
     * Disegna un istanza di questa classe e tutti i rocket associati
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (!death) {
            g.drawImage(sprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            g.drawImage(boss, (x+29)*3, y*3, 18*3, 17*3,null);
        }else{
            g.drawImage(deathAnimation[deathIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
        for (int i = 0; i < rocketGraphics.size(); i++){
            RocketGraphics t = rocketGraphics.get(i);
            t.moveRocket();
            if (checkRocketOutOfBoards(t)){
                rocketGraphics.remove(t);
            }else {
                t.draw(g);
            }
        }
    }

    private boolean checkRocketOutOfBoards(RocketGraphics rocket) {
        if (rocket.getX()+rocket.getW() < 0){
            return true;
        }else if (rocket.getX() > 272){
            return true;
        }else if (rocket.getY() + rocket.getH() < 0){
            return true;
        }else if (rocket.getY() > 208){
            return true;
        }
        return false;
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
                    audioPlayer.playEffects(1);
                    death = true;
                }
            }
        }
        else if (arg instanceof int[] arr){
            rocketGraphics.add(new RocketGraphics(arr[0], arr[1], arr[2],  arr[3], arr[4]));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FinalBossGraphics r){
            return r.x == x && r.y == y;
        }
        return false;
    }

}
