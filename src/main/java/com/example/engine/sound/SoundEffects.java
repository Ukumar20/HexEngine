package com.example.engine.sound;

import com.sun.media.sound.JavaSoundAudioClip;

import java.applet.AudioClip;
import java.io.FileInputStream;

/**
* This is used to play different game sounds
* */
public class SoundEffects {

    public static void play(String fileName) {
        try{
            FileInputStream fs = new FileInputStream(fileName);
            AudioClip clip = new JavaSoundAudioClip(fs);
            clip.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
