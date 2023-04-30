package edu.uoc.pac3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MusicGenreTest {

    @Test
    public void testGetDescription() {
        assertEquals("Alternative Rock", MusicGenre.ALTERNATIVE.getDescription());
        assertEquals("Blues", MusicGenre.BLUES.getDescription());
        assertEquals("Classical Music", MusicGenre.CLASSICAL.getDescription());
        assertEquals("Country Music", MusicGenre.COUNTRY.getDescription());
        assertEquals("Disco", MusicGenre.DISCO.getDescription());
        assertEquals("Jazz", MusicGenre.JAZZ.getDescription());
        assertEquals("Metal", MusicGenre.METAL.getDescription());
        assertEquals("Pop Music", MusicGenre.POP.getDescription());
        assertEquals("Rock & Roll", MusicGenre.ROCK_N_ROLL.getDescription());
        assertEquals("R & B", MusicGenre.R_N_B.getDescription());
        assertEquals("Other", MusicGenre.OTHER.getDescription());
    }

    @Test
    public void testGetCode() {
        assertEquals('A', MusicGenre.ALTERNATIVE.getCode());
        assertEquals('B', MusicGenre.BLUES.getCode());
        assertEquals('C', MusicGenre.CLASSICAL.getCode());
        assertEquals('Y', MusicGenre.COUNTRY.getCode());
        assertEquals('D', MusicGenre.DISCO.getCode());
        assertEquals('J', MusicGenre.JAZZ.getCode());
        assertEquals('M', MusicGenre.METAL.getCode());
        assertEquals('P', MusicGenre.POP.getCode());
        assertEquals('R', MusicGenre.ROCK_N_ROLL.getCode());
        assertEquals('N', MusicGenre.R_N_B.getCode());
        assertEquals('O', MusicGenre.OTHER.getCode());
    }

    @Test
    public void testGetTempo() {
        assertEquals(125, MusicGenre.ALTERNATIVE.getTempo());
        assertEquals(80, MusicGenre.BLUES.getTempo());
        assertEquals(60, MusicGenre.CLASSICAL.getTempo());
        assertEquals(120, MusicGenre.COUNTRY.getTempo());
        assertEquals(130, MusicGenre.DISCO.getTempo());
        assertEquals(100, MusicGenre.JAZZ.getTempo());
        assertEquals(140, MusicGenre.METAL.getTempo());
        assertEquals(110, MusicGenre.POP.getTempo());
        assertEquals(150, MusicGenre.ROCK_N_ROLL.getTempo());
        assertEquals(90, MusicGenre.R_N_B.getTempo());
        assertEquals(105, MusicGenre.OTHER.getTempo());
    }

    @Test
    public void testGetGenreWithNextHigherTempo() {
        assertEquals(MusicGenre.BLUES, MusicGenre.CLASSICAL.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.R_N_B, MusicGenre.BLUES.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.JAZZ, MusicGenre.R_N_B.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.OTHER, MusicGenre.JAZZ.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.POP, MusicGenre.OTHER.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.COUNTRY, MusicGenre.POP.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.ALTERNATIVE, MusicGenre.COUNTRY.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.DISCO, MusicGenre.ALTERNATIVE.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.METAL, MusicGenre.DISCO.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.ROCK_N_ROLL, MusicGenre.METAL.getGenreWithNextHigherTempo());
        assertEquals(MusicGenre.ROCK_N_ROLL, MusicGenre.ROCK_N_ROLL.getGenreWithNextHigherTempo());
    }

    @Test
    public void getMusicGenre() {
        assertEquals(MusicGenre.ALTERNATIVE, MusicGenre.getMusicGenre('A'));
        assertEquals(MusicGenre.BLUES, MusicGenre.getMusicGenre('B'));
        assertEquals(MusicGenre.CLASSICAL, MusicGenre.getMusicGenre('C'));
        assertEquals(MusicGenre.COUNTRY, MusicGenre.getMusicGenre('Y'));
        assertEquals(MusicGenre.DISCO, MusicGenre.getMusicGenre('D'));
        assertEquals(MusicGenre.JAZZ, MusicGenre.getMusicGenre('J'));
        assertEquals(MusicGenre.METAL, MusicGenre.getMusicGenre('M'));
        assertEquals(MusicGenre.POP, MusicGenre.getMusicGenre('P'));
        assertEquals(MusicGenre.ROCK_N_ROLL, MusicGenre.getMusicGenre('R'));
        assertEquals(MusicGenre.R_N_B, MusicGenre.getMusicGenre('N'));
        assertEquals(MusicGenre.OTHER, MusicGenre.getMusicGenre('O'));
        assertNull(MusicGenre.getMusicGenre('X'));
    }

    @Test
    public void testGetGenresSortedByCode() {
       MusicGenre[] musicGenres = MusicGenre.getGenresSortedByCode();
       assertEquals(MusicGenre.BLUES, musicGenres[1]);
        assertEquals(MusicGenre.CLASSICAL, musicGenres[2]);
        assertEquals(MusicGenre.DISCO, musicGenres[3]);
        assertEquals(MusicGenre.JAZZ, musicGenres[4]);
        assertEquals(MusicGenre.METAL, musicGenres[5]);
        assertEquals(MusicGenre.R_N_B, musicGenres[6]);
        assertEquals(MusicGenre.OTHER, musicGenres[7]);
        assertEquals(MusicGenre.POP, musicGenres[8]);
        assertEquals(MusicGenre.ROCK_N_ROLL, musicGenres[9]);
        assertEquals(MusicGenre.COUNTRY, musicGenres[10]);
    }

}
