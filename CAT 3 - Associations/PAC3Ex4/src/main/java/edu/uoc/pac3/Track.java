package edu.uoc.pac3;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Track {

    final private static Logger logger = LogManager.getLogger(Track.class);
    private String name;
    private String composer;
    private int duration;

    public Track(String name, String composer, int duration) {
        setName(name);;
        setComposer(composer);
        setDuration(duration);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration < 1)
            logger.error("Duration should not be a negative value!");
        this.duration = duration;
    }
}
