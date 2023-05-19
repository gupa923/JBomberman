package Model;

public class GameModel {
    private Player player;

    public GameModel(){
        player = new Player(0, 0, 16, 24);
    }

    public Player getPlayer() {
        return player;
    }

    public void updateGame(){
        player.updatePos();
    }
}
