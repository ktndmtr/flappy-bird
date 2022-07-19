package gameObjects;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Ground extends GameObject {

    public Image image;


    public Ground(GamePanel gp) {
        super(gp);

        tileWidth = gp.screenWidth*2;
        tileHeight = gp.screenHeight/4;

        getImage();
        setDefaultValues();
    }

    @Override
    public void getImage(){
        image = new ImageIcon("assets/game-objects/ground.png").getImage();
    }

    @Override
    public void update(){
        if(gp.gameManager.isGameOver())
            return;
        x -= horizontalSpeed;
        if(x < -gp.screenWidth)
            x = 0;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, tileWidth, tileHeight, null);
    }

    public void restart(){
        setDefaultValues();
    }

    private void setDefaultValues(){
        x = 0;
        y = gp.screenHeight*9/10;
    }
}
