package Controller;

import Model.EntityModel.EnemySpawner;
import Model.Level;
import View.EntitiesGraphics.EnemyGraphicsSpawner;
import View.LevelGraphics;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EnemySpawnerManager {
     private LevelManager levelManager;
     private ArrayList<Level> lvls;
     private ArrayList<LevelGraphics> lvlsG;

     public EnemySpawnerManager(){
         levelManager = LevelManager.getInstance();
         lvls = levelManager.getLevels();
         lvlsG = levelManager.getLevelGraphicsArrayList();

         EnemySpawner lvl1Spawner = new EnemySpawner(lvls.get(0).getData());
         EnemyGraphicsSpawner lvl1GSpawner = new EnemyGraphicsSpawner();
         lvl1Spawner.addObserver(lvl1GSpawner);
         lvls.get(0).setEnemySpawner(lvl1Spawner);
         lvlsG.get(0).setEnemyGraphicsSpawner(lvl1GSpawner);
     }
}
