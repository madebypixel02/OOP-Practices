package java.edu.uoc.prac2;

import java.edu.uoc.prac2.Exception.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Identifies a Sprite by a character/symbol and the path to an image
 */
public class Sprite {
    /**
     * String of allowed symbols
     */
    private final static String ALLOWED_CHARS = ".#@bB8sS$";
    /**
     * Relative path of the image for a sprite
     */
    private String img;
    /**
     * Character representing the sprite
     */
    private Character symbol;

    /**
     * Opens a sprite image
     * @param path relative path of the image
     * @return the sprite's image
     * @throws Exception when the file cannot be opened or is not found
     */
    private String openFile(String path) throws Exception {
        String out;
        try (FileInputStream in = new FileInputStream(path)) {
            out = in.toString();
            in.close();
            return out;
        }
        catch (FileNotFoundException e) {
            throw new ParseException(ParseException.ERR_PARSE);
        }
    }
    /**
     * Default Constructor for a sprite given its char symbol
     * @param symbol represents the type of sprite
     * @throws Exception when the specified symbol is not allowed
     */
    public Sprite(Character symbol) throws Exception {
        String path;

        if (ALLOWED_CHARS.indexOf(symbol) == -1)
            throw new ParseException(ParseException.ERR_PARSE);
        this.symbol = symbol;
        if (symbol == '.')
            path =  "./resources/sprites/dot.jpg";
        else if (symbol == '#')
            path =  "./resources/sprites/wall.jpg";
        else if (symbol == '@')
            path =  "./resources/sprites/player.jpg";
        else if (symbol == 'b')
            path =  "./resources/sprites/big_box.jpg";
        else if (symbol == 'B')
            path =  "./resources/sprites/big_box_hole.jpg";
        else if (symbol == '8')
            path =  "./resources/sprites/big_box_in_place.jpg";
        else if (symbol == 's')
            path =  "./resources/sprites/small_box.jpg";
        else if (symbol == 'S')
            path =  "./resources/sprites/small_box_hole.jpg";
        else
            path =  "./resources/sprites/small_box_in_place.jpg";
        img = path;
    }
}
