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

    int x,y,width,height,buttonX,buttonY, buttonWidth, buttonHeight;

    int reps;
    int clicksForRep;
    int clickCount;
    int clickReverse;
    int skipFrames;
    int skipInterval;

    boolean using;
    boolean finished;
    BufferedImage img;
    BufferedImage button;
    MouseHandler mouseHandler;

    public BenchPress(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;
        buttonX = 80;
        buttonY = 270;
        buttonHeight = 80;
        buttonWidth = 80;
        skipInterval = 25;
        resetEquipment();
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
    public boolean successfulSet() {
        return finished;
    }

    @Override
    public void resetEquipment() {
        clickReverse = 1;
        clicksForRep = 10;
        skipFrames = 0;
        finished = false;
        using = false;
        setDefaultImg();
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
        return mouseHandler.mouseX >= buttonX && mouseHandler.mouseX <= buttonX + buttonWidth
                && mouseHandler.mouseY >= buttonY && mouseHandler.mouseY <= buttonY + buttonHeight;
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

            drawStats(g2);
        }
    }

    @Override
    public void drawStats(Graphics2D g2) {
        int interval = 170/clicksForRep;
        g2.drawRect(100,70,40,170);
        g2.fillRect(buttonX,buttonY,buttonWidth,buttonHeight); //button
        g2.fillRect(100,240-interval*clickCount,40,interval*clickCount);
    }


    @Override
    public void update() {
        if(using){
            mouseHandler.update();
            if (clickCount == clicksForRep){
                reps++;
                clickCount=0;
            }
            skipFrames++;
            if(skipFrames >= skipInterval){
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
                finished = true;
                reps = 0;
            }
            try {
                img = ImageIO.read(new File("res/sprites/spriteBench/"+clickCount+".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        mouseHandler.mouseReleased(null);
    }

    @Override
    public void changeDifficulty(ISprite sprite) {
        skipInterval-= sprite.getStrength()*2;
    }
}
