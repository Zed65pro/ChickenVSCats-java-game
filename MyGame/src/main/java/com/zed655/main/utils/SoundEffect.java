
package com.zed655.main.utils;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundEffect {

    Clip clip;
    
    public void loadAudio(String path){
        
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setMicrosecondPosition(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void play(){
        
        clip.setFramePosition(0);
        clip.start();
        
    }
    
    public void loop(){
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
    
    
}
