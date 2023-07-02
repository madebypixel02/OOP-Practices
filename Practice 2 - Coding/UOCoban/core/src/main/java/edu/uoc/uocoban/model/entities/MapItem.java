package edu.uoc.uocoban.model.entities;

import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Item of a game map.
 */
public abstract class MapItem {
    /**
     * Position where the map item is placed.
     */
    private Position position;
    /**
     * Value of Sprite linked to the map item.
     */
    private Sprite sprite;
    /**
     * Constructor with arguments.
     * @param position Initial value of the attribute position.
     * @param sprite Initial value of the attribute sprite.
     */
    protected MapItem(Position position, Sprite sprite) {
        this.position = position;
        this.sprite = sprite;
    }
    /**
     * Gets the current position of the entity.
     * @return current position.
     */
    public Position getPosition() {
        return position;
    }
    /**
     * Sets the position of the entity. If position is null, then the position is not set.
     * @param position to set to the entity
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    /**
     * Getter of the attribute sprite.
     * @return the value of sprite.
     */
    public Sprite getSprite() {
        return sprite;
    }
    /**
     * Setter of the attribute sprite.
     * @param sprite the value of sprite to set.
     */
    protected void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    /**
     * Determines whether the map item is pathable or not by at least one type of entity.
     * @return true if the map item is pathable, false otherwise.
     */
    public abstract boolean isPathable();
}
