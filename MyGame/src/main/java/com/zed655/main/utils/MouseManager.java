
package com.zed655.main.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseManager implements MouseListener, MouseMotionListener{
    
    public boolean leftClick;
    public int mx,my;
    
    @Override
    public void mouseClicked(MouseEvent e) {
            
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == e.BUTTON1)
            leftClick = true;
        
        mx = e.getX();
        my = e.getY();
//        System.out.println(mx + " " + my);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == e.BUTTON1)
            leftClick = false;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        
    }
    
    
}
