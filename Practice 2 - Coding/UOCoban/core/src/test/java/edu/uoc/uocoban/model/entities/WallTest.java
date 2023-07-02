package edu.uoc.uocoban.model.entities;

import edu.uoc.uocoban.model.Level;
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

public class WallTest {

    private Wall wall;

    @BeforeEach
    public void initWallTest() {
        wall = new Wall(new Position(0,0));
    }

    @Test
    @Tag("initLevel")
    void testConstructor() {
        assertEquals(new Position(0,0), wall.getPosition());
        assertEquals(wall.getSprite(), Sprite.WALL);
    }

    @Test
    @Tag("initLevel")
    void testIsPathable() {
        assertFalse(wall.isPathable());
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        wall.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), wall.getPosition());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Wall - Class definition")
    void checkClassSanity() {
        final Class<Wall> ownClass = Wall.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Wall - Fields definition")
    public void checkFieldsSanity() {
        final Class<Wall> ownClass = Wall.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Wall - Methods definition")
    public void checkMethodsSanity() {
        final Class<Wall> ownClass = Wall.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Wall's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(1, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isPathable").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isPathable").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
