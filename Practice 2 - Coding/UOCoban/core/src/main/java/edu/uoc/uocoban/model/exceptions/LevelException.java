package edu.uoc.uocoban.model.exceptions;

/**
 * Manages exceptions that happen during the parsing of a level
 */
public class LevelException extends Exception {
    /**
     * Error message that indicates that the width or height is out of the minimum/maximum bounds
     */
    public final static String SIZE_ERROR = "[ERROR] There is a problem with the height/width of the level";
    /**
     * Error message that indicates that the maximum number of movements is negative or zero
     */
    public final static String MAX_MOVEMENTS_ERROR = "[ERROR] The maximum number of movements should be greater than zero";
    /**
     * Error message that indicates that the map has no crates/boxes
     */
    public final static String BOX_ERROR = "[ERROR] There are no crates in the level";
    /**
     * Error message that indicates that one or more boxes has no destination area
     */
    public final static String BOX_DESTINATION_ERROR = "[ERROR] There are not enough destinations for the crates in the level";
    /**
     * Error message that indicates that the level either has no players or more than one player
     */
    public final static String PLAYER_ERROR = "[ERROR] There must be one player for each level";
    /**
     * Error message that indicates that some other issue has happened when parsing the level file
     */
    public final static String PARSING_LEVEL_FILE_ERROR = "[ERROR] There was some problem parsing the level file";
    /**
     * Message constructor
     * @param msg Message to show in the exception
     */
    public LevelException(String msg) {
        super(msg);
    }
}
