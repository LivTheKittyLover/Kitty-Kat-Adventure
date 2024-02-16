package src.main;
import javax.swing.JPanel;

import src.entity.Player;
import src.tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTitleSize= 16; //16x16
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale; //scales character to fit res 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth= tileSize * maxScreenCol; //768 pixel
    public final int screenHieght = tileSize * maxScreenRow; //576 pixel

    //WORLD MAP PERAM:
    public final int maxWorldCol= 50;
    public final int maxWorldRow= 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);

    //Constructor:
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHieght)); //Set size of this class 
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);//rendering faster
        this.addKeyListener(keyH);
        this.setFocusable(true);//focused to get keyhandler

    }


    public void startGameThread(){
        gameThread = new Thread(this);//gamePanel is being passed and init
        gameThread.start();

    }
    @Override
    public void run() {//use threatd to run

        double drawInterval = 1000000000/FPS; //very small amount of seconds 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread !=null){

           //1.) UPDATE: such as character pos
            update();

           //2.) Draw the screen w updates
           repaint();

           

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // time remaining between next press
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update(){//moves character updates
        
        player.update();

    }
    public void paintComponent(Graphics g){//draw object to screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;//changing graphics to 2D 

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();//release to system

    }

}
