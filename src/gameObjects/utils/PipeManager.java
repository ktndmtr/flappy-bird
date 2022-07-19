package gameObjects.utils;

import gameObjects.Pipe;
import gameObjects.PipeDown;
import gameObjects.PipeUp;
import main.GamePanel;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PipeManager {

    public static final long startDelay = (long) (4 * 1e9);
    public static final int horizontalGap = 250;
    public static final int maxHeight = 450;
    public static final int minHeight = 50;

    private final GamePanel gp;
    private Deque<List<Pipe>> pipePairs;
    private long startTime;

    public PipeManager(GamePanel gp){
        this.gp = gp;
        setDefaultValues();
    }

    private void setDefaultValues(){
        pipePairs = new LinkedList<>();
        for(int i = 0; i < 3; i++) {
            List<Pipe> newPipesPair = generatePipesPair();
            pipePairs.add(newPipesPair);
        }
        startTime = System.nanoTime();
    }

    public void update(){
        if(System.nanoTime() - startTime < startDelay)
            return;

        for(List<Pipe> pipesPair : pipePairs){
            for(Pipe pipe : pipesPair)
                pipe.update();
        }
        Pipe firstPipe = pipePairs.getFirst().get(0);
        if(!firstPipe.withinScreen()) {
            pipePairs.pop();
            List<Pipe> newPipesPair = generatePipesPair();
            pipePairs.add(newPipesPair);
        }
    }

    public void draw(Graphics g){
        if(System.nanoTime() - startTime < startDelay)
            return;
        for(List<Pipe> pipesPair : pipePairs){
            for(Pipe pipe : pipesPair)
                pipe.draw(g);
        }
    }

    private List<Pipe> generatePipesPair(){
        List<Pipe> pipesPair = new LinkedList<>();
        Random random = new Random();
        int height = random.nextInt(maxHeight - minHeight) + minHeight;
        Pipe pipeUp = new PipeUp(gp, height);
        Pipe pipeDown = new PipeDown(gp, height);

        if(pipePairs.size() != 0) {
            pipeUp.x = pipeDown.x = pipePairs.getLast().get(0).x + horizontalGap + pipeUp.tileWidth;
        }
        pipesPair.add(pipeUp);
        pipesPair.add(pipeDown);
        return pipesPair;
    }

    public void restart(){
        PipeDown.restart();
        PipeUp.restart();
        setDefaultValues();
    }

    public Deque<List<Pipe>> getPipePairs() {
        return pipePairs;
    }
}
