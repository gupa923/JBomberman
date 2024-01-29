package Controller;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

import static View.AudioPlayer.EFFECTS;

public class AudioManager {
    public AudioManager(){
        EFFECTS.add(getClip("/audio/Bomberman Dies.wav"));
        EFFECTS.add(getClip("/audio/Enemy Dies.wav"));
        EFFECTS.add(getClip("/audio/Bomb Explodes.wav"));
        EFFECTS.add(getClip("/audio/Place Bomb.wav"));
        EFFECTS.add(getClip("/audio/Item Get.wav"));
        EFFECTS.add(getClip("/audio/Stage Start.wav"));
        EFFECTS.add(getClip("/audio/Stage Clear.wav"));
        EFFECTS.add(getClip("/audio/Audience.wav"));
        EFFECTS.add(getClip("/audio/Ending - Bomberman Falling.wav"));
    }

    private Clip getClip(String name){
        URL url = getClass().getResource(name);
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }
}
