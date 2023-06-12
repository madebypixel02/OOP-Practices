package java.edu.uoc.prac2;

/**
 * Box element
 */
public class Box extends MapElement {
    /**
     * Size of a box (big or small)
     */
    private Size size;
    /**
     * Whether a box is in the destination cell
     */
    private boolean isDelivered = false;
    /**
     * Default constructor
     * @param x horizontal position of the element
     * @param y vertical position of the element
     * @param sprite sprite of the element
     */
    public Box(int x, int y, Sprite sprite, Size size) {
        super(x, y, sprite);
        setSize(size);
        setPathable(false);
    }
    /**
     * Whether a box is in its destination cell
     * @return if a box is delivered
     */
    public boolean isDelivered() {
        return isDelivered;
    }
    /**
     * Updates isDelivered
     * @param delivered new value for isDelivered
     */
    private void setDelivered(boolean delivered) {
        isDelivered = delivered;
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
     * Move method for a map element
     * @param dir direction to move to
     * @return whether the move was successful
     */
    public boolean move(Direction dir) {
        return false;
        //TODO
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
