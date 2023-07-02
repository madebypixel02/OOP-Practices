package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class MovableEntityTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity MovableEntity - Class definition")
    void checkClassSanity() {
        final Class<MovableEntity> ownClass = MovableEntity.class;

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
    @DisplayName("Sanity Level - Fields definition")
    public void checkFieldsSanity() {
        final Class<MovableEntity> ownClass = MovableEntity.class;

        //check attribute fields
        assertEquals(1, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("level").getModifiers()));
            assertEquals(Level.class, ownClass.getDeclaredField("level").getType());

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Level - Methods definition")
    public void checkMethodsSanity() {
        final Class<MovableEntity> ownClass = MovableEntity.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Sprite.class, Level.class).getModifiers();
            assertTrue(Modifier.isProtected(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] MovableEntity's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(2, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());
        //Min private methods: 0
        assertTrue(Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPrivate(p.getModifiers())).count() >= 1);

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getLevel").getModifiers()));
            assertEquals(Level.class, ownClass.getDeclaredMethod("getLevel").getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setLevel", Level.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setLevel", Level.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("move", Direction.class).getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("move", Direction.class).getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("move", Direction.class).getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
