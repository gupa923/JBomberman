package Model.EntityModel.Enemies;

import Model.EntityModel.Player;
import Model.Level;
import Model.StateModels.Partita;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class creates and manages enemies within a game level. Enemies are created from the given integer matrix.
 * @see Level
 * @see Enemy
 * @see Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public class EnemySpawner extends Observable {
    private final int[][] data;
    private final ArrayList<Enemy> inactiveEnemies;
    private final Level level;
    private final ArrayList<Enemy> enemies;

    /**
     * Class Constructor
     * @param level: the level whose enemies are handled by this class
     */
    public EnemySpawner(Level level){
        this.level = level;
        this.data = this.level.getData();
        enemies = new ArrayList<>();
        inactiveEnemies = new ArrayList<>();
        createEnemies();
    }

    /**
     * starting from a matrix of integers, the method scrolls through the latter and based on the integer found a particular enemy is created in the respective positions.
     */
    private void createEnemies() {
        int size = 0;
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[y].length; x++){
                if (data[y][x] == 5){
                    enemies.add(new RedEnemy(x*16, y*16, 16, 24));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }else if (data[y][x] == 6){
                    enemies.add(new YellowEnemy(x*16, y*16, 16, 24));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }else if (data[y][x] == 7){
                    enemies.add(new BrownEnemy(x*16, y*16, 16, 24));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }else if (data[y][x] == 8){
                    enemies.add(new BlueEnemy(x*16, y*16, 16, 16));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }else if (data[y][x] == 66){
                    enemies.add(new ClownBoss(x*16, y*16, 110, 105));
                    enemies.get(size).getHitbox().setLevel(level);
                }else if (data[y][x] == 9){
                    enemies.add(new LastEnemy(x*16, y*16, 16, 22));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }else if (data[y][x] == 10){
                    enemies.add(new FinalBoss(x*16, y*16, 74, 74));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }
            }
        }
    }

    /**
     * This method updates all enemies
     */
    public void update(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
            if (!enemies.get(i).isAlive()){

                despawnEnemy(i);
            }
        }
    }

    /**
     * Checks if one of the active enemies has collided with the player
     * @param player: il player
     */
    public void checkPlayerHit(Player player){
        for (Enemy e : enemies){
            e.playerHit(player);
        }
    }

    /**
     * Given the index i, remove the enemy located at position i in the array
     * @param i: index of the ArrayList in which the enemy to be removed is located
     */
    private void despawnEnemy(int i) {
        Enemy t = enemies.get(i);
        t.setAlive(true);
        t.resetHP();
        inactiveEnemies.add(t);
        enemies.remove(t);
        Partita.SCORE += t.getScore();
        setChanged();
        notifyObservers(t.getScore());
        setChanged();
        notifyObservers(t.toArr());
    }

    /**
     * This method sends the first notification which contains all the data of all enemies
     */
    public void firstNotify(){
        int[][] message = enemies.stream().map(e -> e.toArr()).toArray(int[][]::new);
        setChanged();
        notifyObservers(message);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * this method is called when starting a new game. Resets the class states to their starting states
     */
    public void reset() {
        for (Enemy e: inactiveEnemies){
            e.setAlive(true);
            e.resetPos();
            e.resetHP();
            enemies.add(e);
        }
        inactiveEnemies.clear();
        setChanged();
        notifyObservers("RESET");
    }
}
