package gameObjects.utils;

import gameObjects.Pipe;
import main.GamePanel;

import java.util.List;

public class CollisionChecker {

    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean check(){
        return checkGround() || checkPipes();
    }

    private boolean checkGround(){
        if(gp.flappyBird.getRectangle().intersects(gp.ground.getRectangle())) {
            return true;
        }
        return false;
    }

    private boolean checkPipes(){
        for(List<Pipe> pipesPair : gp.pipeManager.getPipePairs()){
            for(Pipe pipe : pipesPair) {
                if (gp.flappyBird.getRectangle().intersects(pipe.getRectangle())) {
                    return true;
                }
            }
        }
        return false;
    }

}
