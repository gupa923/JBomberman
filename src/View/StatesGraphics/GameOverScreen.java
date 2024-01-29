package View.StatesGraphics;

import View.AudioPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class GameOverScreen extends StateGraphics{

    private BufferedImage[] imgs;
    private int imgIndex;
    private AudioPlayer audioPlayer;
    private boolean firstCall;
    private int firstCallCounter = 0;

    public GameOverScreen(){
        audioPlayer = new AudioPlayer();
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        imgs[0] = loadImg("/Imgs/menu_game_over/Menù_GameOver.png");
        imgs[1] = loadImg("/Imgs/menu_game_over/Menù_GameOver_QUIT_premuto.png");
        imgs[2] = loadImg("/Imgs/menu_game_over/Menù_GameOver_RETRY_premuto.png");
    }

    @Override
    public void draw(Graphics g) {
        if (firstCallCounter == 1){
            firstCall = true;
        }
        if (firstCall) {
            audioPlayer.playEffects(8);
            firstCall = false;
        }
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, 272*3, 272*3);
        g.drawImage(imgs[imgIndex], 0, 0, 816, 816, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("RETRY PRESSED")){
                imgIndex = 2;
            }else if (message.equals("QUIT PRESSED")){
                imgIndex = 1;
            }else if (message.equals("NOT PRESSED")){
                imgIndex = 0;
            }
        }
    }

    public void setFirstCallCounter() {
        this.firstCallCounter ++;
    }

    public void reset() {
        firstCallCounter = 0;
        firstCall = false;
    }
}
