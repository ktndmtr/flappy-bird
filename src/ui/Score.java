package ui;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class Score {

    public final int x = 275;
    public final int y = 100;
    public final int horizontalGap = 40;

    private UI ui;

    public Score(UI ui) {
        this.ui = ui;
    }

    public void draw(Graphics g){
        Deque<Integer> digits = getDigits(ui.gameManager.getCurrentScore());
        int i = 0;
        for (int digit : digits) {
            g.drawImage(ui.getDigitImage(digit), x + i * horizontalGap, y, ui.digitImageTileWidth, ui.digitImageTileHeight, null);
            i += 1;
        }
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
