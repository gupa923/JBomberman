package View.EntitiesGraphics.EnemyGraphics;

import View.StatesGraphics.MatchGraphics;
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
                        enemyGraphics.get(i).setDeath(false);
                        inactiveEnemies.add(enemyGraphics.get(i));
                        enemyGraphics.remove(e);
                    }
                }
            }else if (t[4] == 2){
                YellowEnemyGraphics e = new YellowEnemyGraphics(t[0], t[1], t[2], t[3]);
                for (int i = 0; i < enemyGraphics.size(); i++){
                    if (enemyGraphics.get(i).equals(e)){
                        enemyGraphics.get(i).setDeath(false);
                        inactiveEnemies.add(enemyGraphics.get(i));
                        enemyGraphics.remove(e);
                    }
                }
            }else if (t[4] == 3){
                BrownEnemyGraphics e = new BrownEnemyGraphics(t[0], t[1], t[2], t[3]);
                for (int i = 0; i < enemyGraphics.size(); i++){
                    if (enemyGraphics.get(i).equals(e)){
                        enemyGraphics.get(i).setDeath(false);
                        inactiveEnemies.add(enemyGraphics.get(i));
                        enemyGraphics.remove(e);
                    }
                }
            }else if (t[4] == 4) {
                BlueEnemyGraphics e = new BlueEnemyGraphics(t[0], t[1], t[2], t[3]);
                for (int i = 0; i < enemyGraphics.size(); i++) {
                    if (enemyGraphics.get(i).equals(e)) {
                        enemyGraphics.get(i).setDeath(false);
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
                else if (type == 3){
                    enemyGraphics.add(new BrownEnemyGraphics(t[0], t[1], t[2], t[3]));
                }
                else if (type == 4){
                    enemyGraphics.add(new BlueEnemyGraphics(t[0], t[1], t[2], t[3]));

                }
            }
        }else if (arg instanceof String){
            String temp = (String) arg;
            for (EnemyGraphics e: inactiveEnemies){
                e.resetPos();
                enemyGraphics.add(e);
            }
            inactiveEnemies.clear();

        }else if (arg instanceof Integer){
            int i = (Integer ) arg;
            MatchGraphics.SCORE_VIEW += i;
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
