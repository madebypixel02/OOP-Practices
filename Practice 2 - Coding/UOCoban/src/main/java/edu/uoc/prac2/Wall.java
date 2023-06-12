package java.edu.uoc.prac2;

/**
 * Wall element
 */
public class Wall extends MapElement {
    /**
     * Default constructor
     * @param x horizontal position of the element
     * @param y vertical position of the element
     * @param sprite sprite of the element
     */
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        setPathable(false);
    }
    /**
     * Abstract method for moving elements
     * @param dir direction to move to
     * @return whether the move was made successfully
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
