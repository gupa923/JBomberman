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
    private boolean playing = true;

    public MatchGraphics(){
        gameOverScreen = new GameOverScreen();
    }
    @Override
    public void draw(Graphics g) {
        if (playing) {
            levelGraphics.get(actualLevel).draw(g);
            playerGraphics.draw(g);
            g.setColor(new Color(240, 128, 0));
            g.fillRect(0, 208 * 3, 272 * 3, 64 * 3);
        }else{
            gameOverScreen.draw(g);
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
                actualLevel++;
            }
        }


    }

    private void resetALL() {
        playing = true;
        playerGraphics.reset();
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
}
