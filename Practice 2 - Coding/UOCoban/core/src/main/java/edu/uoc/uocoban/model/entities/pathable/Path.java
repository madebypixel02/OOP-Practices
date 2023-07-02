package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents a path in the map.
 */
public class Path extends MapItem {

    /**
     * Constructor with arguments. The value for sprite is Sprite.PATH.
     *
     * @param position Position where the path is placed.
     */
    public Path(Position position) {
        super(position, Sprite.PATH);
    }

    /**
     * Determines whether the path is a pathable item.
     *
     * @return true
     */
    @Override
    public boolean isPathable() {
        return true;
    }
}
