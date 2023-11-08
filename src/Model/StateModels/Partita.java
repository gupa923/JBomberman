package Model.StateModels;

import Model.EntityModel.Player;
import Model.GameModel;
import Model.Level;
import Model.Stati;

import java.util.ArrayList;

/**
 * la classe partita gestisce la logica della partita
 *
 */
public class Partita extends Stato{
    private boolean firstUpdate = true;
    private Player player;
    private int actuaLevel;
    private ArrayList<Level> levels;
    private GameOver gameOver;
    public Partita(GameModel gameModel) {
        super(gameModel);
        gameOver = new GameOver(gameModel);
    }

    @Override
    public void update() {
        if(player.isAlive()){
            levels.get(actuaLevel).update();
            player.update();
            if (levels.get(actuaLevel).getObstacles().size() == 0){
                nextLevel();
                return;
            }
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

    private void nextLevel() {
        actuaLevel ++;
        player.getHitbox().setLevel(levels.get(actuaLevel));
        setChanged();
        notifyObservers("NEW LEVEL");
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
        player.getHitbox().setLevel(this.levels.get(actuaLevel));
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
