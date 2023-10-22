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

    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;

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

        if (menuGraphics.isActive()){
            menuGraphics.draw(g);
        }else if (matchGraphics.isActive()){
            matchGraphics.draw(g);
        }
    }

    public void setMatchGraphics(MatchGraphics matchGraphics) {
        this.matchGraphics = matchGraphics;
    }

    public void setMenuGraphics(MenuGraphics menuGraphics) {
        this.menuGraphics = menuGraphics;
    }

    public MenuGraphics getMenuGraphics() {
        return menuGraphics;
    }

    public MatchGraphics getMatchGraphics() {
        return matchGraphics;
    }
}
