package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class MutableTest {

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Mutable - Class definition")
    void checkClassSanity() {
        final Class<Mutable> ownClass = Mutable.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertTrue(Modifier.isInterface(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.pathable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Mutable - Fields definition")
    public void checkFieldsSanity() {
        final Class<Mutable> ownClass = Mutable.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Mutable - Methods definition")
    public void checkMethodsSanity() {
        final Class<Mutable> ownClass = Mutable.class;

        //check constructors
        assertEquals(0, ownClass.getDeclaredConstructors().length);

        //Max public methods
        assertEquals(1, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());
        //Min private methods: 0
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPrivate(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("mutate").getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("mutate").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
