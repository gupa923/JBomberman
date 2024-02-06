package Model;

import java.util.Observable;
import java.util.Random;

/**
 * This class manages a User's data and its updating
 * @author Guido Paluzzi, Matteo Santucci
 */
public class User extends Observable {
    private final String nickname;
    private final String password;
    private final int avatarIndex;
    private int gamePlayed;
    private int victories;
    private int record;
    private final Random r = new Random();


    /**
     * Class builder. Creates an instance of User from all the user's data
     * @param usersCredential: all user's data
     */
    public User(String[] usersCredential) {
        nickname = usersCredential[0];
        password = usersCredential[1];
        avatarIndex = Integer.parseInt(usersCredential[2]);
        gamePlayed = Integer.parseInt(usersCredential[3]);
        victories = Integer.parseInt(usersCredential[4]);
        record = Integer.parseInt(usersCredential[5]);
    }

    /**
     * Create a User starting from the username, password and index of the chosen avatar
     * @param nickname: the user's nickname
     * @param password: the password of the user
     * @param avatarIndex: the index of the chosen avatar
     */
    public User(String nickname, String password, int avatarIndex) {
        this.nickname = nickname;
        this.password = password;
        this.avatarIndex = avatarIndex;
    }

    /**
     * When a match ends this method checks if the record has been improved
     * @param record
     */
    public void setRecord(int record) {
        if (record >= this.record) {
            this.record = record;
            notifyObservers(String.valueOf(this.record));
        }
    }

    /**
     * Increase the number of games played
     */
    public void setGamePlayed(){
        gamePlayed++;
        setChanged();
        notifyObservers("GAME");
    }

    /**
     * Increases the number of games won
     */
    public void setVictories(){
        victories++;
        setChanged();
        notifyObservers("VICTORY");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User u){
            return this.nickname.equals(u.nickname) && this.password.equals(u.password);
        }
        return false;
    }

    @Override
    public String toString() {
        return nickname + ' ' + password + ' ' + avatarIndex + " " + gamePlayed + " " + victories + " " + record;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public int getAvatarIndex() {
        return avatarIndex;
    }

    public int getGamePlayed() {
        return gamePlayed;
    }

    public int getRecord() {
        return record;
    }

    public int getVictories() {
        return victories;
    }
}
