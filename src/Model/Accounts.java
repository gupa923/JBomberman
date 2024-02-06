package Model;

import java.util.ArrayList;

import static Model.GameModel.USER;

/**
 * This class manages the various accounts and the login and registration mechanics. It therefore contains all the Users present and their data
 * @see User
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Accounts {
    private final ArrayList<User> users = new ArrayList<>();

    /**
     * Class Constructor
     * @param usersStrings: all users data
     */
    public Accounts(ArrayList<String> usersStrings) {
        loadUsers(usersStrings);
    }

    /**
     * From the data of the various users it creates the Users themselves
     * @param usersStrings: the data of the various Users of the game
     */
    private void loadUsers(ArrayList<String> usersStrings) {
        for (String s: usersStrings){
            String[] usersCredential = s.split(" ");
            users.add(new User(usersCredential));
        }
    }

    /**
     * This method handles login. Then check whether the credentials entered correspond to an existing user
     * @param credential: the credentials with which you want to try to log in
     * @return true if the login was successful
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
     * This method handles logging. Check whether the credentials entered have already been taken by another user, if not, proceed to register the new user
     * @param credential: the credentials with which you want to register
     * @return true if the registration was successful
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
