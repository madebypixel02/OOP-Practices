package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    private Path path;

    @BeforeEach
    public void initPathTest() {
        path = new Path(new Position(0,0));
    }

    @Test
    @Tag("initLevel")
    void testConstructor() {
        assertEquals(new Position(0,0), path.getPosition());
        assertEquals(path.getSprite(), Sprite.PATH);
    }

    @Test
    @Tag("initLevel")
    void testIsPathable() {
        assertTrue(path.isPathable());
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        path.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), path.getPosition());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Path - Class definition")
    void checkClassSanity() {
        final Class<Path> ownClass = Path.class;

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
    @DisplayName("Sanity Path - Fields definition")
    public void checkFieldsSanity() {
        final Class<Path> ownClass = Path.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Path - Methods definition")
    public void checkMethodsSanity() {
        final Class<Path> ownClass = Path.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Path's constructor is defined wrongly");
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
