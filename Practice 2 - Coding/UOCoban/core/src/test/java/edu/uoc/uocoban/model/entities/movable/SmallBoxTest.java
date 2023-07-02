package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallBoxTest {

    private Level level;
    private SmallBox smallBox;

    @BeforeEach
    public void initBigBox() {
        try {
            level = new Level("levels/level1.txt");
            smallBox = (SmallBox) level.getBoxList().stream().filter(b -> b instanceof SmallBox).findFirst().orElse(null);
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with SmallBoxTest initialization");
        }
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        smallBox.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), smallBox.getPosition());
    }

    @Test
    @Tag("initLevel")
    public void testInitSmallBox() {
        assertEquals(Sprite.SMALL_BOX, smallBox.getSprite());
        assertEquals(new Position(5, 3), smallBox.getPosition());
        assertEquals(level, smallBox.getLevel());
        assertFalse(smallBox.isDelivered());
        assertFalse(smallBox.isPathable());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity SmallBox - Class definition")
    void checkClassSanity() {
        final Class<SmallBox> ownClass = SmallBox.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.movable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity SmallBox - Fields definition")
    public void checkFieldsSanity() {
        final Class<SmallBox> ownClass = SmallBox.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity SmallBox - Methods definition")
    public void checkMethodsSanity() {
        final Class<SmallBox> ownClass = SmallBox.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Level.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("[ERROR] SmallBox's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

    }

}
