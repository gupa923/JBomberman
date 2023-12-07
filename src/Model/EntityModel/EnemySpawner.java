package Model.EntityModel;

import Model.Level;
import View.EntitiesGraphics.RedEnemyGraphics;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update();
            if (!enemies.get(i).isAlive()){

                despawnEnemy(i);
            }
        }
    }

    private void despawnEnemy(int i) {
        Enemy t = enemies.get(i);
        enemies.remove(t);
        setChanged();
        notifyObservers(new int[] {t.getX(), t.getY(), t.getW(), t.getH(), 666});
    }

    public void firstNotify(){
        setChanged();
        notifyObservers(enemies.get(0).toArr());
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


}
