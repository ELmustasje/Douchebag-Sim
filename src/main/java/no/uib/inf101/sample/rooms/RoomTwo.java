package no.uib.inf101.sample.rooms;

import no.uib.inf101.sample.GamePanel;
import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.equipment.AbsBench;
import no.uib.inf101.sample.equipment.BenchPress;
import no.uib.inf101.sample.equipment.IEquipment;
import no.uib.inf101.sample.sprites.ISprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoomTwo implements IRoom{

    BufferedImage img;
    Plot plot;
    MouseHandler mouseHandler;

    public RoomTwo(MouseHandler mouseHandler){
        this.mouseHandler = mouseHandler;
        setDefaultValues();
        plot = new Plot(100,250,300,400); //add plot
        plot.addEquiptment(new AbsBench(mouseHandler));
    }

    @Override
    public void drawRoom(Graphics2D g2) {
        g2.drawImage(img,0,0, GamePanel.WIDTH,GamePanel.HEIGTH,null);
        plot.draw(g2);
    }

    @Override
    public void setDefaultValues() {
        try {
            img = ImageIO.read(new File("res/rooms/roomTwo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSpriteToRoom(ISprite sprite) {
        sprite.setValues(-120,200,sprite.getWidth(),sprite.getHeight());

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
