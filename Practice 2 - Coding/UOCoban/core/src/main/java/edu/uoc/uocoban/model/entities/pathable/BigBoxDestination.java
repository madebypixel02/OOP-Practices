package edu.uoc.uocoban.model.entities.pathable;

import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;

/**
 * Represents a destination for big crates in the map
 */
public class BigBoxDestination extends Destination implements Mutable {
    /**
     * Constructor with arguments
     * @param position Initial value of the attribute position
     */
    public BigBoxDestination(Position position) {
        super(position, Sprite.BIG_BOX_DESTINATION);
    }
    /**
     * Mutates the destination sprite to Sprite.BIG_CRATE_ON_DESTINATION when the crate is filled
     */
    @Override
    public void mutate() {
        if (getBox() != null) {
            setSprite(Sprite.BIG_BOX_ON_DESTINATION);
        }
    }
}
