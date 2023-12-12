package Model.EntityModel;

import Model.Level;
import Model.StateModels.Partita;
import View.EntitiesGraphics.RedEnemyGraphics;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

public class EnemySpawner extends Observable {
    private int[][] data;
    private boolean firstUpdate = true;
    private ArrayList<Enemy> inactiveEnemies;
    private Level level;
    private ArrayList<Enemy> enemies;

    public EnemySpawner(Level level){
        this.level = level;
        this.data = this.level.getData();
        enemies = new ArrayList<>();
        inactiveEnemies = new ArrayList<>();
        createEnemies();
        //enemies.add(new RedEnemy(5*16,(4*16) + 8, 16, 24));
        //enemies.get(0).getHitbox().setLevel(level);
    }

    private void createEnemies() {
        int size = 0;
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[y].length; x++){
                if (data[y][x] == 5){
                    enemies.add(new RedEnemy(x*16, y*16, 16, 24));
                    enemies.get(size).getHitbox().setLevel(level);
                    size++;
                }
            }
        }
    }

    public void update(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
            if (!enemies.get(i).isAlive()){

                despawnEnemy(i);
            }
        }
    }

    public void checkPlayerHit(Player player){
        for (Enemy e : enemies){
            e.playerHit(player);
        }
    }

    private void despawnEnemy(int i) {
        Enemy t = enemies.get(i);
        inactiveEnemies.add(t);
        enemies.remove(t);
        Partita.SCORE += t.getScore();
        System.out.println(Partita.SCORE);
        setChanged();
        notifyObservers(new int[] {t.getX(), t.getY(), t.getW(), t.getH()});
    }

    public void firstNotify(){
        int[][] message = enemies.stream().map(e -> e.toArr()).toArray(int[][]::new);
        setChanged();
        notifyObservers(message);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    public void reset() {
        for (Enemy e: inactiveEnemies){
            e.setAlive(true);
            enemies.add(e);
        }
        inactiveEnemies.clear();
        setChanged();
        notifyObservers("RESET");
    }
}
