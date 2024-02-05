package View;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;

/**
 * This class contains the game's effects and song. It allows the classes that instantiate it to perform these effects via appropriate methods
 *
 * @see Clip
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class AudioPlayer {
    /**
     * It's an array of all the audio in the game
     */
    public static ArrayList<Clip> EFFECTS = new ArrayList<>();
    public static boolean PLAY_EFFECTS = true;

    public AudioPlayer(){

    }

    /**
     * This method allows the execution of an audio effect, only if the user has allowed the execution of effects
     * @param i: the index of the effect to be performed
     */
    public void playEffects(int i){
        if (PLAY_EFFECTS) {
            try {
                EFFECTS.get(i).setMicrosecondPosition(0);
                EFFECTS.get(i).start();
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    /**
     * Questo metodo interrompe l'esecuzione della canzone
     */
    public static void StopSong(){
        if (EFFECTS.get(9).isActive()){
            EFFECTS.get(9).stop();
        }
    }

    /**
     * Questo metodo permette l'esecuzione della canzone di sottofondo.
     */
    public static void PlaySong(){
        StopSong();
        FloatControl volume = (FloatControl) EFFECTS.get(9).getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-25);
        EFFECTS.get(9).setMicrosecondPosition(0);
        EFFECTS.get(9).loop(Clip.LOOP_CONTINUOUSLY);
    }

}
