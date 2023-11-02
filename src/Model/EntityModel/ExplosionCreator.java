package Model.EntityModel;

import java.util.ArrayList;

/**
 * questa classe contiene i metodi statici che consentono di creare l'esplosione
 */
public class ExplosionCreator {
    /**
     * questo metodo prende in ingresso una bomba e in base alla bomba passata in input crea un array che contiene le coordinate di tutte le tile coinvolte dall'esplosione
     *
     * ho usato gli stream
     * @param bomb
     * @return
     */
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

        return temp.stream()
                //.filter(p -> CheckOutOfBounds(p))
                .map(c -> MulTimes16(c)).toArray(int[][] :: new);
    }

    /**
     * questo metodo controlla se le tile sono valide: vede se una di esse corrisponde ad un muro. se cioè è false aggiunge la tile a result in caso contrario esce dalla funzione poichè nessuna delle tile successive deve essere aggiunta all'array
     *
     * @param l: lista di tile colpite da un esplosione in una direzione
     * @param result: la lista a cui appendere le tile valide
     * @param lvlData
     */
    private static void AddValidTiles(ArrayList<int[]> l, ArrayList<int[]> result, int[][] lvlData){
        for (int[] p: l){
            if (!CheckValidity(p, lvlData))
                return;
            else{
                result.add(p);
            }
        }
    }

//    private static boolean CheckOutOfBounds(int[] c){
//        if (c[0] < 0 || c[0] >= 17)
//            return false;
//        if (c[1] < 0 || c[1] >= 13)
//            return false;
//        return true;
//    }

    /**
     * controlla se la tile non è un muro
     * @param pos
     * @param lvlData
     * @return
     */
    private static boolean CheckValidity(int[] pos, int[][] lvlData){
        try{
            return lvlData[pos[1]][pos[0]] != 1;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     * moltiplca i valori di un array di dimensione 2 per 16
     * @param c
     * @return
     */
    private static int[] MulTimes16(int[] c){
        int nc0 = c[0] * 16;
        int nc1 = c[1] * 16;
        c[0] = nc0;
        c[1] = nc1;
        return c;
    }
}
