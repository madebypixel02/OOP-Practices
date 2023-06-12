package java.edu.uoc.prac2;

/**
 * Defines the attributes of an element/object in a Level
 */
public abstract class MapElement {
    /**
     * Position of the element
     */
    private Position position;
    /**
     * Sprite information associated with the element
     */
    private Sprite sprite;
    /**
     * Whether another element can go through this element
     */
    private boolean isPathable = false;
    /**
     * Default MapElement constructor
     * @param x position in the x-axis
     * @param y position in the y-axis
     * @param sprite sprite object
     */
    public MapElement(int x, int y, Sprite sprite) {
        this.position = new Position(x,y);
        this.sprite = sprite;
    }
    /**
     * Retrieves the position of the element in the x-axis
     * @return horizontal position
     */
    public int getPositionX() {
        return (int)position.x;
    }
    /**
     * Retrieves the position of the element in the y-axis
     * @return vertical position
     */
    public int getPositionY() {
        return (int)position.y;
    }
    /**
     * Sets position of the element in the x-axis
     * @param x new horizontal position
     */
    public void setPositionX(int x) {
        this.position.x = x;
    }
    /**
     * Sets position of the element in the x-axis
     * @param y new vertical position
     */
    public void setPositionY(int y) {
        this.position.y = y;
    }
    /**
     * Retrieves the element's Sprite information
     * @return sprite of the element
     */
    public Sprite getSprite() {
        return sprite;
    }
    /**
     * Updates the Sprite of an Element
     * @param sprite new sprite of the element
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    /**
     * Sets the attribute isPathable
     * @param pathable new value for isPathable
     */
    public void setPathable(boolean pathable) {
        isPathable = pathable;
    }
    /**
     * Retrieves the attribute isPathable under some conditions
     * @return the attribute isPathable
     */
    public abstract boolean isPathable();
    /**
     * Abstract method for moving elements
     * @param dir direction to move to
     * @return whether the move was made successfully
     */
    public abstract boolean move(Direction dir);
}
