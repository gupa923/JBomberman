package Model;

public class GameModel{
    private static GameModel instance;
    private Stati statoAttuale;
    private Menu menu;
    private Partita partita;
    private Pause pause;


    private GameModel(){
        this.statoAttuale = Stati.MENU;
        partita = new Partita(this);
        menu = new Menu(this);
        pause = new Pause(this);
    }

    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * questo metodo update chiama gli update di tutte le classi del model
     *
     */
    public void update(){
        switch(statoAttuale){
            case MENU -> menu.update();
            case PARTITA -> partita.update();
            case PAUSE -> pause.update();
        }
    }


    public Partita getPartita() {
        return partita;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setStatoAttuale(Stati statoAttuale) {
        this.statoAttuale = statoAttuale;
    }

    public Stati getStatoAttuale() {
        return statoAttuale;
    }

    public Pause getPause() {
        return pause;
    }
}
