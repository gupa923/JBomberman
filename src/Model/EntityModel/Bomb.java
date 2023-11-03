package Model.EntityModel;

import Model.Hitbox;

/**
 * questa classe gestisce le cose che fanno le bombe.
 * ci sta un counter che contiene il numero di bombe attive nel gioco
 * @see Model.EntityModel.Entity
 */
public class Bomb extends Entity{
    /**
     * questo counter contiene il numero di bombe attive nel gioco in preciso momento
     */
    public static int BOMB_COUNTER = 0;
    private int notCollideTick = 0;
    private Player player;
    private final int collideTickLim = 180;
    private final int explosionTick = 480;
    private final int explosionEnd = 600;
    private int[][] explosionTiles;
    private boolean exploding;
    public static int RANGE = 1;
    public Bomb(Player player, int x, int y) {
        super(x*16, y*16, 16, 16);
        this.player = player;
        BOMB_COUNTER++;
        initHitbox();
    }

    @Override
    public void initHitbox() {
        hitbox = new Hitbox(x, y, 16, 16);
        hitbox.setData(player.hitbox.getData());

    }

    /**
     * viene aumentato un contatore che per 180 che indica gli update in cui una bomba è presente nel terreno
     * dopo un certo numero di update il giocatore può collidere con la bomba e dopo un certo numero di update labomba esploderà.
     */
    @Override
    public void update() {
        notCollideTick++;
        if (notCollideTick == explosionTick) {
            player.explodeBomb(this);
        } else if (notCollideTick == explosionEnd){
            player.removeBomb(this);
        }
    }

    /**
     * sto metodo controlla la collisione tra il giocatore e la bomba
     *
     *
     * @param dir: la direzione in cui il player si sta muovendo
     * @return
     */
    public boolean intersect( String dir){
        Hitbox pHitbox = player.getHitbox();
        if (notCollideTick <= collideTickLim)
            return false;
        if (!exploding) {
            switch (dir) {
                case "LEFT", "UP" -> {
                    return checkPoints((hitbox.x) / 16, hitbox.y / 16, pHitbox.x / 16, pHitbox.y / 16);
                }
                case "RIGHT" -> {
                    return checkPoints((hitbox.x) / 16, hitbox.y / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16);
                }
                case "DOWN" -> {
                    return checkPoints((hitbox.x) / 16, (hitbox.y) / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16);
                }
            }
            return false;
        }else{
            switch (dir) {
                case "LEFT", "UP" -> {
                    for (int[] p: explosionTiles){
                        if (checkPoints((p[0]) / 16, p[1] / 16, pHitbox.x / 16, pHitbox.y / 16)){
                            player.setAlive(false);
                            Player.VITE --;
                            break;
                        }
                    }
                }
                case "RIGHT" -> {
                    for (int[] p: explosionTiles){
                        if (checkPoints(p[0] / 16, p[1] / 16, (pHitbox.x + pHitbox.w - 1) / 16, pHitbox.y / 16)){
                            player.setAlive(false);
                            Player.VITE --;
                            break;
                        }
                    }
                }
                case "DOWN" -> {
                    for (int[] p: explosionTiles){
                        if (checkPoints(p[0] / 16, p[1] / 16, pHitbox.x / 16, (pHitbox.y + pHitbox.h - 1) / 16)){
                            player.setAlive(false);
                            Player.VITE --;
                            break;
                        }
                    }
                }
            }
            return false;
        }
        //return checkPoints(hitbox.x + hitbox.w, hitbox.y, pHitbox.x, pHitbox.h) || checkPoints(hitbox.x + hitbox.w, hitbox.y+ hitbox.h, pHitbox.x, pHitbox.y+ pHitbox.h);

    }

    /**
     * è un metodo che controlla se due punti sono uguali
     * @param x1: la x del primo punto
     * @param y1: la y del primo punto
     * @param x2: la x del secondo punto
     * @param y2: la y del secondo punto
     * @return: restituisce true se x1 è uguale a x2 e y1 è uguale a y2
     */
    private boolean checkPoints(int x1, int y1, int x2, int y2){
        return (x1 == x2) && (y1 == y2);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Bomb){
            Bomb b = (Bomb) o;
            return this.x == b.x && this.y == b.y;
        }
        return false;
    }

    public boolean isExploding() {
        return exploding;
    }

    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    public void setExplosionTiles(int[][] explosionTiles) {
        this.explosionTiles = explosionTiles;
    }

    public int[][] getExplosionTiles() {
        return explosionTiles;
    }

    /**
     * metodo creato solo a fini di debug. stampa solamente il contenuto dell'arrylist explosionTiles
     */
    public void printExplosion(){
        for (int y = 0; y < explosionTiles.length; y++){
            System.out.println(explosionTiles[y][0] + " " + explosionTiles[y][1]);
        }
    }
}
