package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents the player of the game.
 * @see MovableEntity
 * @see MapItem
 */
public class Player extends MovableEntity {
    /**
     * Constructor with arguments.
     * @param position Initial value of the attribute {@code Position}.
     * @param level    Initial value of the attribute {@code Level}.
     */
    public Player(Position position, edu.uoc.uocoban.model.Level level) {
        super(position, Sprite.PLAYER, level);
    }

    /**
     * Tries to move the player to the adjacent position determined by {@code direction}.
     * @param direction The {@code direction} where the player attempts to move.
     * @return {@code true} if the player could move; {@code false} otherwise.
     * @see MovableEntity#move(Direction)
     */
    @Override
    public boolean move(Direction direction) {
        //TODO
        return true;
    }
    /**
     * States whether the player is pathable.
     * @return {@code false}.
     * @see MapItem#isPathable()
     */
    @Override
    public boolean isPathable() {
        return false;
    }
}
