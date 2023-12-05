package View;

import View.StatesGraphics.MatchGraphics;
import View.StatesGraphics.MenuGraphics;
import View.StatesGraphics.PauseGraphics;
import View.StatesGraphics.StateGraphics;

import javax.swing.*;
import java.awt.*;

/**
 * questa classe è il pannello del gioco e gestisce tutte le cose che vanno disegnate sullo schermo
 * il gamePanel ha un activeState che ha un metodo draw che verrà invocato durante l'esecuzione del paintComponent.
 * @see JPanel
 * @author gupa9
 */
public class GamePanel extends JPanel {

    private MatchGraphics matchGraphics;
    private MenuGraphics menuGraphics;
    private StateGraphics activeState;
    private LoginPanel loginPanel;
    public GamePanel(StateGraphics activeState){
        setPreferredSize(new Dimension(272 * 3, 272* 3));
        setFocusable(true);
        requestFocusInWindow();
        loginPanel = new LoginPanel();
        add(loginPanel);
        this.activeState = activeState;
    }

    /**
     * questo metodo chiama i metodi draw di tutte le classi della view che gestiscono la rappresentazione a schermo dei vari elementi del gioco
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       // if (menuGraphics.isActive()){
         //   menuGraphics.draw(g);
       // }else if (matchGraphics.isActive()){
         //   matchGraphics.draw(g);
        //}
        if(activeState == null){
            loginPanel.setVisible(true);
        }else {
            loginPanel.setVisible(false);
            if (activeState instanceof PauseGraphics) {
                matchGraphics.getPlayerGraphics().setMoving(false);
                matchGraphics.draw(g);
            }
            activeState.draw(g);
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

    public void setActiveState(StateGraphics activeState) {
        this.activeState = activeState;
    }
}
