package Model;

import java.util.Objects;
import java.util.Random;

public class User {
    private String nickname;
    private String password;
    private int avatarIndex;
    private int gamePlayed;
    private int victories;
    private int record;
    private Random r = new Random();

    public User(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
        avatarIndex = r.nextInt();
    }

    public User(String[] usersCredential) {
        nickname = usersCredential[0];
        password = usersCredential[1];
        avatarIndex = Integer.parseInt(usersCredential[2]);
        gamePlayed = Integer.parseInt(usersCredential[3]);
        victories = Integer.parseInt(usersCredential[4]);
        record = Integer.parseInt(usersCredential[5]);

    }

    public void setRecord(int record) {
        this.record = record;
    }
    public void setGamePlayed(){
        gamePlayed++;
    }

    public void setVictories(){
        victories++;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User){
            User u  = (User) o;
            return this.nickname.equals(u.nickname) && this.password.equals(u.password);
        }
        return false;
    }

    @Override
    public String toString() {
        return nickname + ' ' + password + ' ' + avatarIndex + " " + gamePlayed + " " + record;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
