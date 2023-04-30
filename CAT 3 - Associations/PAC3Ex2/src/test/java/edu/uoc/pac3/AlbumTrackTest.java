package edu.uoc.pac3;

import edu.uoc.pac3.Album;
import edu.uoc.pac3.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTrackTest {

    Album album;
    Track track1;
    Track track2;
    Track track3;

    @BeforeEach
    void initializeAlbumTrackTest() {
        try {
            track1 = new Track("Smells Like Teen Spirit", 256, "Kurt Cobain");
            track2 = new Track("Come as You Are", 194, "Chad Channing");
            track3 = new Track("Something in the World", 301, "Dave Grohl");
            album = new Album("Nevermind", "Nirvana", "ROCK&ROLL");

            album.addTrack(track1);
        } catch (Exception e) {
            fail("initializeIntegrationTest failed");
            e.printStackTrace();
        }
    }

    @Test
    void testAddTrack() {
        try {
            assertEquals(30, album.getTracks().length);
            assertEquals(track1, album.getTrack(0));
            for (int i = 1; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

            Exception ex = assertThrows(Exception.class, () -> album.addTrack(track1));
            assertEquals("[ERROR] The track already exists in this album", ex.getMessage());

            album.addTrack(track2);
            assertEquals(track1, album.getTrack(0));
            assertEquals(track2, album.getTrack(1));
            for (int i = 2; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }
            album.addTrack(track3);
            assertEquals(track1, album.getTrack(0));
            assertEquals(track2, album.getTrack(1));
            assertEquals(track3, album.getTrack(2));
            for (int i = 3; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

            ex = assertThrows(Exception.class, () -> album.addTrack(track2));
            assertEquals("[ERROR] The track already exists in this album", ex.getMessage());

            ex = assertThrows(Exception.class, () -> album.addTrack(track3));
            assertEquals("[ERROR] The track already exists in this album", ex.getMessage());

            for (int i = 3; i < album.getTracks().length; ++i) {
                album.addTrack(new Track("Empty song", 1, "Random Artist"));
            }

            ex = assertThrows(Exception.class, () -> album.addTrack(new Track("Empty song", 1, "Random Artist")));
            assertEquals("[ERROR] Wrong index", ex.getMessage());

        } catch (Exception e) {
            fail("testAddTrack failed");
            e.printStackTrace();
        }
    }

    @Test
    void testIsInTheAlbum() {
        try {
            assertTrue(album.isInTheAlbum(track1));
            assertFalse(album.isInTheAlbum(track2));
            assertFalse(album.isInTheAlbum(track3));

            album.addTrack(track2);
            album.addTrack(track3);

            assertTrue(album.isInTheAlbum(track2));
            assertTrue(album.isInTheAlbum(track3));

            Track track4 = new Track("La Barbacoa", 194,"Georgie Dan");
            assertFalse(album.isInTheAlbum(track4));

        } catch (Exception e) {
            fail("testIsInTheAlbum failed");
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveTrack() {
        try {

            album.removeTrack(track1);
            assertEquals(30, album.getTracks().length);
            for (int i = 0; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

            album.removeTrack(track1);
            album.addTrack(track1);
            assertEquals(track1, album.getTrack(0));

            album.addTrack(track2);
            album.addTrack(track3);
            album.removeTrack(track2);
            assertEquals(track1, album.getTrack(0));
            assertEquals(track3, album.getTrack(2));
            assertNull(album.getTrack(1));

            album.removeTrack(track1);
            album.removeTrack(track3);
            for (int i = 0; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

            Track[] tempTrackList = new Track[30];
            for (int i = 0; i < album.getTracks().length; ++i) {
                tempTrackList[i] = new Track("Empty song", 1, "Random Artist");
                album.addTrack(tempTrackList[i]);
            }
            album.removeTrack(tempTrackList[3]);
            album.removeTrack(tempTrackList[7]);
            album.removeTrack(tempTrackList[8]);
            album.removeTrack(tempTrackList[21]);
            album.removeTrack(tempTrackList[29]);
            album.removeTrack(tempTrackList[29]);
            for (int i = 0; i < album.getTracks().length; ++i) {
                if (i == 3 || i == 7 || i == 8 || i == 21 || i == 29) {
                    assertNull(album.getTrack(i));
                } else {
                    assertNotNull(album.getTrack(i));
                }
            }

        } catch (Exception e) {
            fail("testRemoveTrack failed");
            e.printStackTrace();
        }
    }

    @Test
    void testEmptyAlbum() {
        try {
            album.emptyAlbum();
            for (int i = 0; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

            for (int i = 0; i < album.getTracks().length; ++i) {
                album.addTrack(new Track("Empty song", 1, "Random Artist"));
            }

            for (int i = 0; i < album.getTracks().length; ++i) {
                assertNotNull(album.getTrack(i));
            }

            album.emptyAlbum();
            for (int i = 0; i < album.getTracks().length; ++i) {
                assertNull(album.getTrack(i));
            }

        } catch (Exception e) {
            fail("testEmptyAlbum failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSwapTracks() {
        try {
            album.addTrack(track2);
            album.swapTracks(track1, track2);
            assertEquals(track1, album.getTrack(1));
            assertEquals(track2, album.getTrack(0));

            album.swapTracks(track1, track2);
            assertEquals(track1, album.getTrack(0));
            assertEquals(track2, album.getTrack(1));

            album.swapTracks(track2, track1);
            assertEquals(track1, album.getTrack(1));
            assertEquals(track2, album.getTrack(0));

            Exception ex = assertThrows(Exception.class, () -> album.swapTracks(track2, track3));
            assertEquals("[ERROR] Some of the tracks does not exist in this album", ex.getMessage());

            album.addTrack(track3);
            assertEquals(track3, album.getTrack(2));
            album.swapTracks(track2, track3);
            assertEquals(track1, album.getTrack(1));
            assertEquals(track2, album.getTrack(2));
            assertEquals(track3, album.getTrack(0));

            album.swapTracks(track1, track2);
            album.swapTracks(track1, track3);
            album.swapTracks(track3, track1);
            album.swapTracks(track1, track1);
            album.swapTracks(track3, track2);
            assertEquals(track1, album.getTrack(2));
            assertEquals(track2, album.getTrack(0));
            assertEquals(track3, album.getTrack(1));

            album.removeTrack(track3);
            ex = assertThrows(Exception.class, () -> album.swapTracks(track2, track3));
            assertEquals("[ERROR] Some of the tracks does not exist in this album", ex.getMessage());

            for (int i = 2; i < album.getTracks().length; ++i) {
                album.addTrack(new Track("Empty song", 1, "Random Artist"));
            }

        } catch (Exception e) {
            fail("testSwapTracks failed");
            e.printStackTrace();
        }
    }

    @Test
    void testGetTotalDuration() {
        try {
            assertEquals("00:04:16", album.getTotalDuration());

            album.addTrack(track2);
            album.addTrack(track3);
            assertEquals("00:12:31", album.getTotalDuration());

            album.emptyAlbum();

            assertEquals("00:00:00", album.getTotalDuration());
            for (int i = 0; i < album.getTracks().length; ++i) {
                album.addTrack(new Track("Empty song", 257, "Random Artist"));
            }
            assertEquals("02:08:30", album.getTotalDuration());

            album.removeTrack(album.getTrack(2));
            album.removeTrack(album.getTrack(19));
            album.removeTrack(album.getTrack(29));

            assertEquals("01:55:39", album.getTotalDuration());

        } catch (Exception e) {
            fail("testGetTotalDuration failed");
            e.printStackTrace();
        }
    }

}
