package edu.uoc.uocoban.model.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    @Tag("initLevel")
    void getX() {
        assertEquals(0,Direction.UP.getX());
        assertEquals(0,Direction.DOWN.getX());
        assertEquals(1,Direction.RIGHT.getX());
        assertEquals(-1,Direction.LEFT.getX());
    }

    @Test
    @Tag("initLevel")
    void getY() {
        assertEquals(-1,Direction.UP.getY());
        assertEquals(1,Direction.DOWN.getY());
        assertEquals(0,Direction.RIGHT.getY());
        assertEquals(0,Direction.LEFT.getY());
    }

    @Test
    @Tag("initLevel")
    void getKeyCode() {
        assertEquals(19,Direction.UP.getKeyCode());
        assertEquals(20,Direction.DOWN.getKeyCode());
        assertEquals(22,Direction.RIGHT.getKeyCode());
        assertEquals(21,Direction.LEFT.getKeyCode());
    }

    @Test
    @Tag("basic")
    void getDirectionByKeyCode() {
        assertEquals(19,Direction.UP.getKeyCode());
        assertEquals(20,Direction.DOWN.getKeyCode());
        assertEquals(22,Direction.RIGHT.getKeyCode());
        assertEquals(21,Direction.LEFT.getKeyCode());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Direction - Enum definition")
    void checkEnumSanity() {
        final Class<Direction> ownClass = Direction.class;

        assertTrue(ownClass.isEnum());
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertTrue(Modifier.isFinal(modifiers));

        assertEquals("edu.uoc.uocoban.model.utils",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Direction - Fields definition")
    public void checkFieldsSanity() {
        final Class<Direction> ownClass = Direction.class;

        //check attribute fields: 3 attributes + 4 values + $VALUES
        assertEquals(8, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("x").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("x").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("y").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("y").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("keyCode").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("keyCode").getType());
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Direction - Methods definition")
    public void checkMethodsSanity() {
        final Class<Direction> ownClass = Direction.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor: the first parameter is the value itself and the second one is the ordinal.
            int constructorModifiers = ownClass.getDeclaredConstructor(String.class, int.class, int.class, int.class, int.class).getModifiers();
            assertTrue(Modifier.isPrivate(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Direction's constructor is defined wrongly");
        }

        //Max public methods: 4 methods + values() + valueOf()
        assertEquals(6, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());
        //Min private methods: $values()
        assertTrue(Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPrivate(p.getModifiers())).count() >= 1);

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getX").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getX").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getY").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getY").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getKeyCode").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getKeyCode").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getDirectionByKeyCode", int.class).getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredMethod("getDirectionByKeyCode", int.class).getModifiers()));
            assertEquals(Direction.class, ownClass.getDeclaredMethod("getDirectionByKeyCode", int.class).getReturnType());
            assertEquals(int.class, ownClass.getDeclaredMethod("getKeyCode").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
