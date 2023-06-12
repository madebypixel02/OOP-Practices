package java.edu.uoc.prac2;

import java.edu.uoc.prac2.Exception.ParseException;
import java.io.File;
import java.util.List;

/**
 * Level class in a UOCoban game
 */
public class Level {
    /**
     * File name of the level
     */
    private File file;
    /**
     * Height of a level
     */
    private int height;
    /**
     * Width of a level
     */
    private int width;
    /**
     * Maximum moves in the level
     */
    private int maxMoves;
    /**
     * List containing every map element object in a level
     */
    private List<MapElement> map;
    /**
     * Player of a level
     */
    private Player player;
    /**
     * Boxes of a level
     */
    private List<List<Object>> boxes;
    /**
     * Returns element of the level based on its position
     * @param x position of the element in the x-axis
     * @param y position of the element in the y-axis
     * @return element of the leve or null if not found
     */
    public MapElement getElement(int x, int y) {
        if (y >= height || x >= width)
            return null;
        return map.get(y * width + x);
    }

    /**
     * Parses a level file
     * @param file file to open and read
     * @return whether any errors occurred while parsing
     */
    private boolean parse(File file) throws ParseException {
        //TODO
        return false;
    }
    /**
     * Constructor for a level
     * @param file file to open
     */
    public Level(File file) throws ParseException {
        if (parse(file))
            setFile(file);
        //TODO
    }
    /**
     * Adds an element to a level
     * @param x position of the element in the x-axis
     * @param y position of the element in the y-axis
     * @param element element to add
     * @return whether the element was added
     */
    public boolean addElement(int x, int y, MapElement element) {
        if (y >= height || x >= width)
            return false;
        map.add(y * width + x, element);
        return true;
    }
    /**
     * Removes an element on the level
     * @param x position of the element in the x-axis
     * @param y position of the element in the y-axis
     * @return whether the element was removed
     * @throws Exception when a sprite cannot be created
     */
    public boolean removeElement(int x, int y) throws Exception {
        //TODO
        return addElement(x, y, new Path(x, y, new Sprite('.')));
    }

    /**
     * Checks if te level is won
     * @return true if all boxes are delivered
     */
    public boolean isComplete() {
        for (List<Object> b : boxes) {
            if (!isDelivered((Box)(b.get(0))))
                return false;
        }
        return true;
    }
    /**
     * Checks if a box is delivered
     * @param b box to check
     * @return true if box is delivered
     */
    private boolean isDelivered(Box b) {
        return b.isDelivered();
    }
    /**
     * Checks if the game has reached a deadlock
     * @return whether the deadlock is found
     */
    public boolean isDeadlock() {
        //TODO
        return true;
    }
    /**
     * Checks if player is out of moves
     * @return true if number of moves == maxMoves
     */
    public boolean isLost() {
        return maxMoves == 0;
    }

    /**
     * Retrieves Player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Retrieves Boxes
     * @return List of boxes in a 2-dimension matrix representing <Box,Destination> pairs
     */
    public List<List<Object>> getBoxes() {
        return boxes;
    }
    /**
     * Retrieves the file of the level
     * @return file of the level
     */
    public File getFile() {
        return file;
    }
    /**
     * Updates file of a level
     * @param file new file of a level
     */
    private void setFile(File file) {
        this.file = file;
    }
}