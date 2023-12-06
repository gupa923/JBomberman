package View.EntitiesGraphics;

import View.UtilityInterfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EnemyGraphicsSpawner implements Observer , Drawable {
    private ArrayList<EnemyGraphics> enemyGraphics;

    public EnemyGraphicsSpawner(){
        enemyGraphics = new ArrayList<>();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof int[]){
            int[] t = (int[]) arg;
            enemyGraphics.add(new RedEnemyGraphics(t[0], t[1], t[2], t[3]));
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
