package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents an entity in the game that can move.
 * @author aperez-b
 * @version 1.0
 */
public abstract class MovableEntity extends MapItem {
    /**
     * Reference to the current level object.
     */
    private Level level;
    /**
     * Constructor with arguments.
     * @param position Initial value of the attribute position.
     * @param sprite Initial value of the attribute sprite.
     * @param level Initial value of the attribute level.
     */
    protected MovableEntity(Position position, Sprite sprite, Level level) {
        super(position, sprite);
        this.level = level;
    }
    /**
     * Getter for the level the entity belongs to.
     * @return The level attribute value.
     */
    public Level getLevel() {
        return level;
    }
    /**
     * Setter for the level attribute.
     * @param level The level value to be assigned.
     */
    private void setLevel(Level level) {
        this.level = level;
    }
    /**
     * Tries to move the entity to the adjacent position determined by direction
     * @param direction The direction where the entity attempts to move.
     * @return true if the entity could move; false otherwise.
     */
    public abstract boolean move(Direction direction);
}
