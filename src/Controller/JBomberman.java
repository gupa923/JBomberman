package Controller;

/**
 * This class contains the main method, that instances the class Game and starts the game execution.
 * @author Guido Paluzzi, Matteo Santucci
 */
public class JBomberman {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.startGame();
    }
}
