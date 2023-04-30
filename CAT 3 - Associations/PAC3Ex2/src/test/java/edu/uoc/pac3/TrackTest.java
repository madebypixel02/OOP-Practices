package edu.uoc.pac3;

import edu.uoc.pac3.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest {

    Track track;

    @BeforeEach
    void initializeTrack() {
        try {
            track = new Track("Smells Like Teen Spirit",258,"Kurt Cobain");
        } catch (Exception e) {
            fail("initializeTrack failed");
            e.printStackTrace();
        }
    }

    @Test
    void testTrack() {
        try {
            assertEquals("Smells Like Teen Spirit", track.getName());
            assertEquals(258, track.getDuration());
            assertEquals("Kurt Cobain", track.getComposer());
        } catch (Exception e) {
            fail("testTrack failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetName() {
        try {
            track.setName("In Bloom");
            assertEquals("In Bloom", track.getName());
        } catch (Exception e) {
            fail("testSetName failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetDuration() {
        try {
            track.setDuration(290);
            assertEquals(290, track.getDuration());

            Exception ex = assertThrows(Exception.class, () -> track.setDuration(0));
            assertEquals("[ERROR] Duration must be greater than 0", ex.getMessage());

            ex = assertThrows(Exception.class, () -> track.setDuration(-235));
            assertEquals("[ERROR] Duration must be greater than 0", ex.getMessage());
        } catch (Exception e) {
            fail("testSetDuration failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetComposer() {
        try {
            track.setComposer("Krist Novoselic");
            assertEquals("Krist Novoselic", track.getComposer());
        } catch (Exception e) {
            fail("testSetComposer failed");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(4, Track.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(Track.class.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(Track.class.getDeclaredField("duration").getModifiers()));
            assertTrue(Modifier.isPrivate(Track.class.getDeclaredField("composer").getModifiers()));

            assertTrue(Modifier.isPublic(Track.class.getDeclaredField("ERR_MIN_DURATION").getModifiers()));
            assertTrue(Modifier.isStatic(Track.class.getDeclaredField("ERR_MIN_DURATION").getModifiers()));
            assertTrue(Modifier.isFinal(Track.class.getDeclaredField("ERR_MIN_DURATION").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, Track.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Track.class.getDeclaredConstructor(String.class, int.class, String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of the constructor");
            e.printStackTrace();
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("getName").getModifiers()));
            assertEquals(String.class, Track.class.getDeclaredMethod("getName").getReturnType());
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("setName", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("getDuration").getModifiers()));
            assertEquals(int.class, Track.class.getDeclaredMethod("getDuration").getReturnType());
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("setDuration", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the duration attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("getComposer").getModifiers()));
            assertEquals(String.class, Track.class.getDeclaredMethod("getComposer").getReturnType());
            assertTrue(Modifier.isPublic(Track.class.getDeclaredMethod("setComposer", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the composer attribute");
            e.printStackTrace();
        }

    }

}
