package Model;

/**
 * This Enum contains all states of the game
 * @author Guido Paluzzi, Matteo Santucci
 */

public enum Stati {

    /**
     * This status indicates that you are in the game
     */
    PARTITA,
    /**
     * This state corresponds to the initial menu
     */
    MENU,
    /**
     * This state corresponds to the login state
     */
    LOGIN,
    /**
     * This status corresponds to the settings menu
     */
    SETTINGS,
    /**
     * This state corresponds to the command menu
     */
    COMMAND_INFO,
    /**
     * This state occurs when the player loses the game
     */
    GAME_OVER,
    /**
     * This state occurs when the player wins the game
     */
    WIN,
    /**
     * This status corresponds to the user statistics menu
     */
    STATS,
    /**
     * This state is active when the player pauses the game
     */
    PAUSE;

    public static Stati stato = MENU;
}
