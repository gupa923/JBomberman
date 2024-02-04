package Model;

import java.util.ArrayList;

import static Model.GameModel.USER;

/**
 * Questa classe gestisce i vari account e le meccaniche di login e registrazione. Contiene quindi tutti gli User presenti e i loro dati
 * @see User
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Accounts {
    private final ArrayList<User> users = new ArrayList<>();

    /**
     * Costruttore della classe
     * @param usersStrings: dati di tutti gli utenti
     */
    public Accounts(ArrayList<String> usersStrings) {
        loadUsers(usersStrings);
    }

    /**
     * Dai dati dei vari utenti crea gli User stessi
     * @param usersStrings: i dati dei vari User del gioco
     */
    private void loadUsers(ArrayList<String> usersStrings) {
        for (String s: usersStrings){
            String[] usersCredential = s.split(" ");
            users.add(new User(usersCredential));
        }
    }

    /**
     * Questo metodo gestisce il login. Controlla quindi se le credenziali inserite corrispondono ad un utente esistente
     * @param credential: le credenziali con cui si vuole provare a fare il login
     * @return: true se il login è andato a buon fine
     */
    public boolean login(String[] credential) {
        for (User u: users){
            if (u.getNickname().equals(credential[0]) && u.getPassword().equals(credential[1])){
                USER = u;
                return true;
            }
        }
        return false;
    }

    /**
     * Questo metodo gestisce la registrazione. Controlla se le credenziali inserite sono già state prese da un altro utente, se non è così procede a registrare il nuovo utente
     * @param credential: le credenziali con cui ci si vuole registrare
     * @return: true se la registrazione ha avuto successo
     */
    public boolean register(String[] credential) {
        for (User u: users){
            if (u.getNickname().equals(credential[0]) || u.getPassword().equals(credential[1])){
                return false;
            }
        }
        USER = new User(credential[0], credential[1], Integer.parseInt(credential[2]));
        users.add(USER);
        return true;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getActiveUser(){
        return USER;
    }
}
