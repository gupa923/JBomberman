package Controller;

import Model.GameModel;
import Model.Player;
import View.PlayerGraphics;

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
        player = gameModel.getPlayer();
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
