package gameObjects;

import main.GamePanel;

import java.awt.*;

public abstract class GameObject {

    public static int horizontalSpeed = 3;

    public final GamePanel gp;
    public int x;
    public int y;
    public int tileWidth;
    public int tileHeight;




    public GameObject(GamePanel gp) {
        this.gp = gp;
        x = y = 0;
    }

    public abstract void getImage();

    public abstract void update();

    public abstract void draw(Graphics g);

    public Rectangle getRectangle(){
        return new Rectangle(x, y, tileWidth, tileHeight);
    }
}
