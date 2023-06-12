package java.edu.uoc.prac2;

import java.edu.uoc.prac2.Exception.DestinationException;

/**
 * Destination element
 */
public class Destination extends MapElement {
    /**
     * Associated box
     */
    private Box associatedBox = null;
    /**
     * Size of a destination (big or small)
     */
    private Size size;
    /**
     * Default constructor
     * @param x horizontal position of the element
     * @param y vertical position of the element
     * @param sprite sprite of the element
     */
    public Destination(int x, int y, Sprite sprite, Size size) {
        super(x, y, sprite);
        setSize(size);
        setPathable(isEmpty());
    }
    /**
     * Retrieves the size of a box
     * @return the size of the box (big/small)
     */
    public Size getSize() {
        return size;
    }
    /**
     * Sets the size of a box
     * @param size new size of a box (big/small)
     */
    private void setSize(Size size) {
        this.size = size;
    }
    /**
     * Checks if a box is on top of this destination
     * @return if the associated box is null
     */
    public boolean isEmpty() {
        return associatedBox == null;
    }
    /**
     * Updates the associated box
     * @param associatedBox nex box on this destination
     * @return true if the box was placed on the destination
     */
    private boolean setAssociatedBox(Box associatedBox) throws DestinationException {
        if (getSize() != associatedBox.getSize())
            throw new DestinationException(DestinationException.BOX_DEST_SIZE_MISMATCH);
        if (this.associatedBox != null)
            return false;
        this.associatedBox = associatedBox;
        return true;
    }
    /**
     * Move method for a map element
     * @param dir direction to move to
     * @return whether the move was successful
     */
    public boolean move(Direction dir) {
        return false;
    }
    /**
     * Retrieves the attribute isPathable under some conditions
     * @return the attribute isPathable
     */
    public boolean isPathable() {
        //TODO
        return false;
    }
}
