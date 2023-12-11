package Model.EntityModel;

/**
 * Questa enum rappresenta i possibili tipi di power up presenti nel gioco
 * @author gupa9
 */
public enum PowerUpType {

    /**
     * aumenta il numero massimo di bombe al valore contenuto in val
     */
    BOMB_UP("BOMB_UP", 1, 0),
    /**
     * aumenta il numero di vite al valore presente nel campo val
     */
    LIVE_UP("LIVE_UP", 1, 1),
    /**
     * aggiunge il valore contentuto nel campoval allo score della partita
     */
    CAKE ("CAKE", 100, 2),
    /**
     * aggiunge il valore del campo val al numreo di HP del giocatore
     */
    HP_PLUS("HP_PLUS", 1, 3),
    /**
     * toglie il valoew contenuto in val al numero di HP del giocatore
     */
    HP_MEN("HP_MEN", -1, 4),
    /**
     * rende il giocatore immortale per un breve periodo di tempo
     */
    IMMORTALITY("IMMORTALITY", 0, 5),
    /**
     * permette al giocatore di camminare sopra i blocchi distruttibili cioè ostacoli e bombe
     */
    WALK_OVER("WALK_OVER", 0, 6),
    /**
     * quando viene preso dal giocatore attiva casualmente l'effetto di uno degli altri power up
     */
    RANDOM("SPEED", 1, 7);
    private final String name;
    private final int val;
    private final int id;

    PowerUpType(String name, int val, int id) {
        this.name = name;
        this.val = val;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
