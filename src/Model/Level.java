package Model;

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

    public Level(int[][] data){
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }
}
