package Model.StateModels;

import Model.EntityModel.Obstacle;
import Model.EntityModel.Player;
import Model.EntityModel.PowerUp;
import Model.GameModel;
import Model.Level;
import Model.Stati;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;

/**
 * la classe partita gestisce la logica della partita
 *
 */
public class Partita extends Stato{
    public static int SCORE = 0;
    private boolean firstUpdate = true;
    private Player player;
    private int actuaLevel;
    private ArrayList<Level> levels;
    private GameOver gameOver;
    private Win win;
    private boolean gameCompleted;
    public Partita(GameModel gameModel) {
        super(gameModel);
        gameOver = new GameOver(this.gameModel);
        win = new Win(this.gameModel);
    }

    @Override
    public void update() {
        if (gameCompleted){
            win.update();
        }else {
            if (player.isAlive()) {
                levels.get(actuaLevel).update();
                player.update();

                if (levelCompleted()) {
                    if (checkGameCompleted()){
                        return;
                    }
                    nextLevel();
                    return;
                }
                setChanged();
                notifyObservers("PLAYING");
            } else {
                if (Player.VITE <= 0) {
                    Player.VITE = 4;
                    gameModel.setStatoAttuale(Stati.GAME_OVER);
                    gameOver.update();
                    setChanged();
                    notifyObservers("DEAD");
                } else {
                    Player.VITE--;
                    reset();
                }
            }
        }
    }

    private boolean levelCompleted() {
        if (!levels.get(actuaLevel).getEnemySpawner().getEnemies().isEmpty()){
            return false;
        }
        if (levels.get(actuaLevel).getData()[player.getHitbox().y/16][player.getHitbox().x/16] == 2){
            for (Obstacle o : levels.get(actuaLevel).getObstacles()){
                if (o.getX()/16 == player.getHitbox().x /16 && o.getY()/16 == player.getHitbox().y/16){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkGameCompleted(){
        if (actuaLevel +1 >= levels.size()){
            gameCompleted = true;
            gameModel.setStatoAttuale(Stati.WIN);

            setChanged();
            notifyObservers("WIN");
            return true;
        }
        return false;
    }


    public void nextLevel() {
        actuaLevel ++;
        player.getHitbox().setLevel(levels.get(actuaLevel));
        player.resetPos();
        setChanged();
        notifyObservers("NEW LEVEL");
    }

    public void setPlayer(Player player) {
        this.player = player;
        PowerUp.player = this.player;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
        player.getHitbox().setLevel(this.levels.get(actuaLevel));
        for (Level l : levels){
            l.setPlayer(player);
        }
    }

    /**
     * resetta tutte le classi coinvolte nella partita
     */
    public void reset(){
        gameModel.setStatoAttuale(Stati.PARTITA);
        player.resetPos();

        setChanged();
        notifyObservers("RESET");
    }

    public Player getPlayer() {
        return player;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public void restartGame() {
        player.reset();
        Player.VITE = 4;
        resetLevels();
        actuaLevel = 0;
        gameCompleted = false;
        player.getHitbox().setLevel(levels.get(actuaLevel));
        setChanged();
        notifyObservers("NEW GAME");
    }

    private void resetLevels() {
        for (Level l : levels){
            l.reset();
        }
    }
}
