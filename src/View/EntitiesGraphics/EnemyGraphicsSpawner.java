package View.EntitiesGraphics;

import Model.EntityModel.Enemies.RedEnemy;
import Model.EntityModel.Enemies.YellowEnemy;
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
            if (t[4] == 1) {
                RedEnemyGraphics e = new RedEnemyGraphics(t[0], t[1], t[2], t[3]);
                for (int i = 0; i < enemyGraphics.size(); i++){
                    if (enemyGraphics.get(i).equals(e)){
                        inactiveEnemies.add(enemyGraphics.get(i));
                        enemyGraphics.remove(e);
                    }
                }
            }else if (t[4] == 2){
                YellowEnemyGraphics e = new YellowEnemyGraphics(t[0], t[1], t[2], t[3]);
                for (int i = 0; i < enemyGraphics.size(); i++){
                    if (enemyGraphics.get(i).equals(e)){
                        inactiveEnemies.add(enemyGraphics.get(i));
                        enemyGraphics.remove(e);
                    }
                }
            }


        }else if (arg instanceof int[][]) {
            int[][] tm = (int[][] ) arg;
            for (int i = 0; i < tm.length; i++){
                int[] t = tm[i];
                int type = t[4];
                if (type == 1) {
                    enemyGraphics.add(new RedEnemyGraphics(t[0], t[1], t[2], t[3]));
                }
                else if (type == 2){
                    enemyGraphics.add(new YellowEnemyGraphics(t[0], t[1], t[2], t[3]));
                }
            }
        }else if (arg instanceof String){
            String temp = (String) arg;
            for (EnemyGraphics e: inactiveEnemies){
                e.resetPos();
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
