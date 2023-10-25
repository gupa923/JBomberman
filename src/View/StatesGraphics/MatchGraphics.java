package View.StatesGraphics;

import View.EntitiesGraphics.PlayerGraphics;
import View.LevelGraphics;

import java.awt.*;

/**
 * questa classe gestisce la rappresentazione grafica della partita vera e propria
 *
 *
 *
 */
public class MatchGraphics extends StateGraphics {

    private PlayerGraphics playerGraphics;
    private LevelGraphics levelGraphics;

    public MatchGraphics(){

    }
    @Override
    public void draw(Graphics g) {
        levelGraphics.draw(g);
        playerGraphics.draw(g);
        g.setColor(Color.ORANGE);
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
}
