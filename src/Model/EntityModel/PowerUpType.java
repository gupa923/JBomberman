package Model.EntityModel;

public enum PowerUpType {

    BOMB_UP("BOMB_UP", 1, 0), LIVE_UP("LIVE_UP", 1, 1);
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
