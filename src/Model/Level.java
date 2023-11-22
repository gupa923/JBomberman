package Model;

import Model.EntityModel.Obstacle;
import Model.EntityModel.Player;
import Model.EntityModel.PowerUp;
import Model.EntityModel.PowerUpType;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static Model.StateModels.Partita.SCORE;

/**
 * questa classe contiene i dati dei vari livelli.
 * ogni livello è composto da 17 tile in orizzontale e 13 in verticale.
 * il livello è rappresentato da una matrice di interi con 17 colonne e 13 righe, il valore di un elemento della matrice varie in base
 * al tipo di tile che deve rappresentare: il valore sarò 0 se si tratta di una tile in cui il giocatore può muoversi;
 * 1 se è una tile in cui il giocatore NON può muoversi e 2 se si tratta della tile che dà accesso al mondo successivo.
 *
 */
// TODO fare classe figlia che gestisce i livelli con i Boss.
public class Level extends Observable {
    public static PowerUpType[] pTypes = PowerUpType.values();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private int[][] data;
    private ArrayList<Obstacle> obstacles;
    private boolean firstUpdate = true;

    public Level(int[][] data){
        this.data = data;
        obstacles = new ArrayList<>();
        createObstacles();
    }


    public int[][] getData() {
        return data;
    }
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
        //powerUps.add(new PowerUp(7*16,5*16, PowerUpType.BOMB_UP ));
        //powerUps.add(new PowerUp(11*16, 5*16, PowerUpType.LIVE_UP));
        int[][] message = new int[powerUps.size()][3];
        for (int i = 0; i< powerUps.size(); i++){
            message[i] = powerUps.get(i).toArr();
        }
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

    public int[][] obsToArr(){
        ArrayList<int[]> res = new ArrayList<>();
        for (Obstacle o : obstacles){
            res.add(new int[] {o.getX(), o.getY()});
        }

        return res.stream().toArray(int[][] :: new);
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void explodeObstacle(int x, int y) {
        for (int j = 0; j < obstacles.size(); j++){
            if (obstacles.get(j).getX() == x && obstacles.get(j).getY() == y){
                obstacles.get(j).setHit(true);
                Player.OBSTACLE_DESTROYED ++;
                System.out.println(Player.OBSTACLE_DESTROYED);
                if (Player.OBSTACLE_DESTROYED%10 == 0 && Player.OBSTACLE_DESTROYED != 0) {
                    SCORE += 50;
                    System.out.println(SCORE);
                }

                setChanged();
                notifyObservers(new int[] {x, y});
                return;
            }
        }
    }

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

    public void reset() {
        createObstacles();
        setChanged();
        notifyObservers(obsToArr());

        createPowerUp();

    }
}
