package Controller.EntityManagers;

import Model.EntityModel.Player;
import Model.GameModel;
import View.EntitiesGraphics.PlayerGraphics;

/**
 * questa classe crea il model e la view del Player.
 * @see Player
 * @see PlayerGraphics
 * @author Guido Paluzzi, Matteo Santucci
 */
public class PlayerManager {
    private static PlayerManager instance;
    private final GameModel gameModel;
    private final Player player;
    private final PlayerGraphics playerGraphics;

    private PlayerManager(){
        gameModel = GameModel.getInstance();
        player = new Player(32, 8, 16, 24);
        gameModel.getPartita().setPlayer(player);
        playerGraphics = new PlayerGraphics(player.getX(), player.getY(), player.getW(), player.getH());
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
