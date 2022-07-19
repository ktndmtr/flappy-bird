package ui;

import main.GameManager;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class UI {
    public GameManager gameManager;
    public final int digitImageTileWidth = 32;
    public final int digitImageTileHeight = 48;
    public final Score score;
    public final GameOverScreen gameOverScreen;

    private Image[] digitImages;
    private GamePanel gp;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.gameManager = gp.gameManager;
        getImage();
        score = new Score(this);
        gameOverScreen = new GameOverScreen(this);
    }

    public void draw(Graphics g){
        if(!gameManager.isGameOver()) {
            score.draw(g);
        }
        else{
            gameOverScreen.draw(g);
        }
    }

    public Image getDigitImage(int digit){
        return digitImages[digit];
    }

    private void getImage(){
        digitImages = new Image[10];
        digitImages[0] = new ImageIcon("assets/numbers/0.png").getImage();
        digitImages[1] = new ImageIcon("assets/numbers/1.png").getImage();
        digitImages[2] = new ImageIcon("assets/numbers/2.png").getImage();
        digitImages[3] = new ImageIcon("assets/numbers/3.png").getImage();
        digitImages[4] = new ImageIcon("assets/numbers/4.png").getImage();
        digitImages[5] = new ImageIcon("assets/numbers/5.png").getImage();
        digitImages[6] = new ImageIcon("assets/numbers/6.png").getImage();
        digitImages[7] = new ImageIcon("assets/numbers/7.png").getImage();
        digitImages[8] = new ImageIcon("assets/numbers/8.png").getImage();
        digitImages[9] = new ImageIcon("assets/numbers/9.png").getImage();
    }
}
