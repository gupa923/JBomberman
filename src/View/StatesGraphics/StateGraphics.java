package View.StatesGraphics;

import View.UtilityInterfaces.Drawable;
import View.UtilityInterfaces.ImgImporter;

import java.util.Observer;

/**
 * Questa classe gestisce la rappresentazione grafica di uno Stato
 * @see View.UtilityInterfaces.ImgImporter
 * @see View.UtilityInterfaces.Drawable
 * @see java.util.Observer
 * @author Guido Paluzzi, Matteo Santucci
 */
public abstract class StateGraphics implements Drawable, Observer, ImgImporter {

    public StateGraphics(){

    }

}
