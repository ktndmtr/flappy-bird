package gameObjects;


import main.GamePanel;

import javax.swing.*;

public class PipeUp extends Pipe{

    private static int counter = 0;

    public PipeUp(GamePanel gp, int height) {
        super(gp, height);
        this.y = height + verticalGap;
        this.id = counter++;
    }

    public static void restart(){
        counter = 0;
    }

    @Override
    public void getImage() {
        image = new ImageIcon("assets/game-objects/pipe-up.png").getImage();
    }
}
