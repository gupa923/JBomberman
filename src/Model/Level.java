package Model;

import Model.EntityModel.Obstacle;

import java.util.ArrayList;

/**
 * questa classe contiene i dati dei vari livelli.
 * ogni livello è composto da 17 tile in orizzontale e 13 in verticale.
 * il livello è rappresentato da una matrice di interi con 17 colonne e 13 righe, il valore di un elemento della matrice varie in base
 * al tipo di tile che deve rappresentare: il valore sarò 0 se si tratta di una tile in cui il giocatore può muoversi;
 * 1 se è una tile in cui il giocatore NON può muoversi e 2 se si tratta della tile che dà accesso al mondo successivo.
 *
 */
// TODO fare classe figlia che gestisce i livelli con i Boss.
public class Level {
    private int[][] data;
    private ArrayList<Obstacle> obstacles;

    public Level(int[][] data){
        this.data = data;
        obstacles = new ArrayList<>();
        createObstacles();
    }

    public int[][] getData() {
        return data;
    }
    public void update(){
        for (Obstacle o: obstacles){
            o.update();
        }
    }

    private void createObstacles(){
        for (int y = 0; y < data.length; y++){
            for (int x = 0; x < data[y].length; x++){
                if (data[y][x] == 3){
                    obstacles.add(new Obstacle(x*16, y*16));
                }
            }
        }
    }

    public int[][] obsToArr(){
        ArrayList<int[]> res = new ArrayList<>();
        for (Obstacle o : obstacles){
            res.add(new int[] {o.getX(), o.getY()});
        }

        return res.stream().toArray(int[][] :: new);
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
