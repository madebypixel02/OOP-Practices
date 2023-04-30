package edu.uoc.pac3;

import edu.uoc.pac3.Album;
import edu.uoc.pac3.Album;
import edu.uoc.pac3.AlbumCover;
import edu.uoc.pac3.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {

    Album album;

    @BeforeEach
    void initializeAlbum() {
        try {
            album = new Album("Nevermind","Nirvana","ROCK&ROLL");
        } catch (Exception e) {
            fail("initializeAlbum failed");
            e.printStackTrace();
        }
    }

    @Test
    void testAlbum1() {
        try {
            assertEquals("Nevermind", album.getTitle());
            assertEquals("Nirvana", album.getArtist());
            assertEquals("ROCK&ROLL", album.getGenre());
            assertNotNull(album.getId());
            assertNull(album.getReleaseDate());
            assertFalse( album.isAvailableOnline());
            assertEquals(14.99, album.getPrice());
            assertNull(album.getAlbumCover());
        } catch (Exception e) {
            fail("testAlbum1 failed");
            e.printStackTrace();
        }

    }

    @Test
    void testAlbum2() {
        try {
            LocalDate date = LocalDate.of(1986, Month.JANUARY, 8);
            album = new Album("Nevermind","Nirvana","ROCK&ROLL", date, true, 10.99);
            assertEquals("Nevermind", album.getTitle());
            assertEquals("Nirvana", album.getArtist());
            assertEquals("ROCK&ROLL", album.getGenre());
            assertNotNull(album.getId());
            assertEquals(date.toEpochDay(), album.getReleaseDate().toEpochDay());
            assertTrue(album.isAvailableOnline());
            assertEquals(10.99, album.getPrice());
            assertNull(album.getAlbumCover());
        } catch (Exception e) {
            fail("testAlbum2 failed");
            e.printStackTrace();
        }

    }

    @Test
    void testAlbum3() {
        try {
            LocalDate date = LocalDate.of(2001, Month.MARCH, 25);
            album = new Album("Nevermind","Nirvana","CLASSICAL", date, true, 10.99,
                    "Abbey Road", "Iain Macmillan", 560, 480);
            assertEquals("Nevermind", album.getTitle());
            assertEquals("Nirvana", album.getArtist());
            assertEquals("CLASSICAL", album.getGenre());
            assertNotNull(album.getId());
            assertEquals(date.toEpochDay(), album.getReleaseDate().toEpochDay());
            assertTrue(album.isAvailableOnline());
            assertEquals(10.99, album.getPrice());
            assertNotNull(album.getAlbumCover());
            assertEquals("Abbey Road", album.getAlbumCover().getName());
            assertEquals("Iain Macmillan", album.getAlbumCover().getArtist());
            assertEquals(560, album.getAlbumCover().getWidth());
            assertEquals(480, album.getAlbumCover().getHeight());
        } catch (Exception e) {
            fail("testAlbum3 failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetTitle() {
        try {
            album.setTitle("Abbey Road");
            assertEquals("Abbey Road", album.getTitle());
        } catch (Exception e) {
            fail("testSetTitle failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetArtist() {
        try {
            album.setArtist("The Beatles");
            assertEquals("The-Beatles", album.getArtist());

            album.setArtist("the beatles");
            assertEquals("The-Beatles", album.getArtist());

            album.setArtist("   the    beatles     ");
            assertEquals("The-Beatles", album.getArtist());

            album.setArtist("tHE bEATLES");
            assertEquals("The-Beatles", album.getArtist());

            album.setArtist("tHE bEATLES tribute");
            assertEquals("The-Beatles-Tribute", album.getArtist());

            album.setArtist("       the bEAtLES    TRIBUTE   ");
            assertEquals("The-Beatles-Tribute", album.getArtist());
        } catch (Exception e) {
            fail("testSetArtist failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetGenre() {
        try {
            album.setGenre("CLASSICAL");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre("JAZZ");
            assertEquals("JAZZ", album.getGenre());

            album.setGenre("ROCK&ROLL");
            assertEquals("ROCK&ROLL", album.getGenre());

            album.setGenre("POP");
            assertEquals("POP", album.getGenre());

            album.setGenre("DISCO");
            assertEquals("DISCO", album.getGenre());

            album.setGenre("classical");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre("cLASSICAL");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre("Classical");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre("cLaSSiCAl");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre("classicaL");
            assertEquals("CLASSICAL", album.getGenre());

            album.setGenre(" rock&Roll   ");
            assertEquals("ROCK&ROLL", album.getGenre());

            Exception ex = assertThrows(Exception.class, () -> album.setGenre("CLASSICAL ROCK"));
            assertEquals("[ERROR] The genre is not a valid value", ex.getMessage());

            ex = assertThrows(Exception.class, () -> album.setGenre("ALTERNATIVE ROCK"));
            assertEquals("[ERROR] The genre is not a valid value", ex.getMessage());

        } catch (Exception e) {
            fail("testSetGenre failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetReleaseDate() {
        try {
            LocalDate date = LocalDate.of(2001, Month.MARCH, 25);
            album.setReleaseDate(date);
            assertEquals(date.toEpochDay(), album.getReleaseDate().toEpochDay());

            date = LocalDate.of(1970, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals(date.toEpochDay(), album.getReleaseDate().toEpochDay());

            date = LocalDate.of(2030, Month.DECEMBER, 31);
            album.setReleaseDate(date);
            assertEquals(date.toEpochDay(), album.getReleaseDate().toEpochDay());

        } catch (Exception e) {
            fail("testSetReleaseDate failed");
            e.printStackTrace();
        }

    }

    @Test
    void testGetFormattedReleaseDate() {
        try {
            LocalDate date = LocalDate.of(2001, Month.MARCH, 25);
            album.setReleaseDate(date);
            assertEquals("Released on March 25, 2001 (22 years ago)", album.getFormattedReleaseDate());

            date = LocalDate.of(1970, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals("Released on January 1, 1970 (53 years ago)", album.getFormattedReleaseDate());

            date = LocalDate.of(2022, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals("Released on January 1, 2022 (last year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2022, Month.DECEMBER, 31);
            album.setReleaseDate(date);
            assertEquals("Released on December 31, 2022 (last year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2023, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals("Released on January 1, 2023 (this year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2023, Month.DECEMBER, 31);
            album.setReleaseDate(date);
            assertEquals("Released on December 31, 2023 (this year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2024, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals("Released on January 1, 2024 (next year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2024, Month.DECEMBER, 31);
            album.setReleaseDate(date);
            assertEquals("Released on December 31, 2024 (next year)", album.getFormattedReleaseDate());

            date = LocalDate.of(2025, Month.JANUARY, 1);
            album.setReleaseDate(date);
            assertEquals("Released on January 1, 2025 (in 2 years)", album.getFormattedReleaseDate());

            date = LocalDate.of(2028, Month.MAY, 5);
            album.setReleaseDate(date);
            assertEquals("Released on May 5, 2028 (in 5 years)", album.getFormattedReleaseDate());

        } catch (Exception e) {
            fail("testGetFormattedReleaseDate failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetAvailableOnline() {
        try {
            album.setAvailableOnline(true);
            assertTrue(album.isAvailableOnline());

            album.setAvailableOnline(false);
            assertFalse(album.isAvailableOnline());

            album.setPrice(24.99);
            album.setAvailableOnline(true);
            assertTrue(album.isAvailableOnline());
            assertEquals(23.992, album.getPrice());

        } catch (Exception e) {
            fail("testSetAvailableOnline failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetPrice() {
        try {
            album.setPrice(4.99);
            assertEquals(4.99, album.getPrice());

            album.setPrice(29.99);
            assertEquals(29.99, album.getPrice());

            Exception ex = assertThrows(Exception.class, () -> album.setPrice(4.98));
            assertEquals("[ERROR] The album price must be greater than MIN_PRICE", ex.getMessage());

            ex = assertThrows(Exception.class, () -> album.setPrice(30.0));
            assertEquals("[ERROR] The album price must be less than MAX_PRICE (or 80% of MAX_PRICE if the album is available online)", ex.getMessage());

            album.setAvailableOnline(true);

            album.setPrice(4.99);
            assertEquals(4.99, album.getPrice());

            album.setPrice(23.99);
            assertEquals(23.99, album.getPrice());

            ex = assertThrows(Exception.class, () -> album.setPrice(4.98));
            assertEquals("[ERROR] The album price must be greater than MIN_PRICE", ex.getMessage());

            ex = assertThrows(Exception.class, () -> album.setPrice(24));
            assertEquals("[ERROR] The album price must be less than MAX_PRICE (or 80% of MAX_PRICE if the album is available online)", ex.getMessage());


        } catch (Exception e) {
            fail("testSetPrice failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetAlbumCover() {
        try {
            album.setAlbumCover("Abbey Road", "Iain Macmillan", 560, 480);
            assertEquals("Abbey Road", album.getAlbumCover().getName());
            assertEquals("Iain Macmillan", album.getAlbumCover().getArtist());
            assertEquals(560, album.getAlbumCover().getWidth());
            assertEquals(480, album.getAlbumCover().getHeight());
            assertEquals("7:6", album.getAlbumCover().getAspectRatio());

            album.setAlbumCover("","",500,500);
            assertNull(album.getAlbumCover());
        } catch (Exception e) {
            fail("testSetAlbumCover failed");
            e.printStackTrace();
        }

    }

    @Test
    void testGetTracks() {
        try {
            assertEquals(30, album.getTracks().length);
            for (Track t : album.getTracks()) {
                assertNull(t);
            }
        } catch (Exception e) {
            fail("testGetTracks failed");
            e.printStackTrace();
        }

    }

    @Test
    void testGetTrack() {
        try{
            assertNull(album.getTrack(0));
            assertNull(album.getTrack(29));
        }catch(Exception e){
            fail("getTrack failed");
        }

        Exception ex = assertThrows(Exception.class, () -> album.getTrack(-1));
        assertEquals(Album.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> album.getTrack(-53));
        assertEquals(Album.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> album.getTrack(30));
        assertEquals(Album.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> album.getTrack(86));
        assertEquals(Album.ERR_WRONG_INDEX, ex.getMessage());
    }

    @Test
    void isInTheAlbum() {
        try{
            assertFalse(album.isInTheAlbum(null));
        }catch(Exception e){
            fail("isInTheAlbum failed");
        }

    }

    @Test
    void getTotalDuration() {
        try{
            assertEquals("00:00:00", album.getTotalDuration());
        }catch(Exception e){
            fail("getTotalDuration failed");
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(18, Album.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("title").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("artist").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("genre").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("releaseDate").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("availableOnline").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("price").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("albumCover").getModifiers()));
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("tracks").getModifiers()));

            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("MIN_PRICE").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("MIN_PRICE").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("MIN_PRICE").getModifiers()));

            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("MAX_PRICE").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("MAX_PRICE").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("MAX_PRICE").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_GENRE").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_GENRE").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_GENRE").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_MIN_PRICE").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_MIN_PRICE").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_MIN_PRICE").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_MAX_PRICE").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_MAX_PRICE").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_MAX_PRICE").getModifiers()));

            //Exercise 2

            assertTrue(Modifier.isPrivate(Album.class.getDeclaredField("MAX_TRACKS").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("MAX_TRACKS").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("MAX_TRACKS").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_TRACK_EXISTS").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_TRACK_EXISTS").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_TRACK_EXISTS").getModifiers()));

            assertTrue(Modifier.isPublic(Album.class.getDeclaredField("ERR_TRACK_NOT_EXISTS").getModifiers()));
            assertTrue(Modifier.isStatic(Album.class.getDeclaredField("ERR_TRACK_NOT_EXISTS").getModifiers()));
            assertTrue(Modifier.isFinal(Album.class.getDeclaredField("ERR_TRACK_NOT_EXISTS").getModifiers()));
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
        assertEquals(3, Album.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Album.class.getDeclaredConstructor(String.class, String.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(Album.class.getDeclaredConstructor(String.class, String.class, String.class,
                    LocalDate.class, boolean.class, double.class).getModifiers()));
            assertTrue(Modifier.isPublic(Album.class.getDeclaredConstructor(String.class, String.class, String.class,
                    LocalDate.class, boolean.class, double.class, String.class, String.class, int.class, int.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of the constructor");
            e.printStackTrace();
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getId").getModifiers()));
            assertEquals(UUID.class, Album.class.getDeclaredMethod("getId").getReturnType());
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredMethod("setId").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the id attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getTitle").getModifiers()));
            assertEquals(String.class, Album.class.getDeclaredMethod("getTitle").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setTitle", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the title attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getArtist").getModifiers()));
            assertEquals(String.class, Album.class.getDeclaredMethod("getArtist").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setArtist", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the artist attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getGenre").getModifiers()));
            assertEquals(String.class, Album.class.getDeclaredMethod("getGenre").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setGenre", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the genre attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getReleaseDate").getModifiers()));
            assertEquals(LocalDate.class, Album.class.getDeclaredMethod("getReleaseDate").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setReleaseDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the releaseDate attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("isAvailableOnline").getModifiers()));
            assertEquals(boolean.class, Album.class.getDeclaredMethod("isAvailableOnline").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setAvailableOnline", boolean.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the availableOnline attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getPrice").getModifiers()));
            assertEquals(double.class, Album.class.getDeclaredMethod("getPrice").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setPrice", double.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the price attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getAlbumCover").getModifiers()));
            assertEquals(AlbumCover.class, Album.class.getDeclaredMethod("getAlbumCover").getReturnType());
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("setAlbumCover", String.class, String.class, int.class, int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the albumCover attribute");
            e.printStackTrace();
        }

        // Exercise 2

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getTracks").getModifiers()));
            assertEquals(Track[].class, Album.class.getDeclaredMethod("getTracks").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the getTracks method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getTrack", int.class).getModifiers()));
            assertEquals(Track.class, Album.class.getDeclaredMethod("getTrack", int.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the getTrack method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("isInTheAlbum", Track.class).getModifiers()));
            assertEquals(boolean.class, Album.class.getDeclaredMethod("isInTheAlbum", Track.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the isInTheAlbum method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredMethod("findTrackIndex", Track.class).getModifiers()));
            assertEquals(int.class, Album.class.getDeclaredMethod("findTrackIndex", Track.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the findTrackIndex method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPrivate(Album.class.getDeclaredMethod("setTrack", int.class, Track.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the setTrack method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("addTrack", Track.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the addTrack method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("removeTrack", Track.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the removeTrack method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("emptyAlbum").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the emptyAlbum method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("swapTracks", Track.class, Track.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the swapTracks method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Album.class.getDeclaredMethod("getTotalDuration").getModifiers()));
            assertEquals(String.class, Album.class.getDeclaredMethod("getTotalDuration").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the getTotalDuration method");
            e.printStackTrace();
        }

    }

}
