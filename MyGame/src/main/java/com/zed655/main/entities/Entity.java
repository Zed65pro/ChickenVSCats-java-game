
package com.zed655.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public abstract class Entity {
    
    private final int DEFAULT_HP=100;
    protected int x,y; //Positions
    protected float speedX,speedY;
    protected int hp; //Health
    private String id;
    protected Random rand;
    
    public Entity(int x,int y){
        this.x = x;
        this.y = y;
        
        rand= new Random();
        
        hp=DEFAULT_HP;
        speedX=0;
        speedY=0;
        id="Default";
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    // Getters and Setters
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the speedX
     */
    public float getSpeedX() {
        return speedX;
    }

    /**
     * @return the speedY
     */
    public float getSpeedY() {
        return speedY;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param speedX the speedX to set
     */
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    /**
     * @param speedY the speedY to set
     */
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    /**
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
}
