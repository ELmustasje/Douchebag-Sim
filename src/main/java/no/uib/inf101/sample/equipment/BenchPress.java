package no.uib.inf101.sample.equipment;

import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.rooms.Plot;
import no.uib.inf101.sample.sprites.ISprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BenchPress implements IEquipment{

    int x,y,width,height;

    int reps;
    int clicksForRep;
    int clickCount;
    int clickReverse;
    int skipFrames;
    int skipInterval;

    boolean using;
    BufferedImage img;
    BufferedImage button;
    MouseHandler mouseHandler;

    public BenchPress(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;
        clickReverse = 1;
        clicksForRep = 10;
        skipFrames = 0;
        skipInterval = 120;
        setDefaultImg();
    }

    @Override
    public void use() {
        using = true;
    }

    @Override
    public boolean isInUse() {
        return using;
    }

    @Override
    public void hovering() {
        try {
            img = ImageIO.read(new File("res/equipment/benchpressGlow.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDefaultImg() {
        try {
            img = ImageIO.read(new File("res/equipment/benchpress.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hoveringButtion() {
        return mouseHandler.mouseX >= x && mouseHandler.mouseX <= x + 50
                && mouseHandler.mouseY >= y && mouseHandler.mouseY <= y + 50;
    }

    @Override
    public int getHeigth() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void addToPlot(Plot plot) {
        this.x = plot.getX();
        this.y = plot.getY();
        this.width = plot.getWidth();
        this.height = plot.getHeigth();
    }

    @Override
    public BufferedImage getImg() {
        return img;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(img,x,y,width,height,null);
        if(using){
            g2.fillRect(x,y,50,50);
        }
    }


    @Override
    public void update() {
        if(using){
            System.out.println(clickCount + " " + skipFrames + " " + reps);
            if (clickCount == clicksForRep){
                reps++;
                clickCount=0;
            }
            skipFrames++;
            if(skipFrames == skipInterval){
                clickCount -= clickReverse;
                if(clickCount < 0){
                    clickCount = 0;
                }
                skipFrames = 0;
            }
            if(hoveringButtion() && mouseHandler.mousePressed){
                clickCount++;

            }
            if(reps == 3){
                using = false;
                reps = 0;
            }
        }
        mouseHandler.mouseReleased(null);
    }
}
