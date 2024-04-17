package no.uib.inf101.sample.sprites;

import java.awt.*;

public interface ISprite {
    void drawSprite(Graphics2D g2);
    void update();
    void setDefaultValues();
    void setValues(int x, int y, int width, int height);
    int getWidth();
    int getHeight();
    int getStrength();
    void mirrorTurn(boolean turn);
    void updateStrength(int deltaStrength);
    void stopDrawing();
    void startDrawing();
    void drawStats(Graphics2D g2);
}
