package View.EntitiesGraphics;

import Model.EntityModel.Enemy;
import View.UtilityInterfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EnemyGraphicsSpawner implements Observer , Drawable {
    private ArrayList<EnemyGraphics> enemyGraphics, inactiveEnemies;

    public EnemyGraphicsSpawner(){
        enemyGraphics = new ArrayList<>();
        inactiveEnemies = new ArrayList<>();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof int[]){
            int[] t = (int[]) arg;
            EnemyGraphics e = new RedEnemyGraphics(t[0], t[1], t[2], t[3]);
            for (int i = 0; i < enemyGraphics.size(); i++){
                if (enemyGraphics.get(i).equals(e)){
                    inactiveEnemies.add(enemyGraphics.get(i));
                    enemyGraphics.remove(e);
                }
            }

        }else if (arg instanceof int[][]) {
            int[][] tm = (int[][] ) arg;
            for (int i = 0; i < tm.length; i++){
                int[] t = tm[i];
                enemyGraphics.add(new RedEnemyGraphics(t[0], t[1], t[2], t[3]));
            }
        }else if (arg instanceof String){
            String temp = (String) arg;
            for (EnemyGraphics e: inactiveEnemies){
                enemyGraphics.add(e);
            }
            inactiveEnemies.clear();

        }
    }

    @Override
    public void draw(Graphics g) {
        for (EnemyGraphics e : enemyGraphics){
            e.draw(g);
        }
    }

    public ArrayList<EnemyGraphics> getEnemyGraphics() {
        return enemyGraphics;
    }
}
