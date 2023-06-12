package java.edu.uoc.prac2;

import java.io.File;
import java.nio.file.FileSystemException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Starts a new UOCoban Game
 */
public class Game {
    /**
     * Path of the folder with the level files
     */
    private File path;
    /**
     * Set of levels in the game
     */
    private LinkedList<Level> levels = new LinkedList<Level>();
    /**
     * Number of levels in the game
     */
    private int levelCount;
    /**
     * Current level of the game
     */
    private Iterator<Level> currentLevel;
    /**
     * Total score of the game
     */
    private int totalScore = 0;
    /**
     * Remaining HP
     */
    private int HP = 3;
    /**
     * Constructor for a game
     * @param path source directory where the levels are stored
     */
    public Game(File path) throws Exception {
        File[] files;

        if (path != null && path.exists() && path.isDirectory() && path.canRead() && path.canWrite()) {
            files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".txt")) {
                        if (file.canRead() && file.canWrite())
                            levels.add(new Level(file));
                        else
                            throw new FileSystemException("[ERROR] Could not open all files");
                    }
                }
            }
            else
                throw new FileSystemException("[ERROR] No levels were found in the given folder");
        }
        else
            throw new FileSystemException("[ERROR] Could not open source folder");
        currentLevel = levels.iterator();
        levelCount = levels.size();
        reload();
        check();
    }
    /**
     * Reloads a given level
     */
    public void reload() {
        load((Level)currentLevel);
    }
    /**
     * Resets the game to its initial state
     */
    public void reset() {
        currentLevel = levels.iterator();
        reload();
    }
    /**
     * Checks the current state of the game
     */
    private void check() {
        //TODO
    }
    /**
     * Loads a given game level
     */
    private boolean load(Level level) {
        //TODO
        return (false);
    }
    /**
     * Retrieves the current level
     */
    public Level getCurrentLevel() {
        return (Level)currentLevel;
    }
    /**
     * Advances to the next level
     * @return whether the next level was loaded
     */
    public boolean advanceLevel() {
        if (currentLevel.hasNext()) {
            currentLevel.next();
            return (load((Level)currentLevel));
        }
        return (false);
    }
    /**
     * Get the number of remaining HP
     * @return hp
     */
    public int getHP() {
        return HP;
    }
    /**
     * Decrease HP by one point
     */
    public void decreaseHP() {
        HP--;
    }
    /**
     * Checks if all levels are cleared
     * @return true if every level has been finished
     */
    public boolean isOver() {
        Iterator<Level> level;
        level = levels.iterator();
        while (level.hasNext()) {
            if (!((Level)level).isComplete())
                return false;
            level.next();
        }
        return (true);
    }
    /**
     * Checks if the game is lost
     * @return true if HP is equal to zero
     */
    public boolean isDead() {
        return (HP == 0);
    }
    /**
     * Checks if current level is cleared
     * @return true if current level has been finished
     */
    public boolean currentLevelIsComplete() {
        return (((Level)currentLevel).isComplete());
    }
    /**
     * Checks if current level has reached a deadlock
     * @return true if current level cannot be completed
     */
    public boolean currentLevelIsDeadlock() {
        return (((Level)currentLevel).isDeadlock());
    }
    /**
     * Checks if current level has run out of moves
     * @return true if current level cannot be completed
     */
    public boolean currentLevelIsLost() {
        return (((Level)currentLevel).isLost());
    }

    /**
     * Move element of the current level
     * @param direction direction to move
     * @return whether the move was successful
     */
    public boolean move(MapElement element, Direction direction) {
        return(element.move(direction));
    }
}
