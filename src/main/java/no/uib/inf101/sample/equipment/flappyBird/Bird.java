package no.uib.inf101.sample.equipment.flappyBird;

import no.uib.inf101.sample.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird {
    MouseHandler mouseHandler;
    double gravity = 0.2;
    double jumpPower = 3;
    double speedY = 0;
    int x;
    int y;
    int width;
    int height;
    int margin;

    BufferedImage birdImg;

    public Bird(){
        margin = 0;
        x = 200;
        y = 250;
        width = 25;
        height =25;
        try {
            birdImg = ImageIO.read(new File("res/flappyBird/flappybird.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D g2){
        g2.drawImage(birdImg,x,y,25,25,null);
    }
    public void jump(){
        speedY = -jumpPower;
    }
    public void update(){
        speedY += gravity;
        y+= speedY;
    }
    public boolean birdCrash(Obsticle obs){
        if(
                (x > obs.x && x < obs.x + obs.width)
                ||(x+width > obs.x && x+width < obs.x +obs.width)
        ){
            return !(y > obs.y + obs.height && y + height < obs.y + obs.opening + obs.height);
        }
        return false;
    }
    public boolean outOfBounds(FlappyBird game){
        return !(
                y > game.gameY-10
                && y + height < game.gameY+game.gameHeigth+10
                );
    }
}
