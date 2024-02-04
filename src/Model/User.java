package Model;

import java.util.Observable;
import java.util.Random;

/**
 * Questa classe gestisce i dati di un User, e l'aggiornamento degli stessi
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
     * Costurttore della classe. Crea un'istanza di User a partire da tutti i dati dell'utente
     * @param usersCredential: tutti i dati dell'utente
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
     * Crea un Utente a partire dal nome utente dalla password e dall'indice dell'avatar scelto
     * @param nickname: il nickname dell'utente
     * @param password: la password dekkk'utente
     * @param avatarIndex: l'indice dell'avatar scelto
     */
    public User(String nickname, String password, int avatarIndex) {
        this.nickname = nickname;
        this.password = password;
        this.avatarIndex = avatarIndex;
    }

    /**
     * Quando una partita termina questo metodo controlla se il record è stato migliorato
     * @param record
     */
    public void setRecord(int record) {
        if (record >= this.record) {
            this.record = record;
            notifyObservers(String.valueOf(this.record));
        }
    }

    /**
     * Aumenta il numero di partite giocate
     */
    public void setGamePlayed(){
        gamePlayed++;
        setChanged();
        notifyObservers("GAME");
    }

    /**
     * Aumenta il numero di partite vinte
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
