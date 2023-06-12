package java.edu.uoc.prac2.Exception;

/**
 * Exceptions related to the manipulation of destinations
 */
public class DestinationException extends Exception {
    /**
     * Error message that indicates that a box was placed on a destination of a different size
     */
    public final static String BOX_DEST_SIZE_MISMATCH = "[ERROR] Box and destination size mismatch";
    /**
     * Default constructor
     */
    public DestinationException() {
        super();
    }
    /**
     * Message constructor
     * @param msg Message to show in the exception
     */
    public DestinationException(String msg) {
        super(msg);
    }
}
