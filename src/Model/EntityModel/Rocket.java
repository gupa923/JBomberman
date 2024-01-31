package Model.EntityModel;

public class Rocket extends Entity{
    private String dir;
    /**
     * Ogni Entity viene creata a partire da 4 interi
     *
     * @param x : ascissa punto di spawn
     * @param y : ordinata del punto di spawn
     * @param w : larghezza
     * @param h : altezza
     */
    public Rocket(int x, int y, int w, int h, String dir) {
        super(x, y, w, h);
        this.dir = dir;

    }

    @Override
    public void initHitbox() {

    }

    @Override
    public void update() {

    }
}
