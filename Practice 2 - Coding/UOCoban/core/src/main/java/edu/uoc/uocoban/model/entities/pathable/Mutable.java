package edu.uoc.uocoban.model.entities.pathable;

/**
 * Interface for a mutable map item (i.e., a map item that might change its sprite representation).
 * Mutation is given under certain external conditions of the game.
 *
 * @author Your Name
 * @version 1.0
 */
public interface Mutable {

    /**
     * A specific mutation implemented by the classes implementing the Mutable interface
     * (i.e., a sprite change for a given condition)
     */
    void mutate();
}
