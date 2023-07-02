package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.entities.movable.BigBox;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BigBoxDestinationTest {

    BigBoxDestination bigBoxDestination;

    @BeforeEach
    public void initTestBigBoxDestination() {
        bigBoxDestination = new BigBoxDestination(new Position(0,0));
    }

    @Test
    @Tag("initLevel")
    public void testBigBoxDestinationConstructor() {
        assertNull(bigBoxDestination.getBox());
        assertEquals(new Position(0,0), bigBoxDestination.getPosition());
        assertEquals(Sprite.BIG_BOX_DESTINATION, bigBoxDestination.getSprite());
        assertTrue(bigBoxDestination.isEmpty());
        assertTrue(bigBoxDestination.isPathable());
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        bigBoxDestination.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), bigBoxDestination.getPosition());
    }

    @Test
    @Tag("playLevel")
    public void testSetBox() {
        try {
            Level level = new Level("levels/level1.txt");
            BigBox box = new BigBox(new Position(0,1), level);

            bigBoxDestination.setBox(box);
            assertEquals(box, bigBoxDestination.getBox());
            assertFalse(bigBoxDestination.isEmpty());
            assertFalse(bigBoxDestination.isPathable());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some problem with the level initialization");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity BigBoxDestination - Class definition")
    void checkClassSanity() {
        final Class<BigBoxDestination> ownClass = BigBoxDestination.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.pathable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity BigBoxDestination - Fields definition")
    public void checkFieldsSanity() {
        final Class<BigBoxDestination> ownClass = BigBoxDestination.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity BigBoxDestination - Methods definition")
    public void checkMethodsSanity() {
        final Class<BigBoxDestination> ownClass = BigBoxDestination.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] BigBoxDestination's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(1, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("mutate").getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("mutate").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
    
}
