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
import static org.junit.jupiter.api.Assertions.fail;

public class BigBoxTest {

    private Level level;
    private BigBox bigBox;

    @BeforeEach
    public void initBigBox() {
        try {
            level = new Level("levels/level1.txt");
            bigBox = (BigBox) level.getBoxList().stream().filter(b -> b instanceof BigBox).findFirst().orElse(null);
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with BigBoxTest initialization");
        }
    }

    @Test
    @Tag("initLevel")
    public void testInitBigBox() {
        assertEquals(Sprite.BIG_BOX, bigBox.getSprite());
        assertEquals(new Position(3, 3), bigBox.getPosition());
        assertEquals(level, bigBox.getLevel());
        assertFalse(bigBox.isDelivered());
        assertFalse(bigBox.isPathable());
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        bigBox.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), bigBox.getPosition());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity BigBox - Class definition")
    void checkClassSanity() {
        final Class<BigBox> ownClass = BigBox.class;

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
    @DisplayName("Sanity BigBox - Fields definition")
    public void checkFieldsSanity() {
        final Class<BigBox> ownClass = BigBox.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity BigBox - Methods definition")
    public void checkMethodsSanity() {
        final Class<BigBox> ownClass = BigBox.class;

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
            fail("[ERROR] BigBox's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

    }

}
