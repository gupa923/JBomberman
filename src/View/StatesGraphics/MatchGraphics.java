package View.StatesGraphics;

import View.EntitiesGraphics.PlayerGraphics;
import View.LevelGraphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * questa classe gestisce la rappresentazione grafica della partita vera e propria
 *
 *
 *
 */
public class MatchGraphics extends StateGraphics {

    private PlayerGraphics playerGraphics;
    private int actualLevel;
    private ArrayList<LevelGraphics> levelGraphics;
    private GameOverScreen gameOverScreen;
    private boolean win, playing = true;
    private WinGraphics winGraphics;
    public static int SCORE_VIEW = 0;

    public MatchGraphics(){
        gameOverScreen = new GameOverScreen();
        winGraphics = new WinGraphics();
    }
    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("serif", 1, 30));
        if(win){
            winGraphics.draw(g);
        }else {
            if (playing) {
                levelGraphics.get(actualLevel).draw(g);
                playerGraphics.draw(g);
                g.setColor(new Color(240, 128, 0));
                g.fillRect(0, 208 * 3, 272 * 3, 64 * 3);
                g.setColor(Color.BLACK);
                g.drawString("SCORE = " + String.valueOf(SCORE_VIEW), 272, 208*3 + 64);
            } else {
                gameOverScreen.draw(g);
            }
        }
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            String message = (String) arg;
            if (message.equals("RESET")){
                resetALL();
            } else if (message.equals("PLAYING")){
                playing = true;
            } else if (message.equals("DEAD")){
                playing = false;
            } else if (message.equals("NEW LEVEL")){
                playerGraphics.resetPos();
                actualLevel++;
            }else if (message.equals("WIN")){
                win = true;
            } else if ( message.equals("NEW GAME")){
                win = false;
                playerGraphics.reset();
                actualLevel = 0;
            }
        }
    }

    private void resetALL() {
        playing = true;
        playerGraphics.resetPos();
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
}
