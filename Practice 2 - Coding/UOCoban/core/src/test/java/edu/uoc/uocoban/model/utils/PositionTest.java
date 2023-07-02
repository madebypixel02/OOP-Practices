package edu.uoc.uocoban.model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    private void initPositionTest() {
        position = new Position(3, 4);
    }

    @Test
    @Tag("initLevel")
    void getX() {
        assertEquals(3, position.getX());
    }

    @Test
    @Tag("initLevel")
    void setX() {
        position.setX(0);
        assertEquals(0, position.getX());

        position.setX(-423);
        assertEquals(-423, position.getX());

        position.setX(64);
        assertEquals(64, position.getX());
    }

    @Test
    @Tag("initLevel")
    void getY() {
        assertEquals(4, position.getY());
    }

    @Test
    @Tag("initLevel")
    void setY() {
        position.setY(-123);
        assertEquals(-123, position.getY());

        position.setY(33);
        assertEquals(33, position.getY());

        position.setY(4);
        assertEquals(4, position.getY());
    }

    @Test
    @Tag("initLevel")
    void testEquals() {
        assertTrue(position.equals(position));

        Position p1 = new Position(3,4);
        assertTrue(position.equals(p1));

        p1.setX(4);
        assertFalse(position.equals(p1));

        p1.setY(5);
        assertFalse(position.equals(p1));

        p1.setX(3);
        p1.setY(4);
        assertTrue(position.equals(p1));
    }

    @Test
    @Tag("initLevel")
    void testHashCode() {
        Position p1 = new Position(3,4);
        assertEquals(position.hashCode(), p1.hashCode());
    }

    @Test
    @Tag("initLevel")
    void testToString() {
        Position p1 = new Position(3,4);
        assertEquals("3,4", p1.toString());

        p1.setX(2);
        assertEquals("2,4", p1.toString());

        p1.setY(99);
        assertEquals("2,99", p1.toString());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Position - Class definition")
    void checkClassSanity() {
        final Class<Position> ownClass = Position.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));

        assertEquals("edu.uoc.uocoban.model.utils",ownClass.getPackageName());
    }


    @Test
    @Tag("sanity")
    @DisplayName("Sanity Position - Fields definition")
    public void checkFieldsSanity() {
        final Class<Position> ownClass = Position.class;

        //check attribute fields
        assertEquals(2, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("x").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("x").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("y").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("y").getType());
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Position - Methods definition")
    public void checkMethodsSanity() {
        final Class<Position> ownClass = Position.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(int.class, int.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Position's constructor is defined wrongly");
        }

        //Max public methods: 7 methods
        assertEquals(7, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getX").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getX").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setX", int.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setX", int.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getY").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getY").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("setY", int.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setY", int.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("equals", Object.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("hashCode").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("hashCode").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("toString").getModifiers()));
            assertEquals(String.class, ownClass.getDeclaredMethod("toString").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }
}
