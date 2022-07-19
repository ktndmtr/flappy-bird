package main;

import javax.swing.JFrame;

public class Application extends JFrame {

    public final String title = "Flappy Bird";

    public Application(){
        initUI();
    }

    private void initUI(){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.startGameThread();
    }
}
