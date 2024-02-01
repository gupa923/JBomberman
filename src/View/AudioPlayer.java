package View;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;

public class AudioPlayer {
    public static ArrayList<Clip> EFFECTS = new ArrayList<>();
    public static boolean PLAY_EFFECTS = true;

    public AudioPlayer(){

    }

    public void playEffects(int i){
        if (PLAY_EFFECTS) {
            try {
                EFFECTS.get(i).setMicrosecondPosition(0);
                EFFECTS.get(i).start();
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    public static void StopSong(){
        if (EFFECTS.get(9).isActive()){
            EFFECTS.get(9).stop();
        }
    }
    public static void PlaySong(){
        StopSong();
        EFFECTS.get(9).setMicrosecondPosition(0);
        EFFECTS.get(9).loop(Clip.LOOP_CONTINUOUSLY);
    }

}
