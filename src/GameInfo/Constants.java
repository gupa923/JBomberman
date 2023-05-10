package GameInfo;

/**
 * questa classe conterra altre sottoclassi che contengono le costanti del gioco
 *
 *
 *
 */
public class Constants {

    public static final float GAME_SCALE = 4.0f;
    /**
     * questa classe contiene la dimensione della mappa di gioco, che per adesso è la dimensione di tutta la schermata
     *
     *
     */
    public static class BoardConstants{
        public static final int  TILES_ON_X = 17;
        public static final int TILES_ON_Y = 13;
        public static final int TILES_SIZE = 16;
        public static final int BOARD_SIZE_X = (int)(TILES_ON_X * GAME_SCALE * TILES_SIZE);
        public static final int BOARD_SIZE_Y = (int)(TILES_ON_Y * GAME_SCALE * TILES_SIZE);
    }


    /**
     * questa classe contiene le possibili direzioni in cui si può muovere il giocatore
     * e anche i nemici
     *
     *
     */
    public static class Direction{
        public static final int UP = 1;
        public static final int DOWN = 2;
        public static final int LEFT = 3;
        public static final int RIGHT = 4;
    }
}
