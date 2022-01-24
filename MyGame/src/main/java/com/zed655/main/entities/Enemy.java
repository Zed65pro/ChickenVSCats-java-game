
package com.zed655.main.entities;

import com.zed655.main.utils.EntityManager;
import com.zed655.main.Window;
import com.zed655.main.states.Level;
import com.zed655.main.utils.ImageLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy extends Entity{
    
    private BufferedImage[] img;
    private EntityManager manager;
    private int width,height;
    
    public Enemy(int x, int y,EntityManager manager) {
        super(x, y);
        
        
        width = 165;
        height = 165;
        
        this.manager = manager;
        
        setId("enemy");
        
        img = new BufferedImage[4];
        img[0] = ImageLoader.LoadImage("src\\res\\level1\\cat0.png");
        img[1] = ImageLoader.LoadImage("src\\res\\level1\\cat1.png");
        img[2] = ImageLoader.LoadImage("src\\res\\level1\\cat2.png");
        img[3] = ImageLoader.LoadImage("src\\res\\level1\\cat3.png");
    }

    @Override
    public void tick() {
        
        y += -5-(Level.currentLevel*2.6);
        
        if(y<-150){
            hp = 100;
            
            x = rand.nextInt(Window.width) - width + 40;
            y = (rand.nextInt(Window.height + 900)) + Window.height;
        }
        
        if(hp < 1){
            hp=100;
            
            x = rand.nextInt(Window.width) - width + 40;
            y = (rand.nextInt(Window.height + 900)) + Window.height;
        }
        
        collision();
            
    }
    
    public void collision(){
        
        for(int i=0;i<manager.entities.size();++i){
            
            Entity tempObject = manager.entities.get(i);
            
            if(tempObject.getId() == "enemy" && this!=tempObject){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    x = rand.nextInt(Window.width) - width + 40;
                    y = (rand.nextInt(Window.height + 900)) + Window.height;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if(Level.currentLevel>3)
            g2d.drawImage(img[3],x,y,200,200,null);
        else
            g2d.drawImage(img[Level.currentLevel],x,y,width,height,null);
            
        g.setColor(Color.red);
        g.fillRect(x +30, y - 10, 110, 10);
        
        g.setColor(Color.green);
        g.fillRect(x +30, y - 10, (int) (hp*1.1), 10);
        
        
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 30,y + 20 ,width - 60,height - 30);
    }
    
    
    
}
