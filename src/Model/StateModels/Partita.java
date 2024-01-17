package Model.StateModels;

import Model.EntityModel.Obstacle;
import Model.EntityModel.Player;
import Model.EntityModel.PowerUp;
import Model.GameModel;
import Model.Level;
import Model.Stati;

import java.util.ArrayList;

import static Model.GameModel.USER;

/**
 * la classe partita gestisce la logica della partita
 *
 */
public class Partita extends Stato{
    public static int SCORE = 0;
    private Player player;
    private int actuaLevel;
    private ArrayList<Level> levels;
    private final GameOver gameOver;
    private final Win win;
    private boolean gameCompleted;
    private boolean cheat = false;
    private boolean restarted = true;

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
                    player.setTransition(true);
                    player.sendMessage("NEXT LEVEL");
                    if (player.getTransitionTick() >= 180) {
                        if (checkGameCompleted()) {
                            return;
                        }
                        nextLevel();
                        cheat = false;
                        return;
                    }
                }
                setChanged();
                notifyObservers("PLAYING");
            } else {
                if (Player.VITE <= 0) {
                    //Player.VITE = 7;
                    USER.setRecord(SCORE);
                    SCORE = 0;
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
        if (cheat){
            return true;
        }
        if (actuaLevel == 3){
            //                if (player.getHitbox().x / 16 == 11 && player.getHitbox().y/16 == 7){
            //                    return true;
            //                }
            return levels.get(actuaLevel).getEnemySpawner().getEnemies().isEmpty();
        } else {
            if (!levels.get(actuaLevel).getEnemySpawner().getEnemies().isEmpty()) {
                return false;
            }
            if (levels.get(actuaLevel).getData()[player.getHitbox().y / 16][player.getHitbox().x / 16] == 2) {
                for (Obstacle o : levels.get(actuaLevel).getObstacles()) {
                    if (o.getX() / 16 == player.getHitbox().x / 16 && o.getY() / 16 == player.getHitbox().y / 16) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;

    }

    private boolean checkGameCompleted(){
        if (actuaLevel +1 >= levels.size()){
            gameCompleted = true;
            gameModel.setStatoAttuale(Stati.WIN);

            USER.setVictories();
            USER.setRecord(SCORE);
            setChanged();
            notifyObservers("WIN");
            return true;
        }
        return false;
    }


    public void nextLevel() {
        actuaLevel ++;
        if (actuaLevel >= levels.size()){
            checkGameCompleted();
            return;
        }
        player.getHitbox().setLevel(levels.get(actuaLevel));
        player.resetPos();
        player.loadBufferPUps();
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
        Player.VITE = 7;
        resetLevels();
        actuaLevel = 0;
        SCORE = 0;
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

    public void sendMessage(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    public Win getWin() {
        return win;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

    public boolean isRestarted() {
        return restarted;
    }

    public void setRestarted(boolean restarted) {
        this.restarted = restarted;
    }
}
