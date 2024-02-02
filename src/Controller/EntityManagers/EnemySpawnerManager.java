package Controller.EntityManagers;

import Controller.LevelManager;
import Model.EntityModel.Enemies.ClownBoss;
import Model.EntityModel.Enemies.EnemySpawner;
import Model.EntityModel.Enemies.FinalBoss;
import Model.Level;
import View.EntitiesGraphics.EnemyGraphics.ClownBossGraphics;
import View.EntitiesGraphics.EnemyGraphics.EnemyGraphicsSpawner;
import View.LevelGraphics;

import java.util.ArrayList;

/**
 * Questa classe gestisce la creazione dei EnemySpawner dei rispettivi livelli. Associa ad ogni Enemy il rispettivo observer.
 * @see EnemySpawner
 * @see EnemyGraphicsSpawner
 * @see LevelManager
 * @author Guido Paluzzi, Matteo Santucci
 */
public class EnemySpawnerManager {
     private final LevelManager levelManager;
     private final ArrayList<Level> lvls;
     private final ArrayList<LevelGraphics> lvlsG;

    /**
     * Costruttore della classe
     */
    public EnemySpawnerManager(){
         levelManager = LevelManager.getInstance();
         lvls = levelManager.getLevels();
         lvlsG = levelManager.getLevelGraphicsArrayList();

         createEnemySpawners(lvls, lvlsG);
    }

    /**
     * Per Ogni livello crea il rispettivo EnemySpawner e per ogni LevelGraphics crea il rispettivo EnemyGraphicsSpawner. Infine ad ogni Enemy, creato dall'EnemySpawer, viene aggiunto il rispettivo observer creato dal'EnemyGraphicsSpawner
     * @param lvls: Array dei livelli del gioco
     * @param lvlsG: Array della rappresentazione grafica dei livelli del gioco
     */
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
         }
    }

}
