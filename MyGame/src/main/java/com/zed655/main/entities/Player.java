
package com.zed655.main.entities;

import com.zed655.main.utils.EntityManager;
import com.zed655.main.utils.KeyManager;
import com.zed655.main.Window;
import com.zed655.main.states.Level;
import com.zed655.main.utils.ImageLoader;
import com.zed655.main.utils.SoundEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    private KeyManager keys;
    private EntityManager manager;
    private BufferedImage[] img;
    private SoundEffect oof;
    
    private int width,height;
    
    public Player(int x, int y,KeyManager keyManager,EntityManager manager) {
        
        super(x, y);
        
        setId("player");
        this.keys = keyManager;
        this.manager = manager;
        
        img = new BufferedImage[4];
        img[0] = ImageLoader.LoadImage("src\\res\\level1\\chicken0.png");
        img[1] = ImageLoader.LoadImage("src\\res\\level1\\chicken1.png");
        img[2] = ImageLoader.LoadImage("src\\res\\level1\\chicken3.png");
        img[3] = ImageLoader.LoadImage("src\\res\\level1\\chicken2.png");      
        
        oof = new SoundEffect();
        oof.loadAudio("src\\res\\sounds\\oof.wav");
        
        width = 180;
        height = 180;
    }

    @Override
    public void tick() {
        
        x += speedX;
        y += speedY;
        
        //Movements
        if(keys.right)
            speedX = (int) (5 + Level.currentLevel*1.6);
        else if(!keys.left)
            speedX=0;
        
        if(keys.left)
            speedX = (int) -(5 + Level.currentLevel*1.6);
        else if(!keys.right)
            speedX=0;
        
        if(keys.space && Egg.ready){
            manager.entities.add(new Egg(x+50,y+5,manager));
            Egg.ready = false;
        }
        
        if(x<0)
            x=0;
        if(x>Window.width - 180)
            x=Window.width - 180;
                    
        checkDeath();   
        
        collision();
    }
    
    public void checkDeath(){
        
        if(hp < 1){
            Level.dead = true;
            oof.play();
            manager.entities.remove(this);
        }    
    }
    
    public void collision(){
        
        for(int i=0;i<manager.entities.size();++i){
            Entity tempObject = manager.entities.get(i);
            
            if(tempObject.getId() == "enemy"){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 35;
                    manager.entities.remove(i);
                }
            }
        }
        
        
    }
    
    
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if(Level.currentLevel>3)
            g2d.drawImage(img[3],x,y,width,height,null);
        else
            g2d.drawImage(img[Level.currentLevel],x,y,width,height,null);
        
        g.setColor(Color.red);
        g.fillRect(x + 30, y - 10, 120, 10);
        
//        g.fillRect(x  +35, y +30, width - 60, height - 40);
        g.setColor(Color.green);
        g.fillRect(x + 30, y - 10, (int) (hp*1.2), 10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x +35,y +30, width -60,height -40);
    }

    
    
    
}
