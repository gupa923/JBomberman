package View.EntitiesGraphics;


import Model.Hitbox;
import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * questa classe gestisce la parte grafica relativa al player
 * ridisegna il player quando si muove e imposta la corretta animazione
 *
 * @see ImgImporter
 * @see Observer
 * @author gupa9
 */
//TODO forse è il caso di creare una superclasse
public class PlayerGraphics implements Observer, ImgImporter, Drawable {
    private int x, y, w, h;
    private int speed = 1;
    private boolean moving;
    private BufferedImage right, left, up, down;
    private BufferedImage[] imgAmount;
    private BufferedImage[][] movingAnimations;
    private int animationIndex, animationIndexUpdate, typeAnimation, animationSpeed = 7;
    private BombGraphics bombGraphics;

    //TODO ricordati di eliminare questa variabile perchè ci serve solo a fini di debug.
    private Hitbox hitbox;

    public PlayerGraphics(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        loadSprites();
        loadAnimations();
    }

    /**
     * questo metodo importa attraverso l'interfaccia ImgImporte le immagini relative alle varie animazioni e le salva in un array
     * @see ImgImporter
     */
    //TODO aggiungi animazione morte, immortalità e livello successivo.
    private void loadSprites() {
        right = loadImg("/right.png");
        left = loadImg("/left.png");
        up = loadImg("/up.png");
        down = loadImg("/down.png");

        imgAmount = new BufferedImage[] { down, left, up, right};
    }

    /**
     * a partire dall'array di BufferedImage imgAmount questo metodo crea una matrice di BufferedImage ogni riga conterra frame della stessa animazione
     */
    private void loadAnimations() {
        movingAnimations = new BufferedImage[4][3];
        for (int y = 0; y < movingAnimations.length; y++){
            for (int x = 0; x<movingAnimations[y].length; x++){
                BufferedImage temp = imgAmount[y].getSubimage(x*16, 0, w, h);
                movingAnimations[y][x] = temp;
            }
        }
    }

    /**
     * questo metodo update in base al valore di arg che ci si aspetta essere una stringa, imposta il tipo di animazione da eseguire e se necessario modifica la posizione del giocatore
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    //TODO salvare in una variabile la speed del player.
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String direction = (String) arg;
            switch (direction){
                case "RIGHT" -> {
                    x += speed;
                    moving = true;
                    typeAnimation = 3;
                }
                case "LEFT" -> {
                    moving = true;
                    x -= speed;
                    typeAnimation = 1;
                }
                case "UP" -> {
                    moving = true;
                    y -= speed;
                    typeAnimation = 2;
                }
                case "DOWN" -> {
                    moving = true;
                    y += speed;
                    typeAnimation = 0;
                }
                case "STAY" -> {
                    moving = false;
                }
                case "BOMB" -> {

                    spawnBomb();
                }
            }
        }
    }

    private void spawnBomb() {
        int nx = (x)/16;
        int ny = (y+8)/16;


        bombGraphics = new BombGraphics(nx, ny);
    }

    /**
     * questo metodo si occupa di aggiornare l'animazione.
     * tutte le animazioni sono contenute in una matrice.
     * questo metodo imposta degli indici che fanno in modo di scegliere il frame corretto da disegnare
     * le animazioni hanno un contatore che viene resettato ogni volta che ragginge il valore di animationSpeed, quando questo succede viene incrementato l'indice dell'animazione corrente, se questo indice supera la lunghezza dell'array delle animazioni disponibili allora viene azzerato.
     * se il giocatore non si muove viene disegnato un frame di default.
     *
     */
    private void updateAnimation(){
        if (!moving){
            animationIndex = 1;
        }
        else {
            animationIndexUpdate++;
            if (animationIndexUpdate >= animationSpeed) {
                animationIndexUpdate = 0;
                animationIndex++;
                if (animationIndex >= movingAnimations[typeAnimation].length)
                    animationIndex = 0;
            }
        }

    }

    /**
     * per ora disegna il player e per ora anche la hitbox ma solo per fini di debug.
     * @param g
     */
    @Override
    public void draw(Graphics g){
        updateAnimation();

        g.setColor(Color.RED);
        g.drawRect(hitbox.x * 3, hitbox.y * 3, hitbox.w * 3, hitbox.h * 3);
        g.drawImage(movingAnimations[typeAnimation][animationIndex], x*3, y*3, w*3, h*3, null);
        if (bombGraphics != null)
            bombGraphics.draw(g);
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
