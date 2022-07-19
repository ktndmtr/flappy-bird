package gameObjects;

import main.GamePanel;

import javax.swing.*;

public class PipeDown extends Pipe{

    private static int counter = 0;

    public PipeDown(GamePanel gp, int height) {
        super(gp, height);
        this.y = height - gp.screenHeight;
        id = counter++;
    }

    public static void restart(){
        counter = 0;
    }

    @Override
    public void getImage() {
        image = new ImageIcon("assets/game-objects/pipe-down.png").getImage();
    }
}
