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

    public void register(String text) {
    }
}
