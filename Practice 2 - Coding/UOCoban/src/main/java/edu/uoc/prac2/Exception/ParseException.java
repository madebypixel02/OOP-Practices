package java.edu.uoc.prac2.Exception;

/**
 * Manages exceptions that happen during the parsing of a level
 */
public class ParseException extends Exception {
    /**
     * Error message that indicates that the width or height is out of the minimum/maximum bounds
     */
    public final static String INV_LVL_SIZE = "[ERROR] There is a problem with the height/width of the level";
    /**
     * Error message that indicates that the maximum number of movements is negative or zero
     */
    public final static String INV_MAX_MOVES = "[ERROR] The maximum number of movements should be greater than zero";
    /**
     * Error message that indicates that the map has no crates/boxes
     */
    public final static String ERR_NO_BOXES = "[ERROR] There are no crates in the level";
    /**
     * Error message that indicates that one or more boxes has no destination area
     */
    public final static String ERR_INV_BOX_DEST = "[ERROR] There are not enough destinations for the crates in the level";
    /**
     * Error message that indicates that the level either has no players or more than one player
     */
    public final static String ERR_INV_PLAYER_COUNT = "[ERROR] There must be one player for each level";
    /**
     * Error message that indicates that some other issue has happened when parsing the level file
     */
    public final static String ERR_PARSE = "[ERROR] There was some problem parsing the level file";
    /**
     * Default constructor
     */
    public ParseException() {
        super();
    }
    /**
     * Message constructor
     * @param msg Message to show in the exception
     */
    public ParseException(String msg) {
        super(msg);
    }
}
