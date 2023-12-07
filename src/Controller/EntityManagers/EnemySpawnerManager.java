package Controller.EntityManagers;

import Controller.LevelManager;
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

         EnemySpawner lvl1Spawner = new EnemySpawner(lvls.get(0));
         EnemyGraphicsSpawner lvl1GSpawner = new EnemyGraphicsSpawner();
         lvl1Spawner.addObserver(lvl1GSpawner);
         lvls.get(0).setEnemySpawner(lvl1Spawner);
         lvlsG.get(0).setEnemyGraphicsSpawner(lvl1GSpawner);
         addEnemyObs(lvl1Spawner, lvl1GSpawner);
     }

    private void addEnemyObs(EnemySpawner lvl1Spawner, EnemyGraphicsSpawner lvl1GSpawner) {
         for (int i = 0; i < lvl1Spawner.getEnemies().size(); i++){
            // System.out.println(lvl1Spawner.getEnemies().size());
           //  System.out.println(lvl1GSpawner.getEnemyGraphics().size());
             lvl1Spawner.getEnemies().get(i).addObserver(lvl1GSpawner.getEnemyGraphics().get(i));
             lvl1GSpawner.getEnemyGraphics().get(i).setHitbox(lvl1Spawner.getEnemies().get(i).getHitbox());
         }
    }

}
