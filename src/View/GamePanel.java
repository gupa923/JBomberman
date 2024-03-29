package View;

import View.StatesGraphics.MatchGraphics;
import View.StatesGraphics.MenuGraphics;
import View.StatesGraphics.PauseGraphics;
import View.StatesGraphics.StateGraphics;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the game panel, which allows you to view all the game components on the screen
 * @see JPanel
 * @author Guido Paluzzi, Matteo Santucci
 */
public class GamePanel extends JPanel {

    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private StateGraphics activeState;
    private final LoginPanel loginPanel;

    public GamePanel(StateGraphics activeState){
        loginPanel = new LoginPanel();
        add(loginPanel);
        setPreferredSize(new Dimension(272 * 3, 272* 3));
        setFocusable(true);
        requestFocusInWindow();
        this.activeState = null;
    }

    /**
     * This method manages the graphical representation of all elements of the game
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (activeState != null) {
            if (activeState instanceof PauseGraphics) {
                matchGraphics.getPlayerGraphics().setMoving(false);
                matchGraphics.actualLevelGraphics().freeze(g);
                matchGraphics.getPlayerGraphics().freeze(g);
                matchGraphics.drawUI(g);
            }
            activeState.draw(g);
        }else {
            g.setColor(new Color(255,207, 151));
            g.fillRect(0,0,272*3, 272*3);
        }
    }

    public void setMatchGraphics(MatchGraphics matchGraphics) {
        this.matchGraphics = matchGraphics;
    }
    public void setMenuGraphics(MenuGraphics menuGraphics) {
        this.menuGraphics = menuGraphics;
    }
    public MatchGraphics getMatchGraphics() {
        return matchGraphics;
    }
    public void setActiveState(StateGraphics activeState) {
        this.activeState = activeState;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }
}
