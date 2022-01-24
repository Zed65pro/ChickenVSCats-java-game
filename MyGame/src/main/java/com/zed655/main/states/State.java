/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed655.main.states;

import com.zed655.main.Game;
import java.awt.Graphics;

/**
 *
 * @author zed65
 */
public abstract class State {
    
    protected Game game;//For key-inputs we did this
    private String id;
    
    public State(Game game){
        this.game=game;
    }
    
    //Main abstract methods that all states must have
    public abstract void tick(); // update variables
    public abstract void render(Graphics g); // draw their own graphics, must need g to draw
    
    //Setters getters
    public void setId(String id) {
        this.id = id;
    }

    
    public String getId() {
        return id;
    }
}
