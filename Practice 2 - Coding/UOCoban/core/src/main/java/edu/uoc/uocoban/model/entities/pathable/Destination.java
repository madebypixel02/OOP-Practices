package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.entities.movable.Box;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import edu.uoc.uocoban.model.entities.MapItem;

/**
 * Represents a destination for boxes in the map.
 */
public abstract class Destination extends MapItem implements Mutable {

    /** The box delivered to the implicit destination (if any) */
    private Box box;
    /**
     * Constructor with arguments.
     * @param position Initial value of the attribute position
     * @param sprite   Initial value of the attribute sprite
     */
    protected Destination(Position position, Sprite sprite) {
        super(position, sprite);
    }
    /**
     * Getter for the box attribute.
     * @return The value of the box attribute
     */
    public Box getBox() {
        return box;
    }
    /**
     * Setter for the box attribute. When an entity is assigned to the destination, the destination must be mutated to its corresponding Sprite.
     * @param box The box value to be assigned
     */
    public void setBox(Box box) {
        this.box = box;
    }
    /**
     * States whether the destination is empty or not.
     * @return True if no entity is assigned; false otherwise
     */
    public boolean isEmpty() {
        return box == null;
    }
    /**
     * States whether the destination is pathable (i.e., if it is empty or not).
     * @return True if no entity is in the destination; false otherwise
     */
    @Override
    public boolean isPathable() {
        return isEmpty();
    }
}
