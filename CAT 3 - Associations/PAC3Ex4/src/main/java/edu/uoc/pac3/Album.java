package edu.uoc.pac3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Album {

    final private static Logger logger = LogManager.getLogger(Album.class);
    private String title;
    private String artist;
    private Track[] tracks;

    public Album(String title, String artist, Track[] tracks) {
        logger.info("Creating new album");
        setTitle(title);
        setArtist(artist);
        setTracks(tracks);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        logger.debug("Title: " + title);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        logger.debug("Artist: " + artist);
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
        logger.debug("#tracks: " + tracks.length);
    }
}
