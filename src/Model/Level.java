package Model;

import Model.EntityModel.*;
import Model.EntityModel.Enemies.EnemySpawner;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static Model.StateModels.Partita.SCORE;

/**
 * questa classe gestisce il livello e tutti i suoi oggetti. Contiene la mappa sotto forma di matrice di interi di 17 colonne e 13 righe, inoltre contiene un ArrayList contenete i power up, una contenente gli ostacoli e una contenete i nemici
 * @see PowerUp
 * @see Obstacle
 * @see java.util.Observable
 * @author gupa9
 */
// TODO fare classe figlia che gestisce i livelli con i Boss.
public class Level extends Observable {
    public static PowerUpType[] pTypes = PowerUpType.values();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private int[][] data;
    private ArrayList<Obstacle> obstacles;
    private boolean firstUpdate = true;
    private EnemySpawner enemySpawner;
    private Player player;

    /**
     * costruttore della classe level.
     * @param data: mappa del livello sotto la forma di una matrice
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
     * inizialmente questo metodo crea gli ostacoli e i power up, poi itera sugli ostacoli e li aggiorna. infine itera sui power up e se non sono attivi li rimuove
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

    /**
     * rimuove il power up all'indice i dell'array di power up ed invia una notifica agli observer con le coordinate del power up cancellato
     * @param i: indice del power up da rimuovere
     */
    private void despawnPowerUp(int i) {
        PowerUp temp = powerUps.remove(i);
        setChanged();
        notifyObservers(new int[] {temp.getX(), temp.getY(), temp.getId()});
    }

    /**
     * questo metodo crea i power up. Il metodo cicla sulla matrice data e se l'intero vale 4 allora crea un power up alle posizioni di quell'intero
     */
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

    /**
     * questo metodo crea gli ostacoli. itera sulla matrice e posizione un ostacolo dove questa vale 2, 3, o 4
     * */
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

    public void setData(int[][] data) {
        this.data = data;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * quando viene chiamato questo metodo cerca nell'array l'ostacolo colpito e imposta il suo booleano hit = true.
     * @param x: coordinata dell'ostacolo
     * @param y: coordinata dell'ostacole
     */
    public void explodeObstacle(int x, int y) {
        for (int j = 0; j < obstacles.size(); j++){
            if (obstacles.get(j).getX() == x && obstacles.get(j).getY() == y){
                obstacles.get(j).setHit(true);
                Player.OBSTACLE_DESTROYED ++;
                System.out.println(Player.OBSTACLE_DESTROYED);
                if (Player.OBSTACLE_DESTROYED%10 == 0 && Player.OBSTACLE_DESTROYED != 0) {
                    SCORE += 50;
                    System.out.println(SCORE);
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
     * questo metodo rimuove l'ostacolo dall'array
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
     * questo metodo inizializza nuovamente il livello.
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
