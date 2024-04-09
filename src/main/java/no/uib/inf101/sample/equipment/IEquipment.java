package no.uib.inf101.sample.equipment;

import no.uib.inf101.sample.rooms.Plot;
import no.uib.inf101.sample.sprites.ISprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IEquipment {
    void use();
    boolean isInUse();
    void hovering();
    void setDefaultImg();
    boolean hoveringButtion();
    int getHeigth();
    int getWidth();
    int getY();
    int getX();
    void addToPlot(Plot plot);
    BufferedImage getImg();
    void draw(Graphics2D g2);
    void update();
}
