package Gioco;

import GameInfo.Constants;
import InputManagment.KeyInputManager;
import InputManagment.MouseInputManager;

import static GameInfo.Constants.BoardConstants.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 * in questa classe che eredita da JPanel verrano gestite tutte le cose che devono essere disegnate a schermo
 *
 *
 *
 * @autor gupa923
 */
public class GamePanel extends JPanel {
    private BufferedImage img;
    private BufferedImage subImg;
    private int direction = 0;
    private boolean isMoving = false;
    private int dx, dy;
    private int xm, ym;
    private int rgergg = 0;

    /**
     * nel costruttore chiamo due metodi importImg che crea una BufferedImage e un setPanelSize che imposta la dimensione del GamePanel
     *
     *
     *
     */
    public GamePanel(){
        importImg();
        setPanelSize();

        addKeyListener(new KeyInputManager(this));
        addMouseListener(new MouseInputManager(this));
    }

    /**
     * qui leggo l'immagine e la salvo in img
     *
     *
     */
    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Bomberman1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * imposto le dimensioni del GamePanel
     *
     */
    private void setPanelSize() {
        Dimension size = new Dimension(BOARD_SIZE_X, BOARD_SIZE_Y);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDx(int dx) {
        this.dx += dx;
    }

    public void setDy(int dy) {
        this.dy += dy;
    }

    public void setXm(int xm) {
        this.xm = xm;
        dx = xm;
    }

    public void setYm(int ym) {
        this.ym = ym;
        dy = ym;
    }

    /**
     * con questo metodo disegno le cose cose a schero non si deve chiamare poichè viene eseguito insieme alla creazione del JPanel
     *
     * con il metodo getSubimage prendo rettangolo dell'imagine che parte dal pixel con x = 1 e y = 0, che è largo 16 pixel e alto 24
     * con il metodo drawImage disegno nel GamePanel la subImage create prima, alle coordinate (0, 0) e con width e height dico quanto deve essere grande,
     * in questo caso ho moltiplicato per 10 la dimensione della sottoimagine in modo che si vedesse chiaramente
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        subImg = img.getSubimage(0, 1, 16, 24);
        g.drawImage(subImg, dx, dy, (int) (16* Constants.GAME_SCALE), (int) (24 * Constants.GAME_SCALE), null);

    
    }

}
