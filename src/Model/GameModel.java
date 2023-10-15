package Model;

public class GameModel{
    private static GameModel instance;
    private Player player;
    private Level level;


    private GameModel(){
        player = new Player(32, 8, 16, 24);
    }

    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * questo metodo update chiama gli update di tutte le classi del model
     *
     */
    public void update(){
        player.update();
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * imposta il livello attuale e aggiunge la matrice data alla hitbox
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
        player.getHitbox().setData(level.getData());
    }
}
