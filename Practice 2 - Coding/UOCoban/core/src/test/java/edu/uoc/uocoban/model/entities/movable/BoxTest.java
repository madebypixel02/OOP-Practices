package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Direction;
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

public class BoxTest {

    private Level level1;
    private Level level2;
    private Box box;

    @BeforeEach
    public void initBox() {
        try {
            level1 = new Level("levels/level1.txt");
            level2 = new Level("levels/level2.txt");
            box = level1.getBoxList().stream().findFirst().orElse(null);
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with BigBoxTest initialization");
        }
    }

    @Test
    @Tag("initLevel")
    public void testInitBox() {
        assertEquals(new Position(3, 3), box.getPosition());
        assertEquals(level1, box.getLevel());
        assertFalse(box.isDelivered());
    }

    @Test
    @Tag("initLevel")
    public void testIsPathable() {
        assertFalse(box.isPathable());
    }

    @Test
    @Tag("playLevel")
    public void testMoveBox() {
        assertFalse(box.move(Direction.LEFT));
        assertEquals(new Position(3,3), box.getPosition());
        assertFalse(box.isDelivered());

        assertTrue(box.move(Direction.RIGHT));
        assertEquals(new Position(4,3), box.getPosition());

        assertFalse(box.move(Direction.RIGHT));
        assertEquals(new Position(4,3), box.getPosition());

        assertFalse(box.move(Direction.UP));
        assertEquals(new Position(4,3), box.getPosition());

        assertFalse(box.move(Direction.DOWN));
        assertEquals(new Position(4,3), box.getPosition());
        assertFalse(box.isDelivered());

    }

    @Test
    @Tag("playLevel")
    public void testDeliverBigBox() {
        assertTrue(box.move(Direction.UP));
        assertEquals(new Position(3,2), box.getPosition());
        assertFalse(box.isDelivered());

        assertTrue(box.move(Direction.UP));
        assertTrue(box.isDelivered());
    }

    @Test
    @Tag("playLevel")
    public void testDeliverSmallBox() {
        box = level1.getBoxList().stream().filter(b -> b instanceof SmallBox).findFirst().orElse(null);

        assertTrue(box.move(Direction.RIGHT));
        assertEquals(new Position(6,3), box.getPosition());
        assertFalse(box.isDelivered());

        assertTrue(box.move(Direction.RIGHT));
        assertTrue(box.isDelivered());
    }

    @Test
    @Tag("playLevel")
    public void testDeliverBigBoxInSmallBoxDestination() {
        BigBox bigBox = (BigBox) level2.getBoxList().stream().filter(b -> b instanceof BigBox).findFirst().orElse(null);

        assertTrue(bigBox.move(Direction.DOWN));
        assertEquals(new Position(5,2), bigBox.getPosition());

        assertTrue(bigBox.move(Direction.DOWN));
        assertEquals(new Position(5,3), bigBox.getPosition());

        assertTrue(bigBox.move(Direction.RIGHT));
        assertEquals(new Position(6,3), bigBox.getPosition());

        assertFalse(bigBox.move(Direction.RIGHT));
        assertEquals(new Position(6,3), bigBox.getPosition());
    }

    @Test
    @Tag("playLevel")
    public void testDeliverSmallBoxInBigBoxDestination() {
        SmallBox smallBox = (SmallBox) level2.getBoxList().stream().filter(b -> b instanceof SmallBox).findFirst().orElse(null);

        assertTrue(smallBox.move(Direction.RIGHT));
        assertEquals(new Position(5,2), smallBox.getPosition());

        assertTrue(smallBox.move(Direction.RIGHT));
        assertEquals(new Position(6,2), smallBox.getPosition());

        assertTrue(smallBox.move(Direction.RIGHT));
        assertEquals(new Position(7,2), smallBox.getPosition());

        assertTrue(smallBox.move(Direction.RIGHT));
        assertEquals(new Position(8,2), smallBox.getPosition());

        assertFalse(smallBox.move(Direction.UP));
        assertEquals(new Position(8,2), smallBox.getPosition());

        assertFalse(smallBox.move(Direction.UP));
        assertEquals(new Position(8,2), smallBox.getPosition());

    }

    @Test
    @Tag("playLevel")
    public void testBlockingBoxes() {
        SmallBox smallBox = (SmallBox) level2.getBoxList().stream().filter(b -> b instanceof SmallBox).findFirst().orElse(null);

        assertTrue(smallBox.move(Direction.RIGHT));
        assertEquals(new Position(5,2), smallBox.getPosition());

        assertFalse(smallBox.move(Direction.UP));
        assertFalse(smallBox.move(Direction.UP));
        assertEquals(new Position(5,2), smallBox.getPosition());

        assertTrue(smallBox.move(Direction.DOWN));
        assertTrue(smallBox.move(Direction.LEFT));
        assertTrue(smallBox.move(Direction.LEFT));
        assertTrue(smallBox.move(Direction.LEFT));
        assertTrue(smallBox.move(Direction.UP));
        assertFalse(smallBox.move(Direction.LEFT));
        assertFalse(smallBox.move(Direction.LEFT));
        assertEquals(new Position(2,2), smallBox.getPosition());

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Box - Class definition")
    void checkClassSanity() {
        final Class<Box> ownClass = Box.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertTrue(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.movable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Box - Fields definition")
    public void checkFieldsSanity() {
        final Class<Box> ownClass = Box.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("delivered").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredField("delivered").getType());

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Box - Methods definition")
    public void checkMethodsSanity() {
        final Class<Box> ownClass = Box.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Sprite.class, Level.class).getModifiers();
            assertTrue(Modifier.isProtected(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("[ERROR] Box's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(3, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());
        //Min private methods: 0
        assertTrue(Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPrivate(p.getModifiers())).count() >= 1);

    }

}
