package View.StatesGraphics;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.util.Observer;

/**
 * This class manages the graphical representation of a State
 * @see View.UtilityInterfaces.ImgImporter
 * @see View.UtilityInterfaces.Drawable
 * @see java.util.Observer
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class StateGraphics implements Drawable, Observer, ImgImporter {

    public StateGraphics(){

    }

}
