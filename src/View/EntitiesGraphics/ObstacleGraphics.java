package View.EntitiesGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Questa classe gestisce la rappresentazione grafica degli ostacoli. Ci sono due tipi di ostacoli disponibili
 * @see View.EntitiesGraphics.EntityGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class ObstacleGraphics extends EntityGraphics {
    private final BufferedImage sprite;
    private final BufferedImage explosionSprite;
    private BufferedImage t1Sprite;
    private boolean exploading = false;
    private BufferedImage[] sprites, explosionSprites, t1ExplosionSprites;
    private int animationIndex, animationTick, animatioSpeed = 20;
    private final int type;

    /**
     * Costruttore della classe
     * @param x: coordinata x dell'ostacolo
     * @param y: coordinata y dell'ostacolo
     * @param type: il tipo di ostacolo
     */
    public ObstacleGraphics(int x, int y, int type){
        super(x, y, 16, 16);
        this.type = type;
        sprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Ostacolo.png");
        explosionSprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Esplosione_Ostacolo.png");
        loadAnimations();
    }

    /**
     * Disegna a schermo un'istanza della classe ObstacleGraphics
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        updateAnimation();
        if (type == 0) {
            if (!exploading)
                g.drawImage(sprites[animationIndex], x * 3, y * 3, h * 3, w * 3, null);
            else {
                g.drawImage(explosionSprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            }
        }else {
            if (!exploading){
                g.drawImage(t1Sprite, x*3, y*3, w*3, h*3, null);
            }else{
                g.drawImage(t1ExplosionSprites[animationIndex], x*3, y*3, w*3, h*3, null);
            }
        }
    }

    /**
     * Carica tutte le immagine coinvolte nelle animazione dell'ostacolo. Vengono caricate anche le animazione della morte dell'ostacolo
     */
    @Override
    public void loadAnimations() {
        sprites = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            sprites[i] = sprite.getSubimage(i*16, 0, 16, 16);
        }
        explosionSprites = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            explosionSprites[i] = explosionSprite.getSubimage(i*16, 0, 16,16);
        }
        t1Sprite = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Tronco.png").getSubimage(19, 0, 16, 16);
        t1ExplosionSprites = new BufferedImage[6];
        BufferedImage t = loadImg("/Imgs/entitySprites/obstacleSprite/Sprite_Buco.png");
        for (int i = 0; i < 6; i++) {
            t1ExplosionSprites[i] = t.getSubimage(i*16+ i, 0, 16,16);
        }
    }

    /**
     * Aggiorna l'animazione dell'ostacolo
     */
    @Override
    public void updateAnimation() {
        if (!exploading){
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= sprites.length){
                animationIndex = 0;
            }
        }else{
            animationTick++;
            if (animationTick >= animatioSpeed){
                animationIndex++;
                animationTick = 0;
            }
            if (animationIndex >= explosionSprites.length){
                animationIndex = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Questo metodo fa in modo che l'ostacolo esploda
     * @param exploading: true se l'ostacolo deve esplodere
     */
    public void setExploading(boolean exploading) {
        this.exploading = exploading;
        animatioSpeed = 11;
        animationTick = 0;
        animationIndex = 0;
    }

    /**
     * Fa in modo che gli ostacoli vengano disegnati, ma impedisce il loro aggiornamento
     * @param g: istanza della classe Graphics
     */
    public void freeze(Graphics g) {
        if (type == 0) {
            if (!exploading)
                g.drawImage(sprites[animationIndex], x * 3, y * 3, h * 3, w * 3, null);
            else {
                g.drawImage(explosionSprites[animationIndex], x * 3, y * 3, w * 3, h * 3, null);
            }
        }else {
            if (!exploading){
                g.drawImage(t1Sprite, x*3, y*3, w*3, h*3, null);
            }else{
                g.drawImage(t1ExplosionSprites[animationIndex], x*3, y*3, w*3, h*3, null);
            }
        }
    }

}
