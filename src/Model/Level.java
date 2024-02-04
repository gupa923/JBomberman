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
 * Questa classe gestisce il livello e tutte le sue componenti cioè i nemici, gli ostacoli e i powerup. Ogni livello è costruito a partire da una matrice di interi
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
     * costruttore della classe level.
     * @param data: mappa del livello sotto la forma di una matrice di interi
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
     * Aggiorna lo stato di tutti gli oggetti del livello
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
     * Questo metodo gestisce l'esplosione degli ostacoli
     * @param x: coordinata dell'ostacolo
     * @param y: coordinata dell'ostacole
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
     * Questo metodo rimuove gli ostacoli dal livello dopo che sono esplosi
     * @param obstacle: l'ostacolo da rimuovere
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
     * Questo metodo resetta il livello e tutti i suoi oggetti
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
