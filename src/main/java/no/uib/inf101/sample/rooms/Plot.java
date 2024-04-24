package no.uib.inf101.sample.rooms;

import no.uib.inf101.sample.equipment.IEquipment;
import no.uib.inf101.sample.sprites.ISprite;

import java.awt.*;

public class Plot {

    int x,y,heigth,width;
    IEquipment equipment;

    public Plot(int x,int y,int heigth,int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHeigth(){
        return heigth;
    }
    public int getWidth(){
        return width;
    }

    public void addEquiptment(IEquipment equipment){
        this.equipment = equipment;
        equipment.addToPlot(this);
    }

    public void draw(Graphics2D g2){
        if(equipment == null){
            return;
        }
        equipment.draw(g2);
    }
    public void update(){
        equipment.update();
    }

}
