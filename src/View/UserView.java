package View;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Gestisce la rappresentazione grafica di un utente e dei suoi dati
 * @see View.UtilityInterfaces.ImgImporter
 * @see View.UtilityInterfaces.Drawable
 * @see java.util.Observer
 * @author Guido Paluzzi, Matteo Santucci
 */
public class UserView implements Observer, Drawable, ImgImporter {
    private final String nickname;
    private int games;
    private int victories;
    public static int RECORD;
    private int avatarIndex;
    private BufferedImage[] avatars;
    private BufferedImage avatar;

    /**
     * Costruttore della classe
     * @param nickname: nickname dell'utente
     * @param games: partite giocate dall'utente
     * @param victories: vittorie dell'utente
     * @param record: record dell'utente
     * @param avatarIndex: indice dell'avatar scelto dall'utente
     */
    public UserView(String nickname, int games, int victories, int record, int avatarIndex) {
        this.nickname = nickname;
        this.games = games;
        this.victories = victories;
        this.RECORD = record;
        this.avatarIndex = avatarIndex;
        createAvatar();
        avatar = avatars[avatarIndex-1];
    }

    private void createAvatar() {
        avatars = new BufferedImage[4];
        avatars[0] = loadImg("/Imgs/Avatars/Avatar_1.png");
        avatars[1] = loadImg("/Imgs/Avatars/Avatar_2.png");
        avatars[2] = loadImg("/Imgs/Avatars/Avatar_3.png");
        avatars[3] = loadImg("/Imgs/Avatars/Avatar_4.png");
    }

    /**
     * Aggiorna i valori nei campi di questa classe in base alle notifiche che arrivano dagli Observable
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String s){
            if (s.equals("GAME")){
                games++;
            }else if (s.equals("VICTORY")){
                victories++;
            }else {
                RECORD = Integer.parseInt(s);
            }
        }

    }

    /**
     * Rappresenta graficamente le informazioni dell'utente
     * @param g: istanza della classe Graphics
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255,207, 151));
        g.fillRect(666, 237, 64, 64);
        g.setColor(Color.BLACK);
        g.drawImage(avatar, 666, 237, 64, 64, null);
        g.setFont(new Font("SansSerif", 0, 40));
        g.drawString(nickname, 302, 287);
        g.drawString(String.valueOf(games), 565, 427);
        g.drawString(String.valueOf(victories), 565, 566);
        g.drawString(String.valueOf(RECORD), 492, 696);
    }

    public BufferedImage getAvatar() {
        return avatar;
    }
}
