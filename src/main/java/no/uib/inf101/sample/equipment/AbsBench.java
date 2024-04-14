package no.uib.inf101.sample.equipment;

import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.rooms.Plot;
import no.uib.inf101.sample.sprites.ISprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AbsBench implements IEquipment{

    int x,y,width,height,buttonX,buttonY, buttonWidth, buttonHeight;

    int pic;
    int slidesForSet;
    int slideCount;
    int slideReverse;
    int skipFrames;
    int skipInterval;

    boolean using;
    boolean finished;
    boolean awaitToGoDown;
    BufferedImage img;
    BufferedImage buttonImg;
    MouseHandler mouseHandler;

    public AbsBench(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;
        buttonX = 85;
        buttonY = 320;
        buttonHeight = 80;
        buttonWidth = 80;
        skipInterval = 23;
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
        pic = 1;
        buttonY = 330;
        slideReverse = 1;
        slidesForSet = 4;
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
            buttonImg  = ImageIO.read(new File("res/Other/button.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hoveringSlider() {
        mouseHandler.update();
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
        int interval = 150/slidesForSet;
        g2.setColor(Color.black);
        g2.fillRect(100,100,50,290);

        g2.drawRect(160,100,50,150);
        g2.fillRect(160,250-interval*slideCount,50,interval*slideCount+1);

        if(buttonY < 70){
            buttonY = 70;
        } else if (buttonY > 320){
            buttonY = 320;
        }

        g2.drawImage(buttonImg,buttonX,buttonY,buttonWidth,buttonHeight,null);
    }


    @Override
    public void update() {
        if(using){
            if(hoveringSlider() && mouseHandler.mouseHeld){
                mouseHandler.update();
                buttonY = mouseHandler.mouseY-buttonHeight/2;
                pic = (buttonY)/30;
                if (buttonY <= 70 && !awaitToGoDown){
                    awaitToGoDown = true;
                    slideCount++;
                    if (slideCount == slidesForSet){
                        using = false;
                        finished = true;
                        slideCount = 0;
                    }
                }
                if (buttonY >= 330){
                    awaitToGoDown = false;
                }
            }

            try {
                img = ImageIO.read(new File("res/sprites/spriteBench/"+ (pic-1) +".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void changeDifficulty(ISprite sprite) {
        skipInterval = 22 - sprite.getStrength()*2;
    }
}
