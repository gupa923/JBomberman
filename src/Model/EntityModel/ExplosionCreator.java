package Model.EntityModel;

import java.util.ArrayList;

public class ExplosionCreator {
    public static int[][] CreateExplosionTiles(Bomb bomb){
        int[][] lvlData = bomb.getHitbox().getData();
        int x = bomb.getX()/16;
        int y = bomb.getY()/16;
        //result[0][0] = x;
        //result[0][1] = y;
        ArrayList<int[]> temp = new ArrayList<>();
        ArrayList<int[]> u = new ArrayList<>();
        ArrayList<int[]> d = new ArrayList<>();
        ArrayList<int[]> l = new ArrayList<>();
        ArrayList<int[]> r = new ArrayList<>();
        temp.add(new int[] {x, y});
        for (int c = 1; c <= Bomb.RANGE; c++ ){
            u.add(new int[] {x , y-c});
            d.add(new int[] {x , y+c});
            l.add(new int[] {x -c, y});
            r.add(new int[] {x + c, y});
        }
        AddValidTiles(u, temp, lvlData);
        AddValidTiles(d, temp, lvlData);
        AddValidTiles(l, temp, lvlData);
        AddValidTiles(r, temp, lvlData);

        return temp.stream().filter(p -> CheckOutOfBounds(p)).map(c -> MulTimes16(c)).toArray(int[][] :: new);
    }

    private static void AddValidTiles(ArrayList<int[]> l, ArrayList<int[]> result, int[][] lvlData){
        for (int[] p: l){
            if (!CheckValidity(p, lvlData))
                return;
            else{
                result.add(p);
            }
        }
    }

    private static boolean CheckOutOfBounds(int[] c){
        if (c[0] < 0 || c[0] >= 17)
            return false;
        if (c[1] < 0 || c[1] >= 13)
            return false;
        return true;
    }
    private static boolean CheckValidity(int[] pos, int[][] lvlData){
        try{
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
