package Model.StateModels;

import Model.EntityModel.Player;
import Model.GameModel;
import Model.Level;

/**
 * la classe partita gestisce la logica della partita
 *
 */
public class Partita extends Stato{
    private Player player;
    private Level actuaLevel;
    public Partita(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void update() {
        player.update();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * imposta il livello attuale e aggiunge la matrice data alla hitbox
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.actuaLevel = level;
        player.getHitbox().setData(level.getData());
    }

    public void reset(){
        player.reset();

        setChanged();
        notifyObservers("RESET");
    }

    public Player getPlayer() {
        return player;
    }
}
