package View;

import java.awt.*;

public class MatchGraphics extends StateGraphics {

    private PlayerGraphics playerGraphics;
    private LevelGraphics levelGraphics;

    public MatchGraphics(){

    }
    @Override
    public void draw(Graphics g) {
        levelGraphics.draw(g);
        playerGraphics.draw(g);
    }

    public void setLevelGraphics(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }

    public void setPlayerGraphics(PlayerGraphics playerGraphics) {
        this.playerGraphics = playerGraphics;
    }
}
