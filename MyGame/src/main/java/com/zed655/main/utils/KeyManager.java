package com.zed655.main.utils;

import com.zed655.main.states.Level;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager extends KeyAdapter{

    private boolean[] keys;
    
    public boolean up,down,left,right,space,pause=false;
    
    public KeyManager(){
        //so what we do is once we press a key with the code 0-256 we make it true there
        // then after releasing make it false again.
        keys = new boolean[256]; // all keys on keyboard
    }
    
    @Override // for typing
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
        space = keys[KeyEvent.VK_SPACE];
        
        if(keys[KeyEvent.VK_P] || keys[KeyEvent.VK_ESCAPE]){
            if(!Level.levelUp && StateManager.getState().getId()=="level"){
                
                if(!pause)
                    pause=true;
                else
                    pause=false;
            }
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
        space = keys[KeyEvent.VK_SPACE];
    }
    
}
