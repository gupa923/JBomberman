package Controller;

/**
 * Questa classe contiene il metodo main, che istanzia la classe Game e fa partire l'esecuzione del gioco
 * @author Guido Paluzzi, Matteo Santucci
 */
public class JBomberman {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.startGame();
    }
}
