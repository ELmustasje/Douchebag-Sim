package no.uib.inf101.sample.equipment.flappyBird;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Obsticle {
    int x;
    int y;
    int height;
    int width;
    int opening = 70;
    Random random;
    FlappyBird game;
    BufferedImage pipeDown;
    BufferedImage pipeUp;

    public Obsticle(int x,FlappyBird game){
        this.game = game;
        random = new Random();
        this.x = x;
        y = 150;
        width = 25;
        height = random.nextInt(20,120);
        try {
            pipeDown = ImageIO.read(new File("res/flappyBird/pipeDown.png"));
            pipeUp = ImageIO.read(new File("res/flappyBird/pipeUp.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void update(){
        x-= game.speed;
        if(x < 100){
            x =870;
            height = random.nextInt(20,120);
        }
    }
    protected void draw(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(x,y,width,height);
        g2.fillRect(x,y+opening+height,width,200);
    }
}
