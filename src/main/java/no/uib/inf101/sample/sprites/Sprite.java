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


    GamePanel gp;
    MouseHandler mouseH;
    BufferedImage currentImg;
    BufferedImage drawingImg;


    public Sprite(GamePanel gp, MouseHandler mouseH){
        this.gp = gp;
        this.mouseH = mouseH;
        setDefaultValues();
        try {
            currentImg = ImageIO.read(new File("res/sprites/ChadAsGif/frame_000.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawingImg = currentImg;
    }



    @Override
    public void drawSprite(Graphics2D g2) {
        g2.drawImage(drawingImg,x,y,width,heigth,null);
    }

    @Override
    public void update() {

    }

    @Override
    public void setDefaultValues() {
        this.x = gp.WIDTH/2;
        this.y = gp.HEIGTH/2-150;
        this.heigth = 500;
        this.width = 300;
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
    public void stopDrawing() {
        drawingImg = null;
    }

    @Override
    public void startDrawing() {
        drawingImg = currentImg;
    }

}
