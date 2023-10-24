package Controller.EntityManagers;

import Model.GameModel;
import Model.EntityModel.Player;
import View.EntitiesGraphics.PlayerGraphics;

/**
 * questa classe gestisce il modello del player e la view del player
 * è singleton
 * @author gupa9
 */
public class PlayerManager {
    private static PlayerManager instance;
    private GameModel gameModel;
    private Player player;
    private PlayerGraphics playerGraphics;

    //TODO alla fine ricordati di togliere la hitbox dal playerGraphics
    private PlayerManager(){
        gameModel = GameModel.getInstance();
        player = new Player(32, 8, 16, 24);
        gameModel.getPartita().setPlayer(player);
        playerGraphics = new PlayerGraphics(player.getX(), player.getY(), player.getW(), player.getH());
        playerGraphics.setHitbox(player.getHitbox());
        player.addObserver(playerGraphics);
    }

    public static PlayerManager getInstance() {
        if (instance == null)
            instance = new PlayerManager();
        return instance;
    }

    public PlayerGraphics getPlayerGraphics() {
        return playerGraphics;
    }
}