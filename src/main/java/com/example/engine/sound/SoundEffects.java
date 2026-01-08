package com.example.engine.sound;

import com.example.constants.HexConstants;
import javafx.scene.media.AudioClip;

import java.net.URL;

/**
* This is used to play different game sounds
* */
public class SoundEffects {

    private static AudioClip playSound;

    private static AudioClip winSound;

    public static void playSound() {
        play(playSound, HexConstants.playSoundFilePath);
    }

    public static void winSound() {
        play(winSound, HexConstants.winSoundFilePath);
    }

    public static void play(AudioClip clip, String filePath) {
        if(clip == null) {
            clip = getAudioClip(filePath);
        }
        if(clip != null) {
            clip.play();
        }
    }

    private static AudioClip getAudioClip(String filePath) {
        URL url = SoundEffects.class.getResource(filePath);
        return url != null ? new AudioClip(url.toExternalForm()) : null;
    }
}
