package no.uib.inf101.sample.equipment;
import static org.junit.jupiter.api.Assertions.*;

import no.uib.inf101.sample.equipment.BenchPress;
import no.uib.inf101.sample.equipment.IEquipment;
import no.uib.inf101.sample.rooms.Plot;
import no.uib.inf101.sample.sprites.Sprite;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class EquipmentTest {

    @Test
    void testAddToPlot(){
        Plot p1 = new Plot(100,100,50,50);
        IEquipment bench = new BenchPress(null);
        bench.addToPlot(p1);
        assertEquals(new Point(p1.getX(),p1.getY()),new Point(bench.getX(),bench.getY()));
        assertEquals(new Point(p1.getWidth(),p1.getHeigth()), new Point(bench.getWidth(),bench.getHeigth()));
    }

    @Test
    void TestChangeDifficulty(){
        Sprite sprite = new Sprite(null,null);
        IEquipment bench = new BenchPress(null);
        sprite.updateStrength(10);
        bench.changeDifficulty(sprite);
        assertEquals(10,sprite.getStrength());
        assertEquals(2,bench.getSkipInterval());
    }
}
