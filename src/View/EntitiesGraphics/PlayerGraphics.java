package View.EntitiesGraphics;


import Model.EntityModel.Hitbox;
import View.AudioPlayer;
import View.StatesGraphics.MatchGraphics;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class manages the graphical representation of the player. The bombs present in the game are also associated with this class
 * @see EntityGraphics
 * @see Observer
 * @author Guido Paluzzi, Matteo Santucci
 */
public class PlayerGraphics extends EntityGraphics implements Observer {
    private int speed = 1;
    private boolean immortality = true;
    private boolean moving, changeLevel;
    private BufferedImage right, left, up, down, iRight, iLeft, iUp, iDown;
    private BufferedImage[] imgAmount, immortailtyImgAmount, nextLevel, deathSprites;
    private BufferedImage[][] movingAnimations, immortalityMovingAnimations;
    private int animationIndex;
    private int cLevelIndex;
    private int cLevelIndexUpdate;
    private int animationIndexUpdate;
    private int typeAnimation;
    private final int animationSpeed = 10;
    private BombGraphics bombGraphics;
    private final ArrayList<BombGraphics> bombViews;
    private int deathTick, deathIndex;
    private boolean death;
    private final AudioPlayer audioPlayer;

    /**
     * Class constructor
     * @param x: the x coordinate of the spawn point
     * @param y: the y coordinate of the spawn point
     * @param w: the width of the playerGraphics
     * @param h: the height of the playerGraphics
     */
    public PlayerGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        bombViews = new ArrayList<>();
        audioPlayer = new AudioPlayer();
        loadSprites();
        loadAnimations();
    }


    private void loadSprites() {
        right = loadImg("/Imgs/entitySprites/playerSprites/right.png");
        left = loadImg("/Imgs/entitySprites/playerSprites/left.png");
        up = loadImg("/Imgs/entitySprites/playerSprites/up.png");
        down = loadImg("/Imgs/entitySprites/playerSprites/down.png");

        imgAmount = new BufferedImage[] { down, left, up, right};

        iRight = loadImg("/Imgs/entitySprites/playerSprites/ImmortalitySprites/ImRight.png");
        iLeft = loadImg("/Imgs/entitySprites/playerSprites/ImmortalitySprites/ImLeft.png");
        iUp = loadImg("/Imgs/entitySprites/playerSprites/ImmortalitySprites/ImUp.png");
        iDown = loadImg("/Imgs/entitySprites/playerSprites/ImmortalitySprites/ImDown.png");

        immortailtyImgAmount = new BufferedImage[] {iDown, iLeft, iUp, iRight};
    }

    /**
     * This method loads all the images involved in the animations of the PlayerGraphics class
     */
    @Override
    public void loadAnimations() {
        movingAnimations = new BufferedImage[4][3];
        for (int y = 0; y < movingAnimations.length; y++){
            for (int x = 0; x<movingAnimations[y].length; x++){
                BufferedImage temp = imgAmount[y].getSubimage(x*16, 0, w, h);
                movingAnimations[y][x] = temp;
            }
        }

        immortalityMovingAnimations = new BufferedImage[4][3];
        for (int y = 0; y < immortalityMovingAnimations.length; y++){
            for (int x = 0; x< immortalityMovingAnimations[y].length; x++){
                immortalityMovingAnimations[y][x] = immortailtyImgAmount[y].getSubimage(x * 16, 0, w, h);
            }
        }
        BufferedImage temp = loadImg("/Imgs/entitySprites/playerSprites/Sprite_NEW_LEVEL.png");
        nextLevel = new BufferedImage[9];
        nextLevel[0] = temp.getSubimage(0, 0, 15, 22);
        nextLevel[1] = temp.getSubimage(15, 0, 15, 22);
        nextLevel[2] = temp.getSubimage(30, 0, 16, 22);
        nextLevel[3] = temp.getSubimage(46, 0, 15, 22);
        nextLevel[4] = temp.getSubimage(61, 0, 15, 22);
        nextLevel[5] = temp.getSubimage(76, 0, 16, 22);
        nextLevel[6] = temp.getSubimage(92, 0, 16, 22);
        nextLevel[7] = temp.getSubimage(108, 0, 15, 22);
        nextLevel[8] = temp.getSubimage(123, 0, 9, 22);

        BufferedImage temp1 = loadImg("/Imgs/entitySprites/playerSprites/player_death.png");
        deathSprites = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                deathSprites[i] = temp1.getSubimage(15, 0, 15, 32);
            }else if (i == 0){
                deathSprites[i] = temp1.getSubimage(0,0, 15, 32 );
            }else{
                deathSprites[i] = temp1.getSubimage(15*i + i - 1,0, 15, 32 );
            }
        }
    }

    /**
     * Update the state of this class based on notifications received from the Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String direction){
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
                case "EXPLOSION" -> {
                    //despawnBomb();
                }
                case "SPEED" -> {
                    speed++;
                }
                case "NEXT LEVEL" -> {
                    changeLevel = true;
                    immortality = true;
                }
                case "DYING" -> {
                    death = true;
                    audioPlayer.playEffects(0);
                    deathIndex = 0;
                    deathTick = 0;
                }
                case "IMMORTALITY"->{
                    immortality = true;

                }
                case "NO IMMORTALITY"->{
                    immortality = false;

                }
            }
        }
        if (arg instanceof int[][] pos){
            explodBomb(pos);
        }
        else if (arg instanceof String[] a){
            despawnBomb(a);
        } else if (arg instanceof Integer) {
            int i = (Integer) arg;
            if (i == +1){
                MatchGraphics.LIFE_VIEW++;
            }else {
                MatchGraphics.SCORE_VIEW += i;
            }

        }
    }

    private void despawnBomb(String[] a) {
        int xb = Integer.parseInt(a[0]);
        int yb = Integer.parseInt(a[1]);
        for (int x = 0; x < bombViews.size(); x++){
            BombGraphics temp = bombViews.get(x);
            if (temp.getX() == xb && temp.getY() == yb){
                bombViews.remove(temp);
            }
        }
    }

    private void explodBomb(int[][] pos) {
        for (int x = 0; x < bombViews.size(); x++){
            BombGraphics temp = bombViews.get(x);
            if (temp.getX() == pos[0][0] && temp.getY() == pos[0][1]){
                temp.setExplosion(pos);
                temp.setExploding(true);
                temp.playExplosion();

            }
        }
    }

    private void spawnBomb() {
        int nx = (x)/16;
        int ny = (y+8)/16;

        bombGraphics = new BombGraphics(nx, ny);
        bombGraphics.playSpawn();
        bombViews.add(bombGraphics);
    }

    /**
     * This method updates the state of the animations
     */
    @Override
    public void updateAnimation(){
        if (death){
            deathTick++;
            if (deathTick > 10){
                deathIndex ++;
                deathTick = 0;
                if (deathIndex >= 8){
                    deathIndex = 0;
                }
            }
        }
        else if (changeLevel){
            cLevelIndexUpdate++;
            if (cLevelIndexUpdate >= 10){
                cLevelIndex++;
                cLevelIndexUpdate = 0;
                if (cLevelIndex >= 9){
                    cLevelIndex = 0;
                }
            }
        } else {
            if (!moving) {
                animationIndex = 1;
            } else {
                animationIndexUpdate++;
                if (animationIndexUpdate >= animationSpeed) {
                    animationIndexUpdate = 0;
                    animationIndex++;
                    if (animationIndex >= movingAnimations[typeAnimation].length)
                        animationIndex = 0;
                }
            }
        }
    }

    /**
     * This method draws the player on the screen and also manages the graphic representation of the bombs.
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g){
        updateAnimation();
        for (BombGraphics b : bombViews) {
            b.draw(g);
        }
        if (changeLevel){
            g.drawImage(nextLevel[cLevelIndex], (x/16)* 16*3, y*3, w*3, h*3, null);
        }else if (death){
            g.drawImage(deathSprites[deathIndex],x*3, (y-8)*3, 16*3, 32*3, null );
        }else if (immortality){
            g.drawImage(immortalityMovingAnimations[typeAnimation][animationIndex],x * 3, y * 3, w * 3, h * 3, null);
        }
        else {

            g.drawImage(movingAnimations[typeAnimation][animationIndex], x * 3, y * 3, w * 3, h * 3, null);
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Resets the player state to the initial state
     */
    public void reset() {
        x = 32;
        y = 8;
        immortality = true;
        death = false;
        bombViews.clear();
        typeAnimation = 0;
        animationIndex = 1;
    }

    /**
     * Resets the PlayerGraphics position to the initial position
     */
    public void resetPos() {
        x = 32;
        y = 8;
        death = false;
        immortality = true;
        typeAnimation = 0;
        animationIndex = 1;
    }

    /**
     * This method begins the transition between one level and another
     * @param changeLevel: true if the player is entering the trapdoor to change levels
     */
    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
        cLevelIndexUpdate = 0;
        cLevelIndex = 0;
    }

    /**
     * Draws the player and bombs on the screen but blocks the animation from updating
     * @param g: instance of the Graphics class
     */
    public void freeze(Graphics g) {
        g.drawImage(movingAnimations[0][1], x * 3, y * 3, w * 3, h * 3, null);
        for (BombGraphics b : bombViews) {
            b.freeze(g);
        }
    }
}
