
package com.zed655.main.entities;

import com.zed655.main.Window;
import com.zed655.main.states.Level;
import com.zed655.main.utils.ImageLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Cloud extends Entity {
    
    
    private BufferedImage[] cloudImg;
    
    public Cloud(int x, int y){
        super(x,y);
        
        cloudImg = new BufferedImage[2];
        
        cloudImg[0] = ImageLoader.LoadImage("src\\res\\level1\\cloud.png");
        cloudImg[1] = ImageLoader.LoadImage("src\\res\\level1\\spaceship.png");
        
        speedY = -4;
    }

    @Override
    public void tick() {
        
        y += speedY - (Level.currentLevel*1.2);
        
        if(y < -300){
            y = (rand.nextInt(Window.height + 1500)) + Window.height + 400;
            x = (rand.nextInt(Window.width));
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if(Level.currentLevel<2)
            g2d.drawImage(cloudImg[0],x,y,450,300,null);
        else
            g2d.drawImage(cloudImg[1],x,y,450,300,null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
