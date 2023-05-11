package InputManagment;

import Gioco.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputManager implements MouseListener
{

    private GamePanel panel;

    public MouseInputManager(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.setYm(e.getY());
        panel.setXm(e.getX());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
