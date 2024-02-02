package Model.EntityModel.Enemies;

import Model.EntityModel.Player;
import Model.Level;
import Model.StateModels.Partita;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Questa classe crea e gestisce i nemici all'interno di un livello della partita. I nemici vengono creati a partire dalla matrice di interi data.
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
     * Costruttore della classe
     * @param level: il livello i cui nemici vengono gestiti da questa classe
     */
    public EnemySpawner(Level level){
        this.level = level;
        this.data = this.level.getData();
        enemies = new ArrayList<>();
        inactiveEnemies = new ArrayList<>();
        createEnemies();
    }

    /**
     * partendo da un matrice di interi, il metodo scorre quest'ultima e in base all'intero trovato viene creato un particolare nemico nelle rispettive posizioni.
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
     * Questo metodo aggiorna tutti i nemici
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
     * Controlla se uno dei nemici attivi è entrato in collisione con il player
     * @param player: il player
     */
    public void checkPlayerHit(Player player){
        for (Enemy e : enemies){
            e.playerHit(player);
        }
    }

    /**
     * Dato l'indice i, rimuove il nemico che si trova alla posizione i dell'array
     * @param i: indice dell'ArrayList in cui si trova il nemico da rimuovere
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
     * Questo metodo manda la prima notifica che contiene tutti i dati di tutti nemici
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
     * questo metodo viene chiamato quando si inizia una nuova partita. Ripristina gli stati della classe agli stati di partenza
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
