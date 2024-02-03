package View;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;

/**
 * Questa classe contiene gli effetti e la canzone del gioco. Consente alle classi che la istanziano di eseguire tali effetti tramite appositi metodi
 *
 * @see Clip
 * @autor Guido Paluzzi, Matteo Santucci
 */
public class AudioPlayer {
    /**
     * E' un array di tutti gli audio presenti nel gioco
     */
    public static ArrayList<Clip> EFFECTS = new ArrayList<>();
    public static boolean PLAY_EFFECTS = true;

    public AudioPlayer(){

    }

    /**
     * Questo metodo permette l'esecuzione di un effetto audio, solo se l'utente ha consentito l'esecuzione di effetti
     * @param i: l'indice del effetto da eseguire
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
