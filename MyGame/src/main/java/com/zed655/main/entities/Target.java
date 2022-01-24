
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
import java.util.Random;


public class Target extends Entity{
    
    private BufferedImage img;
    private EntityManager manager;
    
    private int width,height;
    
    private SoundEffect target_hit;
    
    public Target(int x, int y,int width,int height, EntityManager manager) {
        super(x, y);
        this.manager = manager;
        
        this.width= width;
        this.height=height;
        
        setId("target");
        img = ImageLoader.LoadImage("src\\res\\level1\\target.png");
        
        target_hit = new SoundEffect();
        target_hit.loadAudio("src\\res\\sounds\\target_hit.wav");
    }

    @Override
    public void tick() {
        y += -2;
        
        collision();
    }
    
    public void collision(){
        
        for(int i=0;i<manager.entities.size();++i){
            Entity tempObject = manager.entities.get(i);
            
            if(tempObject.getId() == "player"){
                if(getBounds().intersects(tempObject.getBounds())){
                    //generate a new target
                    width = rand.nextInt(50)+50;
                    height = rand.nextInt(50)+50;
                    
                    y = (rand.nextInt(Window.height)) + Window.height;
                    x = (rand.nextInt(Window.width));
                }
            }
            
            if(tempObject.getId() == "egg"){
                
               if(getBounds().intersects(tempObject.getBounds())){
                   
                   //remove
                    manager.entities.remove(i);
                    
                    //reset bullet
                    Egg.ready=true;
                    
                    //generate a new target
                    width = rand.nextInt(50)+50;
                    height = rand.nextInt(50)+50;
                    
                    y = (rand.nextInt(Window.height + 800)) + Window.height;
                    x = (rand.nextInt(Window.width));
                    
                    //score
                    Level.score += (width/2 - 60)*-1;
                    
                    //sound
                    target_hit.play();
               }
           }
           
           if(tempObject.getId() == "target" && this!=tempObject){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    
                    x = rand.nextInt(Window.width);
                    y = (rand.nextInt(Window.height + 800)) + Window.height;
                    width = rand.nextInt(50)+50;
                    height = rand.nextInt(50) + 50;
                }
            }
           
        }
    }
    
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(img, x, y ,width,height,null);
    }

    @Override
    public Rectangle getBounds() {
        
        return new Rectangle(x,y, width,height);
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
}
