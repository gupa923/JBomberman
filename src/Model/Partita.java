package Model;

public class Partita extends Stato{
    private Player player;
    private Level actuaLevel;
    public Partita(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void update() {
        player.update();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * imposta il livello attuale e aggiunge la matrice data alla hitbox
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.actuaLevel = level;
        player.getHitbox().setData(level.getData());
    }

    public Player getPlayer() {
        return player;
    }
}
