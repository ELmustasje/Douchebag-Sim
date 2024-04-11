package no.uib.inf101.sample;

import javax.swing.*;

/**
 * Hello world!
 */
public class Main {

  public static JFrame window;

  public static void main(String[] args) {
    window = new JFrame();

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("DOUCHEBAG SIMULATOR!!!!");


    GamePanel gamePanel = new GamePanel();
    window.setContentPane(gamePanel);


    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamePanel.startGameThread();
  }
}
