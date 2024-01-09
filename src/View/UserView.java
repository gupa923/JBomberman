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
    }

    public BufferedImage getAvatar() {
        return avatar;
    }
}
