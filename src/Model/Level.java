package Model;

import Model.EntityModel.Enemies.EnemySpawner;
import Model.EntityModel.Obstacle;
import Model.EntityModel.Player;
import Model.EntityModel.PowerUp;
import Model.EntityModel.PowerUpType;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static Model.StateModels.Partita.SCORE;

/**
 * This class manages the level and all its components, i.e. enemies, obstacles and powerups. Each level is built from a matrix of integers
 * @see PowerUp
 * @see Obstacle
 * @see java.util.Observable
 * @author Guido Paluzzi, Matteo Santucci
 */
public class Level extends Observable {
    public static PowerUpType[] pTypes = PowerUpType.values();
    private final ArrayList<PowerUp> powerUps = new ArrayList<>();
    private final int[][] data;
    private final ArrayList<Obstacle> obstacles;
    private boolean firstUpdate = true;
    private EnemySpawner enemySpawner;
    private Player player;

    /**
     * constructor of the level class.
     * @param data: level map in the form of a matrix of integers
     */
    public Level(int[][] data){
        this.data = data;
        obstacles = new ArrayList<>();
        createObstacles();
    }


    public int[][] getData() {
        return data;
    }

    /**
     * Updates the state of all objects in the level
     */
    public void update(){
        if (firstUpdate){
            setChanged();
            notifyObservers(obsToArr());
            firstUpdate = false;
            createPowerUp();
        }
        for (int i = 0; i < obstacles.size(); i++){
            obstacles.get(i).update();
        }
        for (int i = 0; i < powerUps.size();i++){
            powerUps.get(i).update();
            if (!powerUps.get(i).isActive()){
                despawnPowerUp(i);
            }
        }
        enemySpawner.update();
        enemySpawner.checkPlayerHit(player);

    }

    private void despawnPowerUp(int i) {
        PowerUp temp = powerUps.remove(i);
        setChanged();
        notifyObservers(new int[] {temp.getX(), temp.getY(), temp.getId()});
    }

    private void createPowerUp() {
        Random r = new Random();
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[y].length; x++){
                if (data[y][x] == 4){
                    powerUps.add(new PowerUp(x*16, y*16, pTypes[r.nextInt(0, pTypes.length)]));
                }
            }
        }
        int[][] message = powerUps.stream().map(PowerUp::toArr).toArray(int[][]::new);
        setChanged();
        notifyObservers(message);
    }

    private void createObstacles(){
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[y].length; x++){
                if (data[y][x] == 3 || data[y][x] == 2 || data[y][x] == 4){
                    obstacles.add(new Obstacle(this,x*16, y*16));
                }
            }
        }
    }

    private int[][] obsToArr(){
        return obstacles.stream().map(o -> new int[]{o.getX(), o.getY()}).toArray(int[][]::new);
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * This method handles the explosion of obstacles
     * @param x: coordinate of the obstacle
     * @param y: coordinate of the obstacle
     */
    public void explodeObstacle(int x, int y) {
        for (int j = 0; j < obstacles.size(); j++){
            if (obstacles.get(j).getX() == x && obstacles.get(j).getY() == y){
                obstacles.get(j).setHit(true);
                Player.OBSTACLE_DESTROYED ++;
                if (Player.OBSTACLE_DESTROYED%10 == 0 && Player.OBSTACLE_DESTROYED != 0) {
                    SCORE += 50;
                    setChanged();
                    notifyObservers(50);
                }

                setChanged();
                notifyObservers(new int[] {x, y});
                return;
            }
        }
    }

    /**
     * This method removes obstacles from the level after they explode
     * @param obstacle: the obstacle to be removed
     */
    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
        for (PowerUp p : powerUps){
            if (p.getX() == obstacle.getX() && p.getY() == obstacle.getY()){
                p.setOver(false);
                break;
            }
        }
        setChanged();
        notifyObservers(new int[] {obstacle.getX(), obstacle.getY()});
    }

    /**
     * This method resets the level and all its objects
     */
    public void reset() {
        obstacles.clear();
        powerUps.clear();
        createObstacles();
        setChanged();
        notifyObservers(obsToArr());

        createPowerUp();
        enemySpawner.reset();

    }

    public EnemySpawner getEnemySpawner() {
        return enemySpawner;
    }

    public void setEnemySpawner(EnemySpawner enemySpawner) {
        this.enemySpawner = enemySpawner;
        this.enemySpawner.firstNotify();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
