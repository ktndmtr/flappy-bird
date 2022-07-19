package gameObjects;

import main.GamePanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class FlappyBird extends GameObject {

    public static final int FPS = 6;
    public static final int verticalSpeed = 7;
    public static final int jumpSpeed = verticalSpeed * 3;
    public static final int jumpDuration = (int)1e8;

    private int spriteCounter = 0;
    private Image downflap, midflap, upflap;
    private KeyHandler keyH;
    private long curJumpStartTime;
    private boolean isJumping;
    private boolean ignoreJump;

    public FlappyBird(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        tileWidth = 68;
        tileHeight = 48;
        getImage();
        setDefaultValues();
    }

    @Override
    public void getImage(){
        downflap = new ImageIcon("assets/game-objects/yellowbird-downflap.png").getImage();
        midflap = new ImageIcon("assets/game-objects/yellowbird-midflap.png").getImage();
        upflap = new ImageIcon("assets/game-objects/yellowbird-upflap.png").getImage();
    }

    @Override
    public void update(){
        if(gp.gameManager.isGameOver()) {
            if(y + tileHeight < gp.ground.y) {
                y += verticalSpeed;
                changeState();
            }
            return;
        }
        changeState();
        if(keyH.spacePressed) {
            if(!ignoreJump) {
                curJumpStartTime = System.nanoTime();
                isJumping = true;
                ignoreJump = true;
            }
        }
        else{
            ignoreJump = false;
        }
        if(isJumping){
            y -= jumpSpeed;
            long currentTime = System.nanoTime();
            if (currentTime - curJumpStartTime > jumpDuration){
                curJumpStartTime = 0;
                isJumping = false;
            }
        }
        y += verticalSpeed;
    }

    @Override
    public void draw(Graphics g){
        Image image = null;
        int state = (spriteCounter / (gp.FPS / FPS)) % 3;
        if(state == 0){
            image = downflap;
        }
        else if(state  == 1){
            image = midflap;
        }
        else if(state == 2){
            image = upflap;
        }
        g.drawImage(image, x, y, tileWidth, tileHeight, null);
    }

    public void restart(){
        setDefaultValues();
    }

    private void setDefaultValues(){
        x = gp.screenWidth/2 - tileWidth/2;
        y = gp.screenHeight/2 - tileHeight/2;
        curJumpStartTime = 0;
        isJumping = false;
        ignoreJump = false;
    }

    private void changeState(){
        spriteCounter++;
        if(spriteCounter == gp.FPS){
            spriteCounter = 0;
        }
    }
}
