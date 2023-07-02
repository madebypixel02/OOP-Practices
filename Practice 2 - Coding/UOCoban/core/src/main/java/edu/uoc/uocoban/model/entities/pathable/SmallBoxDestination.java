package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents a destination for small crates in the map
 * Extends Destination and implements Mutable
 * @see Destination
 * @see Mutable
 */
public class SmallBoxDestination extends Destination implements Mutable {

    /**
     * Constructor with arguments
     * @param position Initial value of the attribute position
     */
    public SmallBoxDestination(Position position) {
        super(position, Sprite.SMALL_BOX_DESTINATION);
    }

    /**
     * Mutates the destination sprite to Sprite.SMALL_CRATE_ON_DESTINATION when the crate is filled
     * @see Mutable#mutate()
     */
    @Override
    public void mutate() {
        //code to mutate the destination sprite to Sprite.SMALL_CRATE_ON_DESTINATION
    }
}
