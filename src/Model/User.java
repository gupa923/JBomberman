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

    public User(String nickname, String password, int avatarIndex, int gamePlayed, int victories, int record) {
        this.nickname = nickname;
        this.password = password;
        this.avatarIndex = avatarIndex;
        this.gamePlayed = gamePlayed;
        this.victories = victories;
        this.record = record;
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


}
