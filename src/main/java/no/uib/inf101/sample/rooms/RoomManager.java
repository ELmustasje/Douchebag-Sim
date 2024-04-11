package no.uib.inf101.sample.rooms;

import no.uib.inf101.sample.MouseHandler;
import no.uib.inf101.sample.sprites.ISprite;

import javax.swing.*;
import java.awt.*;

public class RoomManager extends JPanel{

    IRoom room;
    ISprite sprite;
    MouseHandler mouseHandler;


    public RoomManager(ISprite sprite, MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
        this.room = new RoomOne(mouseHandler);
        this.sprite = sprite;
        room.setSpriteToRoom(sprite);

    }

    public void draw(Graphics2D g2) {
        room.drawRoom(g2);
    }

    public void setNewRoom(IRoom room) {
        this.room = room;
        room.setDefaultValues();
    }

    boolean hoveringRoomPlot(){
        int margin = 4;
        return (
                mouseHandler.mouseX >= room.getPlot().getX() + room.getPlot().getWidth()/margin
                && mouseHandler.mouseX <= room.getPlot().getX() + room.getPlot().getWidth() - room.getPlot().getWidth()/margin
                && mouseHandler.mouseY >= room.getPlot().getY() + room.getPlot().getHeigth()/margin
                && mouseHandler.mouseY <= room.getPlot().getY() +room.getPlot().getHeigth() - room.getPlot().getHeigth()/margin
        );
    }

    public void update() {
        if(hoveringRoomPlot()){
            room.getPlot().hovering();
            if(mouseHandler.mousePressed && !room.getPlot().isInUse()){
                room.getPlot().use();
            }
        }else {
            room.getPlot().setDefaultImg();
        }

        if(room.getPlot().isInUse()){
            sprite.stopDrawing();
        }else {
            room.setDefaultValues();
            sprite.startDrawing();

            if (room.getPlot().successfulSet()){
                sprite.updateStrength(1);
                room.getPlot().resetEquipment();
                room.getPlot().changeDifficulty(sprite);
            }
        }
        room.update();
    }

}
