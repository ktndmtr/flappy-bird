package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class GameOverScreen {

    private final UI ui;
    public final int gameOverImageX = 200;
    public final int gameOverImageY = 200;

    public final int rectX = 225;
    public final int rectY = 250;
    public final int rectWidth = 150;
    public final int rectHeight = 200;
    public final int rectArcWidth = 35;
    public final int rectArcHeight = 35;

    public final String curScoreString = "Score:";
    public final int curScoreStringX = 285;
    public final int curScoreStringY = 270;
    public final int scoreDigitsX = 235;
    public final int scoreDigitsY = 290;

    public final int horizontalGap = 40;

    public final String bestScoreString = "Best:";
    public final int bestScoreStringX = 285;
    public final int bestScoreStringY = 370;
    public final int bestScoreDigitsX = 235;
    public final int bestScoreDigitsY = 390;

    public final String restartString = "Press SPACE to restart game.";
    public final int restartStringX = 110;
    public final int restartStringY = 500;

    private Image gameOverImage;

    public GameOverScreen(UI ui) {
        this.ui = ui;
        gameOverImage = new ImageIcon("assets/ui/gameover.png").getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(gameOverImage, gameOverImageX, gameOverImageY, null);
        g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, rectArcWidth, rectArcHeight);

        g.setColor(Color.ORANGE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 15F));

        g.drawString(curScoreString, curScoreStringX, curScoreStringY);

        Deque<Integer> digits = getDigits(ui.gameManager.getCurrentScore());
        int i = 0;
        for (int digit : digits) {
            g.drawImage(ui.getDigitImage(digit), scoreDigitsX + i * horizontalGap, scoreDigitsY, ui.digitImageTileWidth,
                    ui.digitImageTileHeight, null);
            i += 1;
        }

        g.drawString(bestScoreString, bestScoreStringX, bestScoreStringY);

        digits = getDigits(ui.gameManager.getMaxScore());
        i = 0;
        for (int digit : digits) {
            g.drawImage(ui.getDigitImage(digit), bestScoreDigitsX + i * horizontalGap, bestScoreDigitsY,
                    ui.digitImageTileWidth, ui.digitImageTileHeight, null);
            i += 1;
        }

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
        g.drawString(restartString, restartStringX, restartStringY);
    }

    private Deque<Integer> getDigits(int score){
        int currentScore = score;
        Deque<Integer> digits = new LinkedList<>();
        while (currentScore > 0){
            digits.addFirst(currentScore % 10);
            currentScore /= 10;
        }
        if(digits.size() == 0)
            digits.addFirst(0);
        return digits;
    }
}
