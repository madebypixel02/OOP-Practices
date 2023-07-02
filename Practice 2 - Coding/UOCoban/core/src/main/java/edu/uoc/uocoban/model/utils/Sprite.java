package edu.uoc.uocoban.model.utils;

/**
 * Enum Class Sprite
 * Sprite that stores the configuration file symbol and the image's path.
 */
public enum Sprite {
    WALL('X', "images/wall.png"), // Sprite for a wall
    PATH(' ', "images/path.png"), // Sprite for a path
    PLAYER('@', "images/player.png"), // Sprite for a player
    BIG_BOX('O', "images/big_box.png"), // Sprite for a big box to be placed
    BIG_BOX_DESTINATION('.', "images/big_box_destination.png"), // Sprite for destination of a big box (empty)
    BIG_BOX_ON_DESTINATION('*', "images/big_box_on_destination.png"), // Sprite for a big box placed in its destination
    SMALL_BOX('o', "images/small_box.png"), // Sprite for a small box to be placed
    SMALL_BOX_DESTINATION(',', "images/small_box_destination.png"), // Sprite for destination of a small box (empty)
    SMALL_BOX_ON_DESTINATION(';', "images/small_box_on_destination.png"); // Sprite for a small box placed in its destination
    /**
     * Value in the configuration file.
     */
    private final char symbol;
    /**
     * Value of the image path.
     */
    private final String imageSrc;
    /**
     * Constructor with arguments.
     * @param symbol the value of the configuration file symbol.
     * @param imageSrc the value of the image path.
     */
    private Sprite(char symbol, String imageSrc) {
        this.symbol = symbol;
        this.imageSrc = imageSrc;
    }
    /**
     * Getter of the attribute {@code symbol}.
     * @return the value of {@code symbol}.
     */
    public char getSymbol() {
        return symbol;
    }
    /**
     * Getter of the attribute {@code imageSrc}.
     * @return the value of {@code imageSrc}.
     */
    public String getImageSrc() {
        return imageSrc;
    }
}
