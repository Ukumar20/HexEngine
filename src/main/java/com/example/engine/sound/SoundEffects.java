package com.example.engine.sound;

import com.example.constants.HexConstants;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
* This is used to play different game sounds
* */
public final class SoundEffects {

    private static final Map<String, AudioClip> audioClipMap = new HashMap<>();

    private SoundEffects() {}

    public static void moveSound() {
        play(HexConstants.moveSoundFilePath);
    }

    public static void winSound() {
        play(HexConstants.winSoundFilePath);
    }

    private static void play(String filePath) {
        AudioClip clip = getAudioClip(filePath);
        if(clip != null){
            clip.play();
        }
    }

    private static AudioClip getAudioClip(String filePath) {
        return audioClipMap.computeIfAbsent(filePath, SoundEffects::generateAudioClip);
    }

    private static AudioClip generateAudioClip(String filePath) {
        URL url = SoundEffects.class.getResource(filePath);
        return url != null ? new AudioClip(url.toExternalForm()) : null;
    }
}