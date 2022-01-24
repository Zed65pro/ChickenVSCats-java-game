
package com.zed655.main;

import com.zed655.main.utils.ImageLoader;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


public class Window {
    
    private JFrame frame;
    public static int width,height;
    private String title;
    private BufferedImage icon;

    public Window(String title,int width, int height,Game game) {
        this.width = width;
        this.height = height;
        this.title = title;
        
        icon = ImageLoader.LoadImage("..\\src\\res\\icon.png");
        
        init(game);
    }
    
    private void init(Game game){
        
        frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.setIconImage(icon);
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

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
    
}
