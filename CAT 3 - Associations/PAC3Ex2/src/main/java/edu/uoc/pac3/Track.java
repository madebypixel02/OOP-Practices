package edu.uoc.pac3;

/**
 * The Properties from an album's tracks
 * @author aperez-b
 * @version 1.0
 */
public class Track {
    /**
     * The name of a track
     */
    private String name;
    /**
     * The duration of a track, in seconds
     */
    private int duration;
    /**
     * The composer of a track
     */
    private String composer;
    /**
     * Error representing the minimum duration of a track
     */
    final public static String ERR_MIN_DURATION = "[ERROR] Duration must be greater than 0";
    /**
     * Constructor for a track
     * @param name Name of a track
     * @param duration Duration of a track
     * @param composer composer of a track
     * @throws Exception when duration is not a positive non-zero number
     */
    public Track(String name, int duration, String composer) throws Exception {
        this.setName(name);
        this.setDuration(duration);
        this.setComposer(composer);
    }
    /**
     * Getter for a Track name
     * @return The name of a Track
     */
    public String getName() {
        return (this.name);
    }
    /**
     * Setter of a Track name
     * @param name New name for a Track
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for a Track duration
     * @return The duration of a Track
     */
    public int getDuration() {
        return (this.duration);
    }
    /**
     * Setter of a Track duration
     * @param duration New duration for a Track
     */
    public void setDuration(int duration) throws Exception {
        if (duration < 1)
            throw new Exception(ERR_MIN_DURATION);
        this.duration = duration;
    }
    /**
     * Getter for a Track composer
     * @return The composer of a Track
     */
    public String getComposer() {
        return this.composer;
    }
    /**
     * Setter of a Track composer
     * @param composer New composer for a Track
     */
    public void setComposer(String composer) {
        this.composer = composer;
    }
}
