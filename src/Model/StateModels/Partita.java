package Model.StateModels;

import Model.EntityModel.Player;
import Model.GameModel;
import Model.Level;
import Model.Stati;

/**
 * la classe partita gestisce la logica della partita
 *
 */
public class Partita extends Stato{
    private boolean firstUpdate = true;
    private Player player;
    private Level actuaLevel;
    private GameOver gameOver;
    public Partita(GameModel gameModel) {
        super(gameModel);
        gameOver = new GameOver(gameModel);
    }

    @Override
    public void update() {
        if(player.isAlive()){
            actuaLevel.update();
            player.update();
            setChanged();
            notifyObservers("PLAYING");
        }else {
            if (Player.VITE <= 0) {
                Player.VITE = 4;
                gameModel.setStatoAttuale(Stati.GAME_OVER);
                gameOver.update();
                setChanged();
                notifyObservers("DEAD");
            }else{
                reset();
            }
        }
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
        player.getHitbox().setLevel(level);

    }

    /**
     * resetta tutte le classi coinvolte nella partita
     */
    public void reset(){
        gameModel.setStatoAttuale(Stati.PARTITA);
        player.reset();

        setChanged();
        notifyObservers("RESET");
    }

    public Player getPlayer() {
        return player;
    }
}
