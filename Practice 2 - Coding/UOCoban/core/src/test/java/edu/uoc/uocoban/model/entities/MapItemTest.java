package edu.uoc.uocoban.model.entities;

import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MapItemTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity MapItem - Class definition")
    void checkClassSanity() {
        final Class<MapItem> ownClass = MapItem.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertTrue(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity MapItem - Fields definition")
    public void checkFieldsSanity() {
        final Class<MapItem> ownClass = MapItem.class;

        //check attribute fields
        assertEquals(2, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("position").getModifiers()));
            assertEquals(Position.class, ownClass.getDeclaredField("position").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("sprite").getModifiers()));
            assertEquals(Sprite.class, ownClass.getDeclaredField("sprite").getType());
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity MapItem - Methods definition")
    public void checkMethodsSanity() {
        final Class<MapItem> ownClass = MapItem.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Sprite.class).getModifiers();
            assertTrue(Modifier.isProtected(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] MapItem's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(4, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(1, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getPosition").getModifiers()));
            assertEquals(Position.class, ownClass.getDeclaredMethod("getPosition").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setPosition", Position.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setPosition", Position.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getSprite").getModifiers()));
            assertEquals(Sprite.class, ownClass.getDeclaredMethod("getSprite").getReturnType());
            assertTrue(Modifier.isProtected(ownClass.getDeclaredMethod("setSprite", Sprite.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setSprite", Sprite.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isPathable").getModifiers()));
            assertTrue(Modifier.isAbstract(ownClass.getDeclaredMethod("isPathable").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isPathable").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
