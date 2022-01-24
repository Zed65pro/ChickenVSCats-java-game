/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed655.main.entities;

import com.zed655.main.utils.EntityManager;
import com.zed655.main.Window;
import com.zed655.main.states.Level;
import com.zed655.main.utils.ImageLoader;
import com.zed655.main.utils.SoundEffect;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author zed65
 */
public class Egg extends Entity{
    
    private EntityManager manager;
    private BufferedImage img;
    
    public static boolean ready=true;
    
    private SoundEffect meow;
    
    public Egg(int x, int y,EntityManager manager) {
        super(x, y);
        this.manager = manager;
        setId("egg");
        img = ImageLoader.LoadImage("src\\res\\level1\\egg.png");
        
        meow = new SoundEffect();
        meow.loadAudio("src\\res\\sounds\\meow.wav");
    }

    
    public void collision(){
        
       for(int i=0;i<manager.entities.size();++i){
           Entity tempObject = manager.entities.get(i);
           
           if(tempObject.getId() == "enemy"){
               
               if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.setHp(tempObject.getHp() - 50);
                    if(tempObject.getHp() == 0){
                        meow.play();
                        Level.score +=10;
                    }
                        
                    manager.entities.remove(this);
                    ready=true;
               }
           }
           
       }
        
    }
    
    @Override
    public void tick() {
        y +=(10+Level.currentLevel*1.6);
        
        if(y>Window.height){
            ready=true;
            manager.entities.remove(this);
        }
        
        collision();
            
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(img,x+25,y + 5,40,40,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,40,40);
    }
    
    
    
}
