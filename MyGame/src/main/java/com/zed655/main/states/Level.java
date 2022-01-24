/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed655.main.states;

import com.zed655.main.utils.StateManager;
import com.zed655.main.Game;
import com.zed655.main.Window;
import com.zed655.main.entities.Cloud;
import com.zed655.main.entities.Egg;
import com.zed655.main.entities.Enemy;
import com.zed655.main.entities.Entity;
import com.zed655.main.utils.EntityManager;
import com.zed655.main.entities.Player;
import com.zed655.main.entities.Target;
import com.zed655.main.utils.ImageLoader;
import com.zed655.main.utils.SoundEffect;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Level extends State {

    private EntityManager manager;
    private BufferedImage[] backGround;
    
    public static int score=0;
    public static boolean levelUp=false;
    public static int currentLevel = 0;
    public static boolean dead=false;
            
    private boolean isPause=false;
    
    //Buttons
    private Rectangle nextLevelButton;
    private BufferedImage[] nextLevelImage;
    
    private Rectangle mainMenuButton;
    private BufferedImage[] mainMenuImage;
    
    private Rectangle mainMenu2Button;
    
    private Rectangle quitButton;
    private BufferedImage[] quitImage;
    
    private BufferedImage[] lifeStatus;
    private BufferedImage pauseImg;
    
    
    public Level(Game game) {
        super(game);
        
        setId("level");
        
        loadImgs();
        
            
        manager = new EntityManager();
        
        generateEntity();
        
        generateButtons();
        
        
        
    }
    
    public void loadImgs(){
        
        backGround = new BufferedImage[2];
        backGround[0] = ImageLoader.LoadImage("src\\res\\level1\\sky.png");
        backGround[1] = ImageLoader.LoadImage("src\\res\\level1\\space.jpg");
        
        lifeStatus = new BufferedImage[2];
        lifeStatus[0] = ImageLoader.LoadImage("src\\res\\level1\\gameover.png");
        lifeStatus[1] = ImageLoader.LoadImage("src\\res\\level1\\levelup.png");
        
        pauseImg = ImageLoader.LoadImage("src\\res\\level1\\pause.png");
    }
    
    public void generateButtons(){
        
        //Buttons
        mainMenuButton = new Rectangle(game.getWindow().getWidth()/2 - 250, 450,350,150);
        
        mainMenuImage = new BufferedImage[2];
        mainMenuImage[0] = ImageLoader.LoadImage("src\\res\\level1\\mainMenu.png");
        mainMenuImage[1] = ImageLoader.LoadImage("src\\res\\level1\\mainmenu_hover.png");
        
        nextLevelButton = new Rectangle(game.getWindow().getWidth()/2 - 250, 600,350,150);
        
        nextLevelImage = new BufferedImage[2];
        nextLevelImage[0] = ImageLoader.LoadImage("src\\res\\level1\\nextlevel.png");
        nextLevelImage[1] = ImageLoader.LoadImage("src\\res\\level1\\nextlevel_hover.png");
        
        mainMenu2Button = new Rectangle(game.getWindow().getWidth()/2 - 250, 450,350,150);
        
        quitButton = new Rectangle(game.getWindow().getWidth()/2 - 250, 600,350,150);
        
        quitImage= new BufferedImage[2];
        quitImage[0] = ImageLoader.LoadImage("src\\res\\MainMenu\\quit.png");
        quitImage[1] = ImageLoader.LoadImage("src\\res\\MainMenu\\quit_hover.png");
    }
    
    public void generateEntity(){
        
        Random rand = new Random();
        
        //Cloud generation
        for(int i=0;i<10;++i){
            manager.addEntity(new Cloud(rand.nextInt(Window.width),(rand.nextInt(Window.height + 1500)) + Window.height + 400));
        }
        //Player
        manager.addEntity(new Player(300,80,game.getKeyManager(),manager));
        
        //Enemy Generation
        for(int i=0;i<5+currentLevel;++i)
            manager.addEntity(new Enemy(rand.nextInt(Window.width) + 25,(rand.nextInt(Window.height + 900)) + Window.height,manager));
        
        //Target Generation
        for(int i=0;i<4 + currentLevel;++i){
            manager.addEntity(new Target(rand.nextInt(Window.width),(rand.nextInt(Window.height + 900)) + Window.height
                       ,rand.nextInt(50)+50,rand.nextInt(50) + 50,manager));
        }
        
    }

   
    public void checkDead(){
        
        if(dead){
            //If you click on main menue
            if(game.getMouseManager().mx >= mainMenu2Button.x && game.getMouseManager().mx <= mainMenu2Button.x + 400 &&
                        game.getMouseManager().leftClick ){
                if ( game.getMouseManager().my >= mainMenu2Button.y 
                        && game.getMouseManager().my <= mainMenu2Button.y + 100){
                    
                        // Reset floor
                        manager.entities.clear();
                        //generate
                        generateEntity();
                        //increment level counter
                        currentLevel=0;
                        //unpause game
                        levelUp=false;
                        //dead reset
                        dead = false;
                        //set state to main menu
                        StateManager.setState(game.mainMenu);
                        //reset score
                        score=0;
                        //pause fix
                        game.getKeyManager().pause = false;
                        
                        //music
                        game.click.play();
                        game.bg_level.stop();
                        game.bg_menu.loop();
                }
                            
            }
            
            if(quitButton.contains(game.getMouseManager().mx,game.getMouseManager().my) && game.getMouseManager().leftClick){
                System.exit(1);
            }
            
        }
        
    }
    
    public void checkLevelUp(){
        
        if(levelUp ){
            
            //If you click on next Level
            if(game.getMouseManager().mx >= nextLevelButton.x && game.getMouseManager().mx <= nextLevelButton.x + 400  &&
                        game.getMouseManager().leftClick){
                if ( game.getMouseManager().my >= nextLevelButton.y 
                        && game.getMouseManager().my <= nextLevelButton.y + 100){
                        
                        // Reset floor
                        manager.entities.clear();
                        // Re-generate entities
                        generateEntity();
                        //increment level counter
                        ++currentLevel;
                        //unpause game
                        levelUp=false;
                        //pause fix
                        game.getKeyManager().pause = false;
                        //bullet rdy
                        Egg.ready=true;
                }
                            
            }
            
            //If you click on main menue
            if(game.getMouseManager().mx >= mainMenuButton.x && game.getMouseManager().mx <= mainMenuButton.x + 400 &&
                        game.getMouseManager().leftClick ){
                if ( game.getMouseManager().my >= mainMenuButton.y 
                        && game.getMouseManager().my <= mainMenuButton.y + 100){
                    
                        // Reset floor
                        manager.entities.clear();
                        //generate
                        generateEntity();
                        //increment level counter
                        currentLevel=0;
                        //unpause game
                        levelUp=false;
                        //set state to main menu
                        StateManager.setState(game.mainMenu);
                        //reset score
                        score=0;
                        //pause fix
                        game.getKeyManager().pause = false;
                        //bullet ready
                        Egg.ready=true;
                }
                            
            }
            
        }
    }

    
    //Main methods
     @Override
    public void tick() {
        
        if(score >= (100 + currentLevel*100)){
            levelUp=true;
            game.getKeyManager().pause = true;
        }
        
        if(!levelUp && !game.getKeyManager().pause && !dead)
            manager.tick();
        
        checkDead();
        checkLevelUp();
    }
    
    
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if(currentLevel<2)
            g2d.drawImage(backGround[0],0,0,game.getWindow().getWidth(),game.getWindow().getHeight(),null);
        else
            g2d.drawImage(backGround[1],0,0,game.getWindow().getWidth(),game.getWindow().getHeight(),null);
        
        
        //Score
        drawScore(g);
        
        manager.render(g);
        
        //LevelUp screen
        if(levelUp)
            drawLevelUp(g,g2d);
        
        //Death screen
        if(dead)
            drawDead(g,g2d);
        
        //Pause Img
        if(game.getKeyManager().pause && !levelUp)
            g2d.drawImage(pauseImg,game.getWindow().getWidth()/2 - 250,game.getWindow().getHeight()/2 - 200
                    ,450, 250,null);
        
    }
    
    public void drawDead(Graphics g,Graphics2D g2d){
        
        
        g2d.drawImage(lifeStatus[0],game.getWindow().getWidth()/2 - 400,game.getWindow().getHeight()/2 - 350
                    ,+ 650, 350,null);
        
        if(!mainMenu2Button.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(mainMenuImage[0],mainMenu2Button.x ,mainMenu2Button.y ,mainMenu2Button.width,mainMenu2Button.height ,null);
        else
            g2d.drawImage(mainMenuImage[1],mainMenu2Button.x ,mainMenu2Button.y ,mainMenu2Button.width,mainMenu2Button.height ,null);
        
        if(!quitButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(quitImage[0],mainMenuButton.x ,quitButton.y ,quitButton.width,quitButton.height ,null);
        else
            g2d.drawImage(quitImage[1],mainMenuButton.x ,quitButton.y ,quitButton.width,quitButton.height ,null);
        
    }
    
    public void drawLevelUp(Graphics g,Graphics2D g2d){
        
        g2d.drawImage(lifeStatus[1],game.getWindow().getWidth()/2 - 400,game.getWindow().getHeight()/2 - 350
                    ,+ 650, 350,null);
        
        if(!nextLevelButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(nextLevelImage[0],nextLevelButton.x ,nextLevelButton.y ,nextLevelButton.width,nextLevelButton.height ,null);
        else
            g2d.drawImage(nextLevelImage[1],nextLevelButton.x ,nextLevelButton.y ,nextLevelButton.width,nextLevelButton.height ,null);
        
        if(!mainMenuButton.contains(game.getMouseManager().mx,game.getMouseManager().my))
            g2d.drawImage(mainMenuImage[0],mainMenuButton.x ,mainMenuButton.y ,mainMenuButton.width,mainMenuButton.height ,null);
        else
            g2d.drawImage(mainMenuImage[1],mainMenuButton.x ,mainMenuButton.y ,mainMenuButton.width,mainMenuButton.height ,null);
        
        
        
    }
    
    public void drawScore(Graphics g){
        
        Font font1 = new Font("monospaced",Font.BOLD,30);
        g.setColor(Color.GREEN);
        g.setFont(font1);
        g.drawString("SCORE: "+score,5,30);
        g.drawString("LEVEL: "+ (currentLevel +1),5,60);
        
    }
    
}
