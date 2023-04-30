/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   AlbumCover.java                          =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/30 11:15:31 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/30 11:15:33 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac3;

/**
 * The Properties from an album's cover
 * @author aperez-b
 * @version 1.0
 */
public class AlbumCover {
    /**
     * Name of the Album cover
     */
    private String name;
    /**
     * Name of the Album cover's artist
     */
    private String artist;
    /**
     * Cover's width
     */
    private int width;
    /**
     * Cover's height
     */
    private int height;
    /**
     * Maximum value for the cover (height * width)
     */
    final static private int MAX_RESOLUTION = 1166400;
    /**
     * Error for an invalid name
     */
    final static public String ERR_UNDEFINED_NAME = "[ERROR] The name cannot be an empty value";
    /**
     * Error for an invalid artist
     */
    final static public String ERR_UNKNOWN_ARTIST = "[ERROR] The artist cannot be an empty value";
    /**
     * Error for an invalid minimum resolution
     */
    final static public String ERR_MIN_RESOLUTION = "[ERROR] Width and height must be positive numbers";
    /**
     * Error for an invalid maximum resolution
     */
    final static public String ERR_MAX_RESOLUTION = "[ERROR] The image resolution (i.e., width * height) cannot be greater than MAX_RESOLUTION";
    /**
     * Constructor for an AlbumCover
     * @param name Name of the AlbumCover
     * @param artist Artist of the AlbumCover
     * @param height Height of the AlbumCover
     * @param width Width of the AlbumCover
     * @throws Exception When name or artist are null or empty, and when height or width are invalid
     */
    public AlbumCover(String name, String artist, int width, int height) throws Exception {
        if (width < 1 || height < 1)
            throw new Exception(AlbumCover.ERR_MIN_RESOLUTION);
        if (height * width > AlbumCover.MAX_RESOLUTION)
            throw new Exception(AlbumCover.ERR_MAX_RESOLUTION);
        this.setName(name);
        this.setArtist(artist);
        this.setWidth(width);
        this.setHeight(height);
    }
    /**
     * Getter for an AbumCover's name
     * @return The name of an AlbumCover
     */
    public String getName() {
        return (this.name);
    }
    /**
     * Setter for an AlbumCover's name
     * @param name New name for an AlbumCover
     * @throws Exception When name is null or empty
     */
    public void setName(String name) throws Exception {
        if (name == null || name.length() == 0)
            throw new Exception(AlbumCover.ERR_UNDEFINED_NAME);
        this.name = name;
    }
    /**
     * Getter for an AbumCover's artist
     * @return The artist of an AlbumCover
     */
    public String getArtist() {
        return (this.artist);
    }
    /**
     * Setter for an AlbumCover's artist
     * @param artist New artist for an AlbumCover
     * @throws Exception When artist is null or empty
     */
    public void setArtist(String artist) throws Exception {
        if (artist == null || artist.length() == 0)
            throw new Exception(AlbumCover.ERR_UNKNOWN_ARTIST);
        this.artist = artist;
    }
    /**
     * Getter for an AbumCover's width
     * @return The width of an AlbumCover
     */
    public int getWidth() {
        return (this.width);
    }
    /**
     * Setter for an AlbumCover's width
     * @param width New width for an AlbumCover
     * @throws Exception When width is negative or zero
     */
    public void setWidth(int width) throws Exception {
        if (width < 1)
            throw new Exception(AlbumCover.ERR_MIN_RESOLUTION);
        if (this.height * width > AlbumCover.MAX_RESOLUTION)
            throw new Exception(AlbumCover.ERR_MAX_RESOLUTION);
        this.width = width;
    }
    /**
     * Getter for an AbumCover's height
     * @return The height of an AlbumCover
     */
    public int getHeight() {
        return (this.height);
    }
    /**
     * Setter for an AlbumCover's height
     * @param height New height of an AlbumCover
     * @throws Exception When height is negative or zero
     */
    public void setHeight(int height) throws Exception {
        if (height < 1)
            throw new Exception(AlbumCover.ERR_MIN_RESOLUTION);
        if (height * this.width > AlbumCover.MAX_RESOLUTION)
            throw new Exception(AlbumCover.ERR_MAX_RESOLUTION);
        this.height = height;
    }
    /**
     * Gets the Aspect ratio of an AlbumCover from its height and width
     * @return a string containing the aspect ratio, separated with a colon
     */
    public String getAspectRatio() {
        int gcd;
        StringBuilder aspectRatio;

        aspectRatio = new StringBuilder();
        gcd = GCD.gcd(this.width, this.height);
        return (aspectRatio.append((int)(this.width / gcd)).append(":").append((int)(this.height / gcd)).toString());
    }
}
