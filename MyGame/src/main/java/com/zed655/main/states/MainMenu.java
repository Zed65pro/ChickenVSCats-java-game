
package com.zed655.main.states;

import com.zed655.main.utils.StateManager;
import com.zed655.main.Game;
import com.zed655.main.utils.ImageLoader;
import com.zed655.main.utils.SoundEffect;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class MainMenu extends State{

    private Rectangle playButton ;
    private Rectangle quitButton ;
    private Rectangle helpButton ;
    private Rectangle backButton;
    
    private BufferedImage[] startImage;
    private BufferedImage[] quitImage;
    private BufferedImage backGround;
    private BufferedImage logoText;
    private BufferedImage[] helpImage;
    private BufferedImage helpText;
    private BufferedImage[] backImg;
   
    private boolean isHelp = false;    
    
    public MainMenu(Game game) {
        super(game);
        
        setId("mainmenu");
        
        playButton = new Rectangle(game.getWindow().getWidth()/2 - 240, 350,500,150);
        helpButton = new Rectangle(game.getWindow().getWidth()/2 - 160, 500,350,150);
        quitButton = new Rectangle(game.getWindow().getWidth()/2 - 160, 650,350,150);
        backButton = new Rectangle(game.getWindow().getWidth()/2 - 160, 650,350,150);
        
        logoText = ImageLoader.LoadImage("src\\res\\MainMenu\\coolstart2.png");
        
        startImage = new BufferedImage[2];
        startImage[0] = ImageLoader.LoadImage("src\\res\\MainMenu\\start.png");
        startImage[1] = ImageLoader.LoadImage("src\\res\\MainMenu\\start_hover.png");
        
        quitImage= new BufferedImage[2];
        quitImage[0] = ImageLoader.LoadImage("src\\res\\MainMenu\\quit.png");
        quitImage[1] = ImageLoader.LoadImage("src\\res\\MainMenu\\quit_hover.png");
        
        backGround = ImageLoader.LoadImage("src\\res\\MainMenu\\sky.png");
        
        helpImage = new BufferedImage[2];
        helpImage[0] = ImageLoader.LoadImage("src\\res\\MainMenu\\help.png");
        helpImage[1] = ImageLoader.LoadImage("src\\res\\MainMenu\\help_hover.png");
        
        helpText = ImageLoader.LoadImage("src\\res\\MainMenu\\help_text.png");
        
        backImg = new BufferedImage[2];
        backImg[0] = ImageLoader.LoadImage("src\\res\\MainMenu\\back.png");
        backImg[1] = ImageLoader.LoadImage("src\\res\\MainMenu\\back_hover.png");
    }

    @Override
    public void tick() {
        
        if(!isHelp){
                //Clicking on the start button
            if(game.getMouseManager().mx >= playButton.x && game.getMouseManager().mx <= playButton.x + 400  && game.getMouseManager().leftClick){

                if ( game.getMouseManager().my >= playButton.y 
                        && game.getMouseManager().my <= playButton.y + 100){

                            StateManager.setState(game.level1);

                            //music adapt
                            game.click.play();
                            game.bg_menu.stop();
                            game.bg_level.loop();

                }


            }

            //Quit button
            if(game.getMouseManager().mx >= quitButton.x && game.getMouseManager().mx <= quitButton.x + 400  && game.getMouseManager().leftClick){

                if ( game.getMouseManager().my >= quitButton.y 
                        && game.getMouseManager().my <= quitButton.y + 100){
                                            System.exit(1);
                                            game.click.play();
                }


            }
            
            //Help Button
            if(helpButton.contains(game.getMouseManager().mx,game.getMouseManager().my) && game.getMouseManager().leftClick){
                isHelp = true;
            }
        }
        else
            //back Button
            if(backButton.contains(game.getMouseManager().mx,game.getMouseManager().my) && game.getMouseManager().leftClick ){
                isHelp = false;
                game.getMouseManager().leftClick=false;
            }
        
        
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g.drawImage(backGround,0,0,null);
        g2d.drawImage(backGround, 0, 0,game.getWindow().getWidth(),game.getWindow().getHeight() ,null);
              
        if(!isHelp){
            startText(g,g2d);
            createButtons(g,g2d);   
        }
        else{
            g2d.drawImage(helpText, 0, 0,game.getWindow().getWidth() - 100,game.getWindow().getHeight() - 400,null); 
            
            if(!backButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
                g2d.drawImage(backImg[0], backButton.x, backButton.y,backButton.width,backButton.height, null);
            else
                g2d.drawImage(backImg[1], backButton.x, backButton.y,backButton.width,backButton.height, null);
        }
           
        
        
    }
    
    public void startText(Graphics g,Graphics2D g2d){
        Font font1 = new Font("sans_serif",Font.BOLD,20);
        g.setColor(Color.red);
        g.setFont(font1);
        g.drawString("BETA V1.0",0,20);
        g2d.drawImage(logoText,game.getWindow().getWidth()/2 - 450, 100,900,250, null);
    }
    
    public void createButtons(Graphics g,Graphics2D g2d){      
        
        if(!playButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(startImage[0], playButton.x, playButton.y,playButton.width,playButton.height, null);
        else
            g2d.drawImage(startImage[1], playButton.x, playButton.y,playButton.width,playButton.height, null);
        
        if(!quitButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(quitImage[0], quitButton.x, quitButton.y,quitButton.width,quitButton.height, null);
        else
            g2d.drawImage(quitImage[1], quitButton.x, quitButton.y,quitButton.width,quitButton.height, null);
        
        if(!helpButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(helpImage[0], helpButton.x, helpButton.y,helpButton.width,helpButton.height, null);
        else
            g2d.drawImage(helpImage[1], helpButton.x, helpButton.y,helpButton.width,helpButton.height, null);
        
    }
    
    
}
