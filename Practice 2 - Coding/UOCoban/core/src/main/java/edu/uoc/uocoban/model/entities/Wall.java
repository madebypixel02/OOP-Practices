package edu.uoc.uocoban.model.entities;

import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents a wall in the map.
 * Extends the {@link MapItem} class.
 */
public class Wall extends MapItem {

    /**
     * Constructor with arguments.
     * The value for sprite is {@link Sprite#WALL}.
     * @param position Position where the wall is placed.
     */
    public Wall(Position position) {
        super(position, Sprite.WALL);
    }
    /**
     * Determines whether the wall is a pathable item.
     * Overrides the {@link MapItem#isPathable()} method.
     * @return false, as walls are not pathable.
     */
    @Override
    public boolean isPathable() {
        return false;
    }
}
