package edu.uoc.uocoban.model.utils;

import java.util.Objects;

/**
 * Stores a 2D position in a game map
 */
public class Position {
    /** Value of the attribute X axis */
    private int x;
    /** Value of the attribute Y axis */
    private int y;
    /**
     * Constructor with arguments.
     * @param x Initial value of the attribute x
     * @param y Initial value of the attribute y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Getter of the attribute x
     * @return The current value of the attribute x
     */
    public int getX() {
        return x;
    }
    /**
     * Setter of the attribute x
     * @param x New value for the attribute x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Getter of the attribute y
     * @return The current value of the attribute y
     */
    public int getY() {
        return y;
    }
    /**
     * Setter of the attribute y
     * @param y New value for the attribute y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Checks if two positions are equal.
     * @param other object to compare against.
     * @return true if the type of other is Position and x == this.x and also y == this.y, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Position position = (Position) other;
        if (x != position.x) return false;
        return y == position.y;
    }
    /**
     * Returns the hash of a Position object.
     * @return The result of applying Objects.hash on x and y.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * Returns a comma-separated string representation of a Position.
     * Format: "x,y"
     * Example: "3,4"
     * @return A string representation of the Position object
     */
    @Override
    public String toString() {
        return x + "," + y;
    }
}
