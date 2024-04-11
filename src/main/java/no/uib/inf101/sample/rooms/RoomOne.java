package no.uib.inf101.sample.rooms;

import no.uib.inf101.sample.GamePanel;
import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.equipment.BenchPress;
import no.uib.inf101.sample.equipment.IEquipment;
import no.uib.inf101.sample.sprites.ISprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoomOne implements IRoom{

    BufferedImage img;
    Plot plot;
    MouseHandler mouseHandler;


    public RoomOne(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;
        setDefaultValues();
        plot = new Plot(150,250,300,400);
        plot.addEquiptment(new BenchPress(mouseHandler));
    }

    @Override
    public void drawRoom(Graphics2D g2) {
        g2.drawImage(img,0,0, GamePanel.WIDTH,GamePanel.HEIGTH,null);
        plot.draw(g2);
    }


    @Override
    public void setDefaultValues() {
        try {
            img = ImageIO.read(new File("res/rooms/roomOne.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSpriteToRoom(ISprite sprite) {
        //to flip the image take g2.drawImage(image, x + width, y, -width, height, null);
        sprite.setValues(GamePanel.WIDTH/2 + sprite.getWidth(),GamePanel.HEIGTH/2-150,-sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void update() {
        plot.update();
    }

    @Override
    public IEquipment getPlot() {
        return plot.equipment;
    }
}
