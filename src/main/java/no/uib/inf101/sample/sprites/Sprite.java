package no.uib.inf101.sample.sprites;

import no.uib.inf101.sample.GamePanel;
import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.equipment.IEquipment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite implements ISprite{
    int x;
    int y;
    int heigth;
    int width;
    int strength;


    GamePanel gp;
    MouseHandler mouseH;
    BufferedImage currentImg;
    BufferedImage drawingImg;
    BufferedImage bisepForStat;


    public Sprite(GamePanel gp, MouseHandler mouseH){
        this.gp = gp;
        this.mouseH = mouseH;
        setDefaultValues();
        drawingImg = currentImg;
    }



    @Override
    public void drawSprite(Graphics2D g2) {
        g2.drawImage(drawingImg,x,y,width,heigth,null);
        drawStats(g2);

    }

    @Override
    public void update() {

    }

    @Override
    public void setDefaultValues() {
        this.x = gp.WIDTH/2;
        this.y = gp.HEIGTH/2;
        this.heigth = 400;
        this.width = 500;
        this.strength = 0;
        try {
            currentImg = ImageIO.read(new File("res/sprites/spriteDefault.png"));
            bisepForStat = ImageIO.read(new File("res/Other/BisepForStats.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValues(int x, int y, int width, int height) {
       this.x = x;
       this.y = y;
       this.width = width;
       this.heigth = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return heigth;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void updateStrength(int deltaStrength) {
        strength+= deltaStrength;
        if(strength > 10){
            strength = 10;
        }

    }

    @Override
    public void stopDrawing() {
        drawingImg = null;
    }

    @Override
    public void startDrawing() {
        drawingImg = currentImg;
    }

    @Override
    public void drawStats(Graphics2D g2) {
        int boxWidth = 200;
        int boxHeigth = 25;
        int interval = 200/10;//strength step
        int boxX = gp.getWidth() - boxWidth - 15;
        int boxY = 15;

        g2.drawRect(boxX,boxY,boxWidth,boxHeigth);
        g2.setColor(Color.red);
        g2.fillRect(boxX,boxY,interval*strength,boxHeigth);
        Color myColor = new Color(255,255,255,50);
        g2.setColor(myColor);
        g2.fillRect(boxX-50,boxY-10,50,45);
        g2.drawImage(bisepForStat,boxX-50,boxY-7,45,40,null);
    }

}
