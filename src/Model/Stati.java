package Model;

/**
 * è un enum con tutti i possibili stati del gioco che verranno cambiati l
 * in base a determinate azioni fatte dall'utente
 */

public enum Stati {

    PARTITA,
    MENU,
    LOGIN,
    SETTINGS,
    COMMAND_INFO,
    GAME_OVER,
    WIN,
    QUIT,
    PAUSE;

    public static Stati stato = MENU;
}
