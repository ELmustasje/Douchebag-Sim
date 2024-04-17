package no.uib.inf101.sample.rooms;

import no.uib.inf101.sample.equipment.IEquipment;
import no.uib.inf101.sample.sprites.ISprite;

import java.awt.*;

public interface IRoom {
    void drawRoom(Graphics2D g2);
    void setDefaultValues();
    void setSpriteToRoom(ISprite sprite);
    void update();
    Point getNextRoomButton();
    Point getPrevRoomButton();
    IEquipment getPlot();
}
