package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.entities.movable.Box;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Destination - Class definition")
    void checkClassSanity() {
        final Class<Destination> ownClass = Destination.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertTrue(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.pathable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Destination - Fields definition")
    public void checkFieldsSanity() {
        final Class<Destination> ownClass = Destination.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("box").getModifiers()));
            assertEquals(Box.class, ownClass.getDeclaredField("box").getType());

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Destination - Methods definition")
    public void checkMethodsSanity() {
        final Class<Destination> ownClass = Destination.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Sprite.class).getModifiers();
            assertTrue(Modifier.isProtected(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Wall's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(4, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBox").getModifiers()));
            assertEquals(Box.class, ownClass.getDeclaredMethod("getBox").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setBox", Box.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setBox", Box.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isEmpty").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isEmpty").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isPathable").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isPathable").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
    
}
