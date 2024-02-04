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
 *Questa classe gestisce le meccaniche della partita e tutte le entità che prendono parte ad essa.
 * Alla partita sono associati il Plaeyer, i Livelli e gli stati Win e GameOver poichè possono avvenire solo mentre si sta giocando una partita.
 * @see Model.StateModels.Stato
 * @author Guido Paluzzi, Matteo Santucci
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

    /**
     * Costruttore della classe
     * @param gameModel: L'istanza del GameModel
     */
    public Partita(GameModel gameModel) {
        super(gameModel);
        gameOver = new GameOver(this.gameModel);
        win = new Win(this.gameModel);
    }

    /**
     * Aggiorna lo stato della partita
     */
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
        if (actuaLevel == 3 || actuaLevel == 6){
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


    /**
     * Questo metodo gestisce la transizione al livello successivo
     */
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


    /**
     * Setta il Player e associa il player ai PowerUp
     * @param player: Un istanza della classe Player
     */
    public void setPlayer(Player player) {
        this.player = player;
        PowerUp.PLAYER = this.player;
    }

    /**
     * Setta tutti i livelli della partita
     * @param levels: tutti i livelli della partita
     */
    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
        player.getHitbox().setLevel(this.levels.get(actuaLevel));
        for (Level l : levels){
            l.setPlayer(player);
        }
    }

    /**
     * Questo metodo resetta le classi coinvolte nella partita
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

    /**
     * Questo metodo rinizia la partita dopo una vittoria o una sconfitt
     */
    public void restartGame() {
        resetLevels();
        cheat = false;
        player.reset();
        Player.VITE = 7;
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

    /**
     * Invia una notifica agli observer
     * @param arg: gli argomenti da inviare all'observer
     */
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
