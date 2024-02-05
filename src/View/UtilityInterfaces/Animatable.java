package View.UtilityInterfaces;

/**
 * It is implemented by all classes that have the characteristic of having animations
 * @author Guido Paluzzi, Matteo Santucci
 */
public interface Animatable {

    /**
     * This method loads the images needed for animations
     */
    void loadAnimations();

    /**
     * This method updates the animation as time passes
     */
    void updateAnimation();
}
