package main;

import gameObjects.FlappyBird;
import gameObjects.Ground;
import gameObjects.Pipe;
import gameObjects.utils.PipeManager;

import java.util.List;

public class GameManager {

    private GamePanel gp;
    private KeyHandler keyH;
    private FlappyBird flappyBird;
    private Ground ground;
    private PipeManager pipeManager;
    private boolean gameOver = false;
    private long gameOverLastTime;
    private int currentScore;
    private int maxScore;

    public GameManager(GamePanel gp) {
        this.gp = gp;
        this.keyH = gp.keyH;
        this.flappyBird = gp.flappyBird;
        this.ground = gp.ground;
        this.pipeManager = gp.pipeManager;
        currentScore = maxScore = 0;
    }

    public void update(){
        if(gameOver){
            if(keyH.spacePressed && System.nanoTime() - gameOverLastTime > 2*1e9)
                restart();
            return;
        }
        if(gp.collisionChecker.check()){
            gameOver();
            return;
        }
        //check that bird passes pipe
        for(List<Pipe> pipesPair : pipeManager.getPipePairs()){
            Pipe pipe = pipesPair.get(0);
            if(pipe.id == currentScore){
                if(flappyBird.x + flappyBird.tileWidth/2 >= pipe.x + pipe.tileWidth/2)
                    currentScore++;
                break;
            }
        }
    }

    public void restart(){
        flappyBird.restart();
        ground.restart();
        pipeManager.restart();

        gameOver = false;

        currentScore = 0;
    }

    public void gameOver(){
        gameOver = true;
        gameOverLastTime = System.nanoTime();
        maxScore = currentScore > maxScore ? currentScore : maxScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
