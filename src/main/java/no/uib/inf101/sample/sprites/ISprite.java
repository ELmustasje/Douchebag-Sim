package no.uib.inf101.sample.sprites;

import no.uib.inf101.sample.equipment.IEquipment;

import java.awt.*;

public interface ISprite {
    void drawSprite(Graphics2D g2);
    void update();
    void setDefaultValues();
    void setValues(int x, int y, int width, int height);
    int getWidth();
    void stopDrawing();
    void startDrawing();
}
