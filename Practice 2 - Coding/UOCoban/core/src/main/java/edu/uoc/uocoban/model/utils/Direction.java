package edu.uoc.uocoban.model.utils;

/**
 @version 1.0
 */
public enum Direction {

    /**
     * UP direction. x = 0, y = -1 and keyCode = 19.
     */
    UP(0, -1, 19),
    /**
     * RIGHT direction. x = 1, y = 0 and keyCode = 22.
     */
    RIGHT(1, 0, 22),
    /**
     * DOWN direction. x = 0, y = 1 and keyCode = 20.
     */
    DOWN(0, 1, 20),
    /**
     * LEFT direction. x = -1, y = 0 and keyCode = 21.
     */
    LEFT(-1, 0, 21);
    /**
     * Offset in X axis.
     */
    private final int x;
    /**
     * Offset in Y axis.
     */
    private final int y;
    /**
     * Integer value that is given by libgdx to a key pressed by the user.
     */
    private final int keyCode;
    /**
     * Direction constructor.
     * @param x Value of the attribute x.
     * @param y Value of the attribute y.
     * @param keyCode Value of the attribute keyCode.
     */
    private Direction(int x, int y, int keyCode) {
        this.x = x;
        this.y = y;
        this.keyCode = keyCode;
    }
    /**
     * Getter of the attibute x.
     * @return The value of the attribute x.
     */
    public int getX() {
        return x;
    }
    /**
     * Getter of the attibute y.
     * @return The value of the attribute y.
     */
    public int getY() {
        return y;
    }
    /**
     * Getter of the attibute keyCode.
     * @return The value of the attribute keyCode.
     */
    public int getKeyCode() {
        return keyCode;
    }
    /**
     * Given the value of keyCode, it returns the corresponding Direction value.
     * @param keyCode Integer value of a key.
     * @return The Direction value linked to keyCode. If any, then returns null.
     */
    public static Direction getDirectionByKeyCode(int keyCode) {
        for (Direction direction : Direction.values()) {
            if (direction.getKeyCode() == keyCode) {
                return direction;
            }
        }
        return null;
    }
}
