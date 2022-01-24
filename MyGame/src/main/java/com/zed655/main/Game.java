/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed655.main;

import com.zed655.main.utils.MouseManager;
import com.zed655.main.utils.KeyManager;
import com.zed655.main.utils.EntityManager;
import com.zed655.main.entities.Player;
import com.zed655.main.states.Level;
import com.zed655.main.states.MainMenu;
import com.zed655.main.states.State;
import com.zed655.main.utils.StateManager;
import com.zed655.main.utils.SoundEffect;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author zed65
 */
public class Game extends Canvas implements Runnable{
    
    //Window
    private Window window;
    
    //thread-implemented by runnable
    private Thread thread;
    
    //running-condition
    private boolean running=false;
    
    //Keymanager
    private KeyManager keyManager;
    
     //MouseManager
    private MouseManager mouseManager;
    
    //All states
    public State level1;
    public State mainMenu;
    
    //Music Box
    public SoundEffect click;
    public SoundEffect bg_menu;
    public SoundEffect bg_level;
    
    
    public Game(String title,int width,int height){
        
        window = new Window(title,width,height,this);
        
        //Managers 
        keyManager = new KeyManager(); 
        mouseManager = new MouseManager();
        
        this.addKeyListener(keyManager);
        this.addMouseListener(mouseManager);
        this.addMouseMotionListener(mouseManager);
        //States
        level1 = new Level(this);
        mainMenu = new MainMenu(this);
                
        StateManager.setState(mainMenu);
        
        generateMusic();
        
        bg_menu.loop();
        
        start();
    }
    
    public void generateMusic(){
        
        //Sounds
        click = new SoundEffect();
        click.loadAudio("..\\src\\res\\sounds\\click.wav");
        
        bg_menu = new SoundEffect();
        bg_menu.loadAudio("..\\src\\res\\sounds\\bg_mainmenu.wav");
        
        bg_level = new SoundEffect();
        bg_level.loadAudio("..\\src\\res\\sounds\\bg_level.wav");
        
        bg_level = new SoundEffect();
        bg_level.loadAudio("..\\src\\res\\sounds\\bg_level.wav");
        
        bg_level = new SoundEffect();
        bg_level.loadAudio("..\\src\\res\\sounds\\bg_level.wav");
        
        bg_level = new SoundEffect();
        bg_level.loadAudio("..\\src\\res\\sounds\\bg_level.wav");
        
        bg_level = new SoundEffect();
        bg_level.loadAudio("..\\src\\res\\sounds\\bg_level.wav");
    }
    
    public void tick(){
        
        if(StateManager.getState() != null)
            StateManager.getState().tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //Draw here
        g.setColor(Color.red);
        g.fillRect(0, 0, window.getWidth(), window.getHeight());
        
        if(StateManager.getState() != null)
            StateManager.getState().render(g);
        //Done drawing
        
        bs.show();
        g.dispose();
    }
    
    //Automated by start() using the thread.start()
    public void run() {
        
        //Time Management
        int fps=80;
        double timePerTick = 1000000000 / fps;
        double delta=0;
        long now;
        long last=System.nanoTime(); 
        
        //Count Time and fps
        long timer = 0;
        int ticks = 0;
        
        // Game Timer
        double timerSeconds=0; 
        long startTime = System.currentTimeMillis();
        
        
        //Game loop
        while(running){
            
            //Time Stuff
            now = System.nanoTime();
            delta += (now-last) / timePerTick;
            timer += (now - last);
            last = now;
            
            if(delta>=1){
                tick();
                render();
                --delta;
                ++ticks;
            }
            
            // To print Fps on screen
            if(timer > 1000000000  ){//1*10^9 nanoseconds = 1Second
                //System.out.println("Fps: " + ticks);
                ticks=0;
                timer=0;
            }
            
            //To print time played since start of program
            //timerSeconds = (System.currentTimeMillis() - startTime)/1000;
            //System.out.println("Time Played: "+  (int)timerSeconds);
        }
    }
    
    public synchronized void start(){
        if(running)
            return;
        running=true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running=false;
        
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
              
    }

    /**
     * @return the keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * @return the mouseManager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    /**
     * @return the window
     */
    public Window getWindow() {
        return window;
    }
}
