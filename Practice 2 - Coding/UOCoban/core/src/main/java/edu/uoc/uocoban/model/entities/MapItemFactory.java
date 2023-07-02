package edu.uoc.uocoban.model.entities;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.entities.movable.BigBox;
import edu.uoc.uocoban.model.entities.movable.Player;
import edu.uoc.uocoban.model.entities.movable.SmallBox;
import edu.uoc.uocoban.model.entities.pathable.BigBoxDestination;
import edu.uoc.uocoban.model.entities.pathable.Path;
import edu.uoc.uocoban.model.entities.pathable.SmallBoxDestination;
import edu.uoc.uocoban.model.utils.Position;

/**
 * MapItem Simple Factory class.
 * @author aperez-b
 * @version 1.0
 */
public abstract class MapItemFactory {

    /**
     * Returns a new {@link MapItem} object.
     *
     * @param x             Column of the coordinate/position in which the item is in the board.
     * @param y             Row of the coordinate/position in which the item is in the board.
     * @param itemMapSymbol String value of the {@link MapItem} enumeration that corresponds to the item of the map.
     * @return {@link MapItem} object.
     * @throws IllegalStateException When a wrong symbol is used as an argument.
     */
    public static MapItem getItemMapInstance(int x, int y, char itemMapSymbol, Level level) throws IllegalStateException {
        Position position = new Position(x, y);

        return switch (itemMapSymbol) {
            case '.' -> new Path(position);
            case '#' -> new Wall(position);
            case 's' -> new SmallBox(position, level);
            case 'S' -> new SmallBoxDestination(position);
            case 'b' -> new BigBox(position, level);
            case 'B' -> new BigBoxDestination(position);
            case '@' -> new Player(position, level);
            default -> throw new IllegalStateException("Unexpected value: " + Character.toLowerCase(itemMapSymbol));
        };

    }
}
