
package com.zed655.main.utils;

import com.zed655.main.entities.Entity;
import com.zed655.main.states.Level;
import com.zed655.main.utils.SoundEffect;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class EntityManager {
    
    public LinkedList<Entity> entities = new LinkedList<Entity>();
    
    
    public void tick(){
        
        for(int i=0;i<entities.size();++i){
            Entity tempObject = entities.get(i);
            
            tempObject.tick();
            
        }
    }
    
    public void render(Graphics g){
        
        for(int i=0;i<entities.size();++i){
            Entity tempObject = entities.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addEntity(Entity tempObject){
        
        entities.add(tempObject);
    }
    
    public void removeEntity(Entity tempObject){
        
        entities.remove(tempObject);
    }
}
