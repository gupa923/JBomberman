package Controller.EntityManagers;

import Controller.LevelManager;
import Model.EntityModel.Enemies.EnemySpawner;
import Model.Level;
import View.EntitiesGraphics.EnemyGraphics.EnemyGraphicsSpawner;
import View.LevelGraphics;

import java.util.ArrayList;

public class EnemySpawnerManager {
     private LevelManager levelManager;
     private ArrayList<Level> lvls;
     private ArrayList<LevelGraphics> lvlsG;

     public EnemySpawnerManager(){
         levelManager = LevelManager.getInstance();
         lvls = levelManager.getLevels();
         lvlsG = levelManager.getLevelGraphicsArrayList();

         createEnemySpawners(lvls, lvlsG);
     }

    private void createEnemySpawners(ArrayList<Level> lvls, ArrayList<LevelGraphics> lvlsG) {
         for (int i = 0;i < lvls.size(); i++) {
             EnemySpawner lvl1Spawner = new EnemySpawner(lvls.get(i));
             EnemyGraphicsSpawner lvl1GSpawner = new EnemyGraphicsSpawner();
             lvl1Spawner.addObserver(lvl1GSpawner);
             lvls.get(i).setEnemySpawner(lvl1Spawner);
             lvlsG.get(i).setEnemyGraphicsSpawner(lvl1GSpawner);
             addEnemyObs(lvl1Spawner, lvl1GSpawner);
         }
    }


    private void addEnemyObs(EnemySpawner lvl1Spawner, EnemyGraphicsSpawner lvl1GSpawner) {
         for (int i = 0; i < lvl1Spawner.getEnemies().size(); i++){

             lvl1Spawner.getEnemies().get(i).addObserver(lvl1GSpawner.getEnemyGraphics().get(i));
             lvl1GSpawner.getEnemyGraphics().get(i).setHitbox(lvl1Spawner.getEnemies().get(i).getHitbox());
         }
    }

}
