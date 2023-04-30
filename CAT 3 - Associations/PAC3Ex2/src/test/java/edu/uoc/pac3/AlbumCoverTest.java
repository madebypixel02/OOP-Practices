package edu.uoc.pac3;

import edu.uoc.pac3.AlbumCover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumCoverTest {

    AlbumCover albumCover;

    @BeforeEach
    void initializeAlbumCover() {
        try {
            albumCover = new AlbumCover("Abbey Road", "Iain Macmillan", 560, 480);
        } catch (Exception e) {
            fail("initializeAlbumCover failed");
            e.printStackTrace();
        }
    }

    @Test
    void testAlbumCover() {
        AlbumCover newAlbumCover;
        try {
            newAlbumCover = new AlbumCover("Baby in a Swimming Pool", "Robert Fisher", 1000, 800);
            assertEquals("Baby in a Swimming Pool", newAlbumCover.getName());
            assertEquals("Robert Fisher", newAlbumCover.getArtist());
            assertEquals(1000, newAlbumCover.getWidth());
            assertEquals(800, newAlbumCover.getHeight());
        } catch (Exception e) {
            fail("testAlbumCover failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetName() {
        try {
            albumCover.setName("The Beatles - Abbey Road");
            assertEquals("The Beatles - Abbey Road", albumCover.getName());

            Exception ex = assertThrows(Exception.class, () -> albumCover.setName(""));
            assertEquals("[ERROR] The name cannot be an empty value", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setName(null));
            assertEquals("[ERROR] The name cannot be an empty value", ex.getMessage());

        } catch (Exception e) {
            fail("testSetName failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetAuthor() {
        try {
            albumCover.setArtist("Sir Iain Macmillan");
            assertEquals("Sir Iain Macmillan", albumCover.getArtist());

            Exception ex = assertThrows(Exception.class, () -> albumCover.setArtist(""));
            assertEquals("[ERROR] The artist cannot be an empty value", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setArtist(null));
            assertEquals("[ERROR] The artist cannot be an empty value", ex.getMessage());

        } catch (Exception e) {
            fail("testSetAuthor failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetWidth() {
        try {
            assertEquals(560, albumCover.getWidth());

            albumCover.setWidth(620);
            assertEquals(620, albumCover.getWidth());

            Exception ex = assertThrows(Exception.class, () -> albumCover.setWidth(2800));
            assertEquals("[ERROR] The image resolution (i.e., width * height) cannot be greater than MAX_RESOLUTION", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setWidth(0));
            assertEquals("[ERROR] Width and height must be positive numbers", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setWidth(-200));
            assertEquals("[ERROR] Width and height must be positive numbers", ex.getMessage());

        } catch (Exception e) {
            fail("testSetWidth failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetHeight() {
        try {
            assertEquals(480, albumCover.getHeight());

            albumCover.setHeight(1100);
            assertEquals(1100, albumCover.getHeight());

            Exception ex = assertThrows(Exception.class, () -> albumCover.setHeight(2800));
            assertEquals("[ERROR] The image resolution (i.e., width * height) cannot be greater than MAX_RESOLUTION", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setHeight(0));
            assertEquals("[ERROR] Width and height must be positive numbers", ex.getMessage());

            ex = assertThrows(Exception.class, () -> albumCover.setHeight(-450));
            assertEquals("[ERROR] Width and height must be positive numbers", ex.getMessage());

        } catch (Exception e) {
            fail("testSetHeight failed");
            e.printStackTrace();
        }
    }

    @Test
    void testGetAspectRatio() {
        try {
            assertEquals("7:6", albumCover.getAspectRatio());

            albumCover.setWidth(1000);
            albumCover.setHeight(1000);
            assertEquals("1:1", albumCover.getAspectRatio());

            albumCover.setWidth(1);
            albumCover.setHeight(1);
            assertEquals("1:1", albumCover.getAspectRatio());

            albumCover.setHeight(10);
            assertEquals("1:10", albumCover.getAspectRatio());

            albumCover.setWidth(100);
            assertEquals("10:1", albumCover.getAspectRatio());

            albumCover.setWidth(640);
            albumCover.setHeight(480);
            assertEquals("4:3", albumCover.getAspectRatio());

            albumCover.setWidth(800);
            albumCover.setHeight(600);
            assertEquals("4:3", albumCover.getAspectRatio());

            albumCover.setWidth(720);
            albumCover.setHeight(360);
            assertEquals("2:1", albumCover.getAspectRatio());

            albumCover.setWidth(640);
            albumCover.setHeight(360);
            assertEquals("16:9", albumCover.getAspectRatio());

            albumCover.setWidth(640);
            albumCover.setHeight(512);
            assertEquals("5:4", albumCover.getAspectRatio());

            albumCover.setWidth(720);
            albumCover.setHeight(348);
            assertEquals("60:29", albumCover.getAspectRatio());

        } catch (Exception e) {
            fail("testGetAspectRatio failed");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(9, AlbumCover.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(AlbumCover.class.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(AlbumCover.class.getDeclaredField("artist").getModifiers()));
            assertTrue(Modifier.isPrivate(AlbumCover.class.getDeclaredField("width").getModifiers()));
            assertTrue(Modifier.isPrivate(AlbumCover.class.getDeclaredField("height").getModifiers()));

            assertTrue(Modifier.isPrivate(AlbumCover.class.getDeclaredField("MAX_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isStatic(AlbumCover.class.getDeclaredField("MAX_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isFinal(AlbumCover.class.getDeclaredField("MAX_RESOLUTION").getModifiers()));

            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredField("ERR_UNDEFINED_NAME").getModifiers()));
            assertTrue(Modifier.isStatic(AlbumCover.class.getDeclaredField("ERR_UNDEFINED_NAME").getModifiers()));
            assertTrue(Modifier.isFinal(AlbumCover.class.getDeclaredField("ERR_UNDEFINED_NAME").getModifiers()));

            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredField("ERR_UNKNOWN_ARTIST").getModifiers()));
            assertTrue(Modifier.isStatic(AlbumCover.class.getDeclaredField("ERR_UNKNOWN_ARTIST").getModifiers()));
            assertTrue(Modifier.isFinal(AlbumCover.class.getDeclaredField("ERR_UNKNOWN_ARTIST").getModifiers()));

            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredField("ERR_MIN_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isStatic(AlbumCover.class.getDeclaredField("ERR_MIN_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isFinal(AlbumCover.class.getDeclaredField("ERR_MIN_RESOLUTION").getModifiers()));

            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredField("ERR_MAX_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isStatic(AlbumCover.class.getDeclaredField("ERR_MAX_RESOLUTION").getModifiers()));
            assertTrue(Modifier.isFinal(AlbumCover.class.getDeclaredField("ERR_MAX_RESOLUTION").getModifiers()));
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
        assertEquals(1, AlbumCover.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredConstructor(String.class, String.class, int.class, int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of the constructor");
            e.printStackTrace();
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("getName").getModifiers()));
            assertEquals(String.class, AlbumCover.class.getDeclaredMethod("getName").getReturnType());
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("setName", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("getArtist").getModifiers()));
            assertEquals(String.class, AlbumCover.class.getDeclaredMethod("getArtist").getReturnType());
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("setArtist", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the artist attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("getWidth").getModifiers()));
            assertEquals(int.class, AlbumCover.class.getDeclaredMethod("getWidth").getReturnType());
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("setWidth", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the width attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("getHeight").getModifiers()));
            assertEquals(int.class, AlbumCover.class.getDeclaredMethod("getHeight").getReturnType());
            assertTrue(Modifier.isPublic(AlbumCover.class.getDeclaredMethod("setHeight", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the height attribute");
            e.printStackTrace();
        }
    }

}
