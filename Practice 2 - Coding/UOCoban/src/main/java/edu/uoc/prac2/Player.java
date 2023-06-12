package java.edu.uoc.prac2;

/**
 * Wall element
 */
public class Player extends MapElement {
    /**
     * Default constructor
     * @param x horizontal position of the element
     * @param y vertical position of the element
     * @param sprite sprite of the element
     */
    public Player(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        setPathable(false);
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
