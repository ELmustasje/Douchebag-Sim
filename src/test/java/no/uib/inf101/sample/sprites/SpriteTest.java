package no.uib.inf101.sample.sprites;

import no.uib.inf101.sample.sprites.ISprite;
import no.uib.inf101.sample.sprites.Sprite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {

    @Test
    void sanityTest(){
        ISprite sprite = new Sprite(null,null);
        assertEquals(400,sprite.getHeight());
        assertEquals(150,sprite.getWidth());
        assertEquals(0,sprite.getStrength());
        assertFalse(sprite.isMirrored());
    }

    @Test
    void testSetValues(){
        ISprite sprite = new Sprite(null,null);
        sprite.setValues(1,1,1,1);
        assertEquals(1,sprite.getX());
        assertEquals(1,sprite.getY());
        assertEquals(1,sprite.getWidth());
        assertEquals(1,sprite.getHeight());
    }

    @Test
    void testUpdateStrength(){
        ISprite sprite = new Sprite(null,null);
        assertEquals(0,sprite.getStrength());
        sprite.updateStrength(1);
        assertEquals(1,sprite.getStrength());
        sprite.updateStrength(999);
        assertEquals(10,sprite.getStrength());
    }
}
