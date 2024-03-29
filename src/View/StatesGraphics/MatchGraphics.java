package View.StatesGraphics;

import View.AudioPlayer;
import View.EntitiesGraphics.PlayerGraphics;
import View.LevelGraphics;
import View.UserView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class manages the graphical representation of the game, therefore also the representation of the GameOver, Win and Pause states
 */
public class MatchGraphics extends StateGraphics {

    private PlayerGraphics playerGraphics;
    private int actualLevel;
    private ArrayList<LevelGraphics> levelGraphics;
    private final GameOverScreen gameOverScreen;
    private boolean win, playing = true;
    private final WinGraphics winGraphics;
    private final BufferedImage matchUI;
    private final BufferedImage lifeUI;
    /**
     * The graphic representation of the score
     */
    public static int SCORE_VIEW = 0;
    /**
     * The graphic representation of lives
     */
    public static int LIFE_VIEW = 7;
    private AudioPlayer audioPlayer;

    /**
     * Class constructor
     */
    public MatchGraphics(){
        audioPlayer = new AudioPlayer();
        gameOverScreen = new GameOverScreen();
        winGraphics = new WinGraphics();
        matchUI = loadImg("/Imgs/UI PARTITA.png");
        lifeUI = loadImg("/Imgs/UI.png").getSubimage(1,39,16, 16);
    }

    /**
     * Draw all the elements involved in the match
     * @param g: instance of the Graphics class
     */
    @Override
    public void draw(Graphics g) {
        if(win){
            levelGraphics.get(actualLevel).freeze(g);
            playerGraphics.freeze(g);
            drawUI(g);
            winGraphics.draw(g);
        }else {
            if (playing) {
                levelGraphics.get(actualLevel).draw(g);
                playerGraphics.draw(g);
                drawUI(g);
            } else {
                levelGraphics.get(actualLevel).freeze(g);
                drawUI(g);
                playerGraphics.freeze(g);
                gameOverScreen.draw(g);
            }
        }
    }

    /**
     * Draws all information regarding the match such as remaining lives, current score and record
     * @param g: instance of the Graphics class
     */
    public void drawUI(Graphics g){
        g.setFont(new Font("serif", 1, 30));
        g.drawImage(matchUI, 0, 0, 272*3, 272*3, null);
        g.setColor(Color.BLACK);
        g.drawString("SCORE" , 272 + 40, 208*3 + 64);
        g.drawString("RECORD", 544 + 40, 208*3 + 64);
        g.drawString(String.valueOf(SCORE_VIEW) , 272 + 40, 208*3 + 64 + 64 + 20);
        g.drawString(String.valueOf(LIFE_VIEW) ,  128 + 32 + 16, 208*3 + 64 + 40 );
        g.drawString(String.valueOf(UserView.RECORD) ,  544 + 40, 208*3 + 64 + 64 + 20 );
        g.drawImage(lifeUI, 36, 208*3 + 64, 64, 64, null);
    }

    public void setLevelGraphics(ArrayList<LevelGraphics> levelGraphics) {
        this.levelGraphics = levelGraphics;
    }

    public void setPlayerGraphics(PlayerGraphics playerGraphics) {
        this.playerGraphics = playerGraphics;
    }

    public PlayerGraphics getPlayerGraphics() {
        return playerGraphics;
    }

    /**
     * Updates the state of this class based on the notifications that are sent by the Observables
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String message){
            if (message.equals("RESET")){
                resetALL();
                LIFE_VIEW--;
            } else if (message.equals("PLAYING")){
                playing = true;
            } else if (message.equals("DEAD")){
                LIFE_VIEW = 7;
                playing = false;
                gameOverScreen.setFirstCallCounter();
            } else if (message.equals("NEW LEVEL")){
                playerGraphics.resetPos();
                playerGraphics.setChangeLevel(false);
                actualLevel++;
            }else if (message.equals("WIN")){
                win = true;
                audioPlayer.playEffects(7);
            } else if ( message.equals("NEW GAME")){
                win = false;
                playerGraphics.reset();
                playerGraphics.setChangeLevel(false);
                gameOverScreen.reset();
                actualLevel = 0;
                SCORE_VIEW = 0;
            }
        }
    }

    /**
     * Resets the attributes of the PlayerGraphics class
     */
    private void resetALL() {
        playing = true;
        playerGraphics.resetPos();
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }

    public WinGraphics getWinGraphics() {
        return winGraphics;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public LevelGraphics actualLevelGraphics() {
        return levelGraphics.get(actualLevel);
    }
}
