package gameObjects;

import main.GamePanel;

import java.awt.*;

public abstract class Pipe extends GameObject {

    public static final int verticalGap = 200;

    private final int height;
    public Image image;
    public int id;

    public Pipe(GamePanel gp, int height) {
        super(gp);
        this.height = height;

        x = gp.screenWidth;

        tileWidth = 130;
        tileHeight = gp.screenHeight;

        getImage();
    }


    @Override
    public void update() {
        if(gp.gameManager.isGameOver())
            return;
        x -= horizontalSpeed;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, tileWidth, tileHeight, null);
    }

    public boolean withinScreen(){
        if(x + tileWidth < 0)
            return false;
        return true;
    }
}
