package Controller;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

import static View.AudioPlayer.EFFECTS;

/**
 * Questa classe importa tutti gli effetti audio presenti nel gioco
 * @see Clip
 * @author Guido Paluzzi, Matteo Santucci
 */
public class AudioManager {
    /**
     * Costruttore della classe.
     */
    public AudioManager(){
        EFFECTS.add(importAudio("/audio/Bomberman Dies.wav"));
        EFFECTS.add(importAudio("/audio/Enemy Dies.wav"));
        EFFECTS.add(importAudio("/audio/Bomb Explodes.wav"));
        EFFECTS.add(importAudio("/audio/Place Bomb.wav"));
        EFFECTS.add(importAudio("/audio/Item Get.wav"));
        EFFECTS.add(importAudio("/audio/Stage Start.wav"));
        EFFECTS.add(importAudio("/audio/Stage Clear.wav"));
        EFFECTS.add(importAudio("/audio/Audience.wav"));
        EFFECTS.add(importAudio("/audio/Ending - Bomberman Falling.wav"));
        EFFECTS.add(importAudio("/audio/BgMusic.wav"));
    }

    private Clip importAudio(String name){
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
