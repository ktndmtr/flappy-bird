package main;


import gameObjects.FlappyBird;
import gameObjects.Ground;
import gameObjects.utils.CollisionChecker;
import gameObjects.utils.PipeManager;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int screenWidth = 600;
    public final int screenHeight = 800;
    public final int FPS = 60;
    public final KeyHandler keyH = new KeyHandler();
    public final FlappyBird flappyBird = new FlappyBird(this, keyH);
    public final Ground ground = new Ground(this);
    public final PipeManager pipeManager = new PipeManager(this);
    public final CollisionChecker collisionChecker = new CollisionChecker(this);
    public final GameManager gameManager = new GameManager(this);
    public final UI ui = new UI(this);

    private Thread gameThread;
    private Image background;

    public GamePanel(){
        loadBackground();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    private void loadBackground() {
        background = new ImageIcon("assets/game-objects/background-day.png").getImage();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1e9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            long difTime = (currentTime - lastTime);
            delta += difTime / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
        }
    }

    public void update(){
        pipeManager.update();
        ground.update();
        flappyBird.update();

        gameManager.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        pipeManager.draw(g2);
        ground.draw(g2);
        flappyBird.draw(g2);
        ui.draw(g2);

        g.dispose();
    }
}
