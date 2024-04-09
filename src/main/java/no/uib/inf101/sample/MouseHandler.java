package no.uib.inf101.sample;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public int mouseX;
    public int mouseY;
    public boolean mousePressed;

    public MouseHandler(){
        update();
    }

    public void update(){
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
        mouseX -= Main.window.getX();
        mouseY -= Main.window.getY();
    }

    public String mouseInfoString(){
        return "mouseX: " + mouseX + "mouseY: " + mouseY;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        update();
        mousePressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
