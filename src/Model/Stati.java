package Model;

/**
 * Questa Enum contiene tutti gli stati del gioco
 * @author Guido Paluzzi, Matteo Santucci
 */

public enum Stati {

    /**
     * Questo stato indica che ci si trova nella partita
     */
    PARTITA,
    /**
     * Questo stato corrisponde al menù iniziale
     */
    MENU,
    /**
     * Questo stato corrisponde allo stato di login
     */
    LOGIN,
    /**
     * Questo stato corrisponde al menù delle impostazione
     */
    SETTINGS,
    /**
     * Questo stato corrisponde al menù dei comandi
     */
    COMMAND_INFO,
    /**
     * Questo stato si verifica quando il giocatoreperde la partita
     */
    GAME_OVER,
    /**
     * Questo stato si verifica quando il giocatore vince la partita
     */
    WIN,
    /**
     * Questo stato corrisponde al menù delle statistiche dell'utente
     */
    STATS,
    /**
     * Questo stato è attivo quando il giocatore mette in pausa il gioco
     */
    PAUSE;

    public static Stati stato = MENU;
}
