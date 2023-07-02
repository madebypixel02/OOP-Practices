package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import edu.uoc.uocoban.model.utils.Direction;

/**
 * Represents a box in the map
 */
public abstract class Box extends MovableEntity {
    /**
     * States whether the box has been delivered to a valid destination point
     */
    private boolean delivered = false;
    /**
     * Constructor with arguments (by default, `delivered` attribute is set as `false`).
     * @param position Initial value of the attribute `position`
     * @param sprite Initial value of the attribute `sprite`
     * @param level Initial value of the attribute `level`
     */
    protected Box(Position position, Sprite sprite, Level level) {
        super(position, sprite, level);
        this.delivered = false;
    }
    /**
     * Getter for the `delivered` attribute
     * @return `delivered` attribute value
     */
    public boolean isDelivered() {
        return delivered;
    }
    /**
     * Setter for the `delivered` attribute
     * @param delivered `delivered` attribute value
     */
    private void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    /**
     * States whether the box element is pathable or not
     * @return `false`
     */
    @Override
    public boolean isPathable() {
        return false;
    }
    /**
     * Tries to move the box to the adjacent position determined by `direction`.
     * @param direction The `direction` where the box attempts to move
     * @return `true` if the box could successfully move; `false` otherwise
     */
    @Override
    public boolean move(Direction direction) {
        //TODO
        return false;
    }
}
