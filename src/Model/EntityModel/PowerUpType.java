package Model.EntityModel;

/**
 * This enum represents the possible types of power ups present in the game
 * @author Guido Paluzzi, Matteo Santucci
 */
public enum PowerUpType {

    /**
     * increases the maximum number of bombs to the value contained in val
     */
    BOMB_UP("BOMB_UP", 1, 0),
    /**
     * increases the number of lives to the value present in the value field
     */
    LIVE_UP("LIVE_UP", 1, 1),
    /**
     * adds the value contained in the field value to the match score
     */
    CAKE ("CAKE", 500, 2),
    /**
     * adds the value of the val field to the player's HP number
     */
    HP_PLUS("HP_PLUS", 1, 3),
    /**
     * removes the value contained in val from the player's HP number
     */
    HP_MEN("HP_MEN", -1, 4),
    /**
     * makes the player immortal for a short period of time
     */
    IMMORTALITY("IMMORTALITY", 0, 5),
    /**
     * allows the player to walk over destructible blocks i.e. obstacles and bombs
     */
    WALK_OVER("WALK_OVER", 0, 6),
    /**
     * when taken by the player it randomly activates the effect of one of the other power ups
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
