package Model;

import java.util.ArrayList;
import static Model.GameModel.USER;

public class Accounts {
    private ArrayList<User> users = new ArrayList<>();


    public Accounts(ArrayList<String> usersStrings) {
        loadUsers(usersStrings);
    }

    private void loadUsers(ArrayList<String> usersStrings) {
        for (String s: usersStrings){
            String[] usersCredential = s.split(" ");
            for (String ps: usersCredential){
                System.out.println(ps);
            }
            users.add(new User(usersCredential));
        }
    }


    public boolean login(String[] credential) {
        System.out.println("STOCAZZO");
        for (User u: users){
            if (u.getNickname().equals(credential[0]) && u.getPassword().equals(credential[1])){
                USER = u;
                return true;
            }
        }
        return false;
    }

    public boolean register(String[] credential) {
        for (User u: users){
            if (u.getNickname().equals(credential[0]) || u.getPassword().equals(credential[1])){
                return false;
            }
        }
        USER = new User(credential[0], credential[1]);
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
