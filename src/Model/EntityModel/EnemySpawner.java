package Model.EntityModel;

import Model.Level;

import java.util.ArrayList;
import java.util.Observable;

public class EnemySpawner extends Observable {
    private int[][] data;
    private boolean firstUpdate = true;

    private ArrayList<Enemy> enemies;

    public EnemySpawner(Level level){
        this.data = data;
        enemies = new ArrayList<>();
        enemies.add(new RedEnemy(5*16,(4*16) + 8, 16, 24));
        enemies.get(0).getHitbox().setLevel(level);
    }

    public void update(){
        for (Enemy e : enemies){
            e.update();
        }
    }

    public void firstNotify(){
        setChanged();
        notifyObservers(enemies.get(0).toArr());
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
