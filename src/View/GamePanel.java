package View;

import javax.swing.*;
import java.awt.*;

/**
 * questa classe è il pannello del gioco e gestisce tutte le cose che vanno disegnate sullo schermo
 *
 * @see JPanel
 * @author gupa9
 */
public class GamePanel extends JPanel {

    private PlayerGraphics playerGraphics;
    private LevelGraphics levelGraphics;

    public GamePanel(){
        setPreferredSize(new Dimension(272 * 3, 208* 3));
    }

    /**
     * questo metodo chiama i metodi draw di tutte le classi della view che gestiscono la rappresentazione a schermo dei vari elementi del gioco
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        levelGraphics.draw(g);
        playerGraphics.draw(g);
    }

    public void setPlayerGraphics(PlayerGraphics playerGraphics){
        this.playerGraphics = playerGraphics;
    }

    public void setLevelGraphics(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }
}
