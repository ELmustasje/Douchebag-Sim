package no.uib.inf101.sample;

import no.uib.inf101.sample.rooms.RoomManager;
import no.uib.inf101.sample.sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    public final static int WIDTH = 800;
    public final static int HEIGTH = 600;
    public static boolean showFPS = true;

    int FPS = 60;

    MouseHandler mouseH = new MouseHandler();
    Thread gameThread;
    Sprite sprite = new Sprite(this,mouseH);
    RoomManager roomManager = new RoomManager(sprite,mouseH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGTH));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int drawCount = -1;

        while (gameThread != null){
            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;
            timer += drawInterval;
            drawCount++;

            if(timer >= 1000000000){
                if(showFPS){
                    System.out.println("FPS:"+drawCount);
                }

                drawCount = -1;
                timer = 0;
            }
            if(remainingTime < 0){
                remainingTime = 0;
            }



            try {
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime += drawInterval;
        }
    }
    public void update(){
        sprite.update();
        roomManager.update();
        mouseH.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        roomManager.draw(g2);
        sprite.drawSprite(g2);
        g2.dispose();
    }
}
