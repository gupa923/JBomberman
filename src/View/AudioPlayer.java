package View;

import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class AudioPlayer {
    public static ArrayList<Clip> EFFECTS = new ArrayList<>();

    public AudioPlayer(){

    }

    public void playEffects(int i){
        try {
            EFFECTS.get(i).setMicrosecondPosition(0);
            EFFECTS.get(i).start();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("SEI UN COGLIONE");
        }
    }

}
