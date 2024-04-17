package no.uib.inf101.sample;

import no.uib.inf101.sample.midi.ChadSong;
import no.uib.inf101.sample.rooms.RoomManager;
import no.uib.inf101.sample.sprites.Sprite;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    public final static int WIDTH = 800;
    public final static int HEIGTH = 600;


    MouseHandler mouseH = new MouseHandler();
    Thread gameThread;

    Sprite sprite = new Sprite(this,mouseH);
    RoomManager roomManager = new RoomManager(sprite,mouseH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGTH));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        ChadSong chadSong = new ChadSong();
        chadSong.run();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }

    }

    public void update(){
        if (mouseH.mousePressed){
            roomManager.update();
            mouseH.update();
            mouseH.used();
        }else {
            roomManager.update();
            mouseH.update();
        }


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        roomManager.draw(g2);
        sprite.drawSprite(g2);
        g2.dispose();
    }
}
