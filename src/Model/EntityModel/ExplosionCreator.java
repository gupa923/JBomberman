package Model.EntityModel;

import Model.Level;

import java.util.ArrayList;

/**
 * This class contains static methods for creating explosions
 * @author Guido Paluzzi, Matteo Santucci
 */
public class ExplosionCreator {
    /**
     * The explosion radius of the bomb
     */
    public static int RANGE = 1;

    /**
     * Given an incoming bomb creates the explosion
     * @param bomb: Exploding Bomb
     * @return: An integer matrix containing the coordinates of the tiles involved in the explosion.
     */
    public static int[][] CreateExplosionTiles(Bomb bomb){
        Level lvl = bomb.getHitbox().getLevel();
        int x = bomb.getX()/16;
        int y = bomb.getY()/16;
        ArrayList<int[]> temp = new ArrayList<>();
        ArrayList<int[]> u = new ArrayList<>();
        ArrayList<int[]> d = new ArrayList<>();
        ArrayList<int[]> l = new ArrayList<>();
        ArrayList<int[]> r = new ArrayList<>();
        temp.add(new int[] {x, y});
        for (int c = 1; c <= RANGE; c++ ){
            u.add(new int[] {x , y-c});
            d.add(new int[] {x , y+c});
            l.add(new int[] {x -c, y});
            r.add(new int[] {x + c, y});
        }
        AddValidTiles(u, temp, lvl);
        AddValidTiles(d, temp, lvl);
        AddValidTiles(l, temp, lvl);
        AddValidTiles(r, temp, lvl);

        return temp.stream().map(c -> MulTimes16(c)).toArray(int[][] :: new);
    }

    /**
     * This method checks if the tiles involved in the explosion are valid, i.e. if they are not walls or if they are outside the map
     * @param l: list of tiles hit by an explosion in one direction
     * @param result: the list to which to append valid tiles
     */
    private static void AddValidTiles(ArrayList<int[]> l, ArrayList<int[]> result, Level lvl){
        for (int[] p: l){
            if (!CheckValidity(p, lvl)) {
                result.add(new int[]{0, 0});
                return;
            }else{
                result.add(p);
            }
        }
    }

    private static boolean CheckValidity(int[] pos, Level lvl){
        int[][] lvlData = lvl.getData();
        try{
            if (lvlData[pos[1]][pos[0]] != 1){
                if (lvlData[pos[1]][pos[0]] == 3 || lvlData[pos[1]][pos[0]] == 2 || lvlData[pos[1]][pos[0]] == 4){
                    for (int x = 0; x < lvl.getObstacles().size(); x++){
                        if (lvl.getObstacles().get(x).getX() == pos[0]*16 && lvl.getObstacles().get(x).getY() == pos[1]*16){
                            lvl.explodeObstacle(pos[0]*16, pos[1]*16);
                            return false;
                        }
                    }
                }
                return true;
            }
            return lvlData[pos[1]][pos[0]] != 1;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    private static int[] MulTimes16(int[] c){
        int nc0 = c[0] * 16;
        int nc1 = c[1] * 16;
        c[0] = nc0;
        c[1] = nc1;
        return c;
    }
}
