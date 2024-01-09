package View;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class UserView implements Observer, Drawable, ImgImporter {
    private String nickname;
    private int games, victories, record;
    private BufferedImage avatar;

    public UserView(String nickname, int games, int victories, int record, int avatarIndex) {
        this.nickname = nickname;
        this.games = games;
        this.victories = victories;
        this.record = record;
        createAvatar();
    }

    private void createAvatar() {
        avatar = loadImg("/Imgs/UI.png").getSubimage(1,39,16, 16);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(avatar, 666, 237, 64, 64, null);
        g.setFont(new Font("SansSerif", 0, 40));
        g.drawString(nickname, 302, 287);
        g.drawString(String.valueOf(games), 565, 427);
        g.drawString(String.valueOf(victories), 565, 566);
        g.drawString(String.valueOf(record), 492, 696);
        //g.drawString(String.valueOf(victories), 302, 287);
        //g.drawString(String.valueOf(avatar), 302, 287);
    }

    public BufferedImage getAvatar() {
        return avatar;
    }
}
