package View.EntitiesGraphics;

import Model.EntityModel.Bomb;
import View.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class BombGraphics implements ImgImporter {
    private int x, y, w, h;
    private BufferedImage[] imgs;


    public BombGraphics(int x, int y){
        this.x = x;
        this.y = y;
        w = h = 16;
        importImgs();
    }

    private void importImgs(){
        BufferedImage temp = loadImg("/bomb.png");

        imgs = new BufferedImage[3];
        imgs[0] = temp.getSubimage(0, 0, 16, 16);
        imgs[1] = temp.getSubimage(16, 0, 16, 16);
        imgs[2] = temp.getSubimage(32, 0, 16, 16);
    }

    public void draw(Graphics g){
        g.drawImage(imgs[0], x*3, (y+8)*3, w*3, h*3, null);
    }

}
