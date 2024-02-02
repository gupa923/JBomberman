package View.UtilityInterfaces;

/**
 * E' implementata da tutte le classi che hanno la caratteristica di avere animazioni
 * @author Guido Paluzzi, Matteo Santucci
 */
public interface Animatable {

    /**
     * Questo metodo carica le immagini necessarie per le animazioni
     */
    void loadAnimations();

    /**
     * Questo metodo aggiorna l'animazione al passare del tempo
     */
    void updateAnimation();
}
