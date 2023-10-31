package View.StatesGraphics;

import View.EntitiesGraphics.PlayerGraphics;
import View.LevelGraphics;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * questa classe gestisce la rappresentazione grafica della partita vera e propria
 *
 *
 *
 */
public class MatchGraphics extends StateGraphics implements Observer {

    private PlayerGraphics playerGraphics;
    private LevelGraphics levelGraphics;

    public MatchGraphics(){

    }
    @Override
    public void draw(Graphics g) {
        levelGraphics.draw(g);
        playerGraphics.draw(g);
        g.setColor(new Color(240, 128,0));
        g.fillRect(0, 208*3, 272*3, 64*3);
    }

    public void setLevelGraphics(LevelGraphics levelGraphics) {
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
            }
        }
    }

    private void resetALL() {
        playerGraphics.reset();
    }
}
