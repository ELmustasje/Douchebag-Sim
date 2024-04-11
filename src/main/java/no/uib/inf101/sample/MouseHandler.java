package no.uib.inf101.sample;

import no.uib.inf101.sample.rooms.RoomManager;

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
        mouseX -= Main.window.getX()+5;
        mouseY -= Main.window.getY()+30;

    }
    public void used(){
        mousePressed = false;
    }

    public String mouseInfoString(){
        return "mouseX: " + mouseX + "mouseY: " + mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
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
