package Controller;

/**
 * è un enum con tutti i possibili stati del gioco che verranno cambiati l
 * in base a determinate azioni fatte dall'utente
 */

//TODO avremo una classe per ogni stato (una nel model e una nella view) in base allo stato attuale verra aggiornata solo una parte del codice
public enum Stati {

    PARTITA, MENU, LOGIN, IMPOSTAZIONI, QUIT;

    public static Stati stato = MENU;
}
