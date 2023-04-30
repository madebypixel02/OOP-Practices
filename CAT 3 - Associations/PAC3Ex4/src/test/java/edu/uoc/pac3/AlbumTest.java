package edu.uoc.pac3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AlbumTest {

    private static ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        File logFile = new File("pac3.log");
        logFile.delete();
    }

    @Test
    public void testTrackSetter() {
        Track track = new Track("Smells Like Teen Spirit", "Kurt Cobain", 0);
        String timestamp = getTimestamp();
        assertEquals(timestamp + " ERROR edu.uoc.pac3.Track - Duration should not be a negative value!", outContent.toString().trim());

        cleanStreams();

        track = new Track("Smells Like Teen Spirit", "Kurt Cobain", -214);
        timestamp = getTimestamp();
        assertEquals(timestamp + " ERROR edu.uoc.pac3.Track - Duration should not be a negative value!", outContent.toString().trim());

        cleanStreams();

        track = new Track("Smells Like Teen Spirit", "Kurt Cobain", 214);
        timestamp = getTimestamp();
        assertEquals("", outContent.toString().trim());
    }

    @Test
    public void testAlbumConstructor() {
        Album album = new Album("Nevermind", "Nirvana", new Track[]{});

        String timestamp = getTimestamp();
        assertEquals(timestamp + " INFO  edu.uoc.pac3.Album - Creating new album" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Title: Nevermind" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Artist: Nirvana"  + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - #tracks: 0", outContent.toString().trim());

        cleanStreams();
        Track t = new Track("Yellow Submarine", "John Lennon", 240);
        new Album("Abbey Road", "The Beatles", new Track[]{t});
        timestamp = getTimestamp();
        assertEquals(timestamp + " INFO  edu.uoc.pac3.Album - Creating new album" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Title: Abbey Road" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Artist: The Beatles"  + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - #tracks: 1", outContent.toString().trim());

        cleanStreams();
        t = new Track("La Barbacoa", "Georgie Dan", 240);
        new Album("Grandes Exitos", "Georgie Dan", new Track[]{t,t,t,t,t,t,t,t,t,t});
        timestamp = getTimestamp();
        assertEquals(timestamp + " INFO  edu.uoc.pac3.Album - Creating new album" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Title: Grandes Exitos" + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - Artist: Georgie Dan"  + System.lineSeparator() +
                timestamp + " DEBUG edu.uoc.pac3.Album - #tracks: 10", outContent.toString().trim());

        cleanStreams();

    }

    private String getTimestamp() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("HH:mm");
        String timestamp = localDate.format(simpleDateFormat);
        return timestamp;
    }

    private void cleanStreams() {
        outContent.reset();
    }

}
