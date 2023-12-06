package Model.EntityModel;

import java.util.ArrayList;
import java.util.Observable;

public class EnemySpawner extends Observable {
    private int[][] data;
    private boolean firstUpdate = true;

    private ArrayList<Enemy> enemies;

    public EnemySpawner(int[][] data){
        this.data = data;
        enemies = new ArrayList<>();
        enemies.add(new RedEnemy(5*16,(4*16) + 8, 16, 24));
    }

    public void update(){

    }

    public void firstNotify(){
        setChanged();
        notifyObservers(enemies.get(0).toArr());
    }

}
