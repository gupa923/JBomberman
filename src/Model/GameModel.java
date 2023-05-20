package Model;

/**
 * questa classe è il modello del gioco, che quindi gestisce tutti i modelli e la loro inizializazione
 *
 * @author gupa9
 */
public class GameModel {
    private Player player;

    public GameModel(){
        player = new Player(0, 0, 16, 24);
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * questo metodo chiama gli update di tutti i modelli.
     *
     *
     */
    public void updateGame(){
        player.updatePos();
    }
}
