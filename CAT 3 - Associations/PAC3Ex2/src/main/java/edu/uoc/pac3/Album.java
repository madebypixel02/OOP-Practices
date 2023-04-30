/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   Album.java                               =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/30 11:03:57 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/30 11:04:10 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac3;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;

/**
 * This class represents an Album
 * @author aperez-b
 * @version 1.0
 */
public class Album {
    /**
     * Unique identifier for an Album, in UUID format
     */
    private UUID id = UUID.randomUUID();
    /**
     * Title of an Album
     */
    private String title;
    /**
     * The name of an Album artist
     */
    private String artist;
    /**
     * Genre of an Album
     */
    private String genre;
    /**
     * Release date for an Album
     */
    private LocalDate releaseDate = null;
    /**
     * Whether an Album is available online
     */
    private boolean availableOnline = false;
    /**
     * Cover of the Album
     */
    private AlbumCover albumCover = null;
    /**
     * List of tracks of an Album
     */
    final private Track []tracks = new Track[Album.MAX_TRACKS];
    /**
     * Price of an Album
     */
    private double price = 14.99;
    /**
     * Minimum price of an Album
     */
    final static private double MIN_PRICE = 4.99;
    /**
     * Maximum price of an Album
     */
    final static private double MAX_PRICE = 29.99;
    /**
     * Maximum number of tracks of an Album
     */
    final static private int MAX_TRACKS = 30;
    /**
     * Error for an invalid genre
     */
    final static public String ERR_GENRE = "[ERROR] The genre is not a valid value";
    /**
     * Error for an invalid minimum price
     */
    final static public String ERR_MIN_PRICE = "[ERROR] The album price must be greater than MIN_PRICE";
    /**
     * Error for an invalid maximum price
     */
    final static public String ERR_MAX_PRICE = "[ERROR] The album price must be less than MAX_PRICE (or 80% of MAX_PRICE if the album is available online)";
    /**
     * Error for an invalid index
     */
    final static public String ERR_WRONG_INDEX = "[ERROR] Wrong index";
    /**
     * Error for a track that already exists
     */
    final static public String ERR_TRACK_EXISTS = "[ERROR] The track already exists in this album";
    /**
     * Error for a track that does not exist
     */
    final static public String ERR_TRACK_NOT_EXISTS = "[ERROR] Some of the tracks does not exist in this album";
    /**
     * Constructor for an Album
     * @param title New title for an Album
     * @param artist New artist for an Album
     * @param genre New genre for an Album
     * @throws Exception When genre is invalid
     */
    public Album(String title, String artist, String genre) throws Exception {
        this.setTitle(title);
        this.setArtist(artist);
        this.setGenre(genre);
    }
    /**
     * Constructor for an Album
     * @param title New title for an Album
     * @param artist New artist for an Album
     * @param genre New genre for an Album
     * @param releaseDate New release date for an Album
     * @param availableOnline Whether an Album is available online
     * @param price New Price for an Album
     * @throws Exception When genre or price is invalid
     */
    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price) throws Exception {
        this.setTitle(title);
        this.setArtist(artist);
        this.setGenre(genre);
        this.setReleaseDate(releaseDate);
        this.setAvailableOnline(availableOnline);
        this.setPrice(price);
    }/**
     * Constructor for an Album
     * @param title New title for an Album
     * @param artist New artist for an Album
     * @param genre New genre for an Album
     * @param releaseDate New release date for an Album
     * @param availableOnline Whether an Album is available online
     * @param price New Price for an Album
     * @param coverName New name for an Album's Cover
     * @param coverArtist New artist for an Album's Cover
     * @param coverWidth New width for an Album's Cover
     * @param coverHeight New height for an Album's Cover
     * @throws Exception When genre or price is invalid
     */
    public Album(String title, String artist, String genre, LocalDate releaseDate, boolean availableOnline, double price, String coverName, String coverArtist, int coverWidth, int coverHeight) throws Exception {
        this.setTitle(title);
        this.setArtist(artist);
        this.setGenre(genre);
        this.setReleaseDate(releaseDate);
        this.setAvailableOnline(availableOnline);
        this.setPrice(price);
        this.setAlbumCover(coverName, coverArtist, coverWidth, coverHeight);
    }
    /**
     * Getter for an Album Id
     * @return The Id of an Album
     */
    public UUID getId() {
        return (this.id);
    }
    /**
     * Setter for an Album Id
     */
    private void setId() {
        this.id = UUID.randomUUID();
    }
    /**
     * Getter for an Album Title
     * @return The title of an Album
     */
    public String getTitle() {
        return (this.title);
    }
    /**
     * Setter for an Album Title
     * @param title New title for an Album
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Getter for an Album Artist
     * @return The artist of an Album
     */
    public String getArtist() {
        return this.artist;
    }
    /**
     * Setter for an Album Artist
     * @param artist New artist for an Album
     */
    public void setArtist(String artist) {
        String []names;
        StringBuilder fullName;

        fullName = new StringBuilder();
        this.artist = "";
        names = artist.split(" ");
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() != 0) {
                fullName.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1).toLowerCase());
                if (i + 1 < names.length)
                    fullName.append("-");
            }
        }
        this.artist = fullName.toString();
    }
    /**
     * Getter for an Album Genre
     * @return The genre of an Album
     */
    public String getGenre() {
        return this.genre;
    }
    /**
     * Setter for an Album Genre
     * @param genre New genre for an Album
     * @throws Exception when genre is invalid
     */
    public void setGenre(String genre) throws Exception {
        String genreFormatted;
        boolean isValid;
        String []aux = {"CLASSICAL", "JAZZ", "ROCK&ROLL", "POP", "DISCO"};

        isValid = false;
        genreFormatted = genre.trim().toUpperCase();
        for (String type : aux) {
            if (type.equals(genreFormatted)) {
                isValid = true;
                break;
            }
        }
        if (!isValid)
            throw new Exception(Album.ERR_GENRE);
        this.genre = genreFormatted;
    }
    /**
     * Getter for an Album ReleaseDate
     * @return The releaseDate of an Albun
     */
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }
    /**
     * Setter for an Album ReleaseDate
     * @param releaseDate New releaseDate for an Album
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * Gets a string with the date of the release date
     * @return String containing the date of a release date, with extra details at the end
     */
    public String getFormattedReleaseDate() {
        StringBuilder out;
        StringBuilder aux;
        int releaseYear, currentYear;

        releaseYear = this.releaseDate.getYear();
        currentYear = LocalDate.now().getYear();
        aux = new StringBuilder();
        if (releaseYear == currentYear)
            aux.append("this year");
        else if (releaseYear > currentYear) {
            if (releaseYear == currentYear + 1)
                aux.append("next year");
            else
                aux.append("in ").append(releaseYear - currentYear).append(" years");
        }
        else if (releaseYear + 1 == currentYear)
            aux.append("last year");
        else
            aux.append(currentYear - releaseYear).append(" years ago");
        out = new StringBuilder("Released on ");
        out.append(this.releaseDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))).append(" (").append(aux).append(")");
        return (out.toString());
    }
    /**
     * Whether an Album is available online
     * @return if an Album is available online
     */
    public boolean isAvailableOnline() {
        return availableOnline;
    }
    /**
     * Sets if an Album is available online
     * @param availableOnline whether an Album is available online
     */
    public void setAvailableOnline(boolean availableOnline) throws Exception {
        this.availableOnline = availableOnline;
        if (this.getPrice() > (0.8 * Album.MAX_PRICE))
            this.setPrice(0.8 * Album.MAX_PRICE);
    }
    /**
     * Getter for an Album Price
     * @return The price of an Album
     */
    public double getPrice() {
        return price;
    }
    /**
     * Setter for an Album Price
     * @param price New price for an Album
     */
    public void setPrice(double price) throws Exception {
        if (price < Album.MIN_PRICE)
            throw new Exception(Album.ERR_MIN_PRICE);
        else if ((price > Album.MAX_PRICE && !this.availableOnline) || (price > (0.8 * Album.MAX_PRICE) && this.availableOnline))
            throw new Exception(Album.ERR_MAX_PRICE);
        this.price = price;
    }
    /**
     * Getter for an Album Cover
     * @return The cover of an Album
     */
    public AlbumCover getAlbumCover() {
        return this.albumCover;
    }
    /**
     * Setter for an Album Cover
     * @param name New name for the AlbumCover
     * @param artist New artist for the AlbumCover
     * @param width New width for the AlbumCover
     * @param height New height for the AlbumCover
     */
    public void setAlbumCover(String name, String artist, int width, int height) throws Exception {
        try {
            this.albumCover = new AlbumCover(name, artist, width, height);
        }
        catch(Exception e) {
            this.albumCover = null;
        }
    }
    /**
     * Returns list of tracks on an Album
     * @return Tracks from a given Album
     */
    public Track[] getTracks() {
        return this.tracks;
    }
    /**
     * Gets the track in a given index from an album
     * @param index Index of the track to be retrieved
     * @return track at the specified index
     * @throws Exception when index is out of bounds of the track array
     */
    public Track getTrack(int index) throws Exception {
        if (index < 0 || index >= this.tracks.length)
            throw new Exception(ERR_WRONG_INDEX);
        return this.tracks[index];
    }
    /**
     * Checks if an album is on a track
     * @param track the track to find in the tracks of an album
     * @return whether or not the track is found on the track list
     */
    public boolean isInTheAlbum(Track track) {
        for (Track t : this.tracks)
            if (t != null && t == track)
                return (true);
        return (false);
    }

    /**
     * Checks if an album is on a track
     * @param track the track to find in the tracks of an album
     * @return the index where the track is located, or -1 if not found
     */
    private int findTrackIndex(Track track) {
        for (int i = 0; i < this.tracks.length; i++) {
            if (track != null && this.tracks[i] == track)
                return (i);
        }
        return (-1);
    }
    /**
     * Adds a track in the given index
     * @param index position where track will be placed
     * @param track track to be added to the array
     * @throws Exception when index is out of bounds of the array
     */
    private void setTrack(int index, Track track) throws Exception {
        if (index < 0 || index >= this.tracks.length)
            throw new Exception(ERR_WRONG_INDEX);
        this.tracks[index] = track;
    }
    /**
     * Adds a track in the first non-null position in the track list
     * @param track track to be added to the array
     * @throws Exception when index is out of bounds of the array, or when array is full
     */
    public void addTrack(Track track) throws Exception {
        int i;

        i = 0;
        if (this.isInTheAlbum(track))
            throw new Exception(Album.ERR_TRACK_EXISTS);
        while (i < this.tracks.length) {
            if (this.tracks[i] == null) {
                this.setTrack(i, track);
                break;
            }
            i++;
        }
        if (i == this.tracks.length)
            throw  new Exception(Album.ERR_WRONG_INDEX);
    }
    /**
     * Removes track from track list
     * @param track track to be removed
     */
    public void removeTrack(Track track) {
        for (int i = 0; i < this.tracks.length; i++) {
            if (this.tracks[i] == track)
                this.tracks[i] = null;
        }
    }
    /**
     * Deletes all tracks from an album
     */
    public void emptyAlbum() {
        for (Track track : this.tracks)
            this.removeTrack(track);
    }
    /**
     * Swaps two tracks in album, exchanging positions on the track list
     * @param track1 First track
     * @param track2 Second track
     * @throws Exception when either track cannot be found on the track list
     */
    public void swapTracks(Track track1, Track track2) throws Exception {
        int index1, index2;

        index1 = this.findTrackIndex(track1);
        index2 = this.findTrackIndex(track2);
        if ((index1 == -1 && track1 != null) || (index2 == -1 && track2 != null))
            throw new Exception(Album.ERR_TRACK_NOT_EXISTS);
        this.setTrack(index2, track1);
        this.setTrack(index1, track2);
    }
    /**
     * Prints the total duration of all tracks on an album in a human-readable way
     * @return string with the format HH:MM:SS
     */
    public String getTotalDuration() {
        int totalSeconds;

        totalSeconds = 0;
        for (Track track : this.tracks) {
            if (track != null)
                totalSeconds += track.getDuration();
        }
        return (String.format("%02d:%02d:%02d",totalSeconds / 3600, (totalSeconds % 3600) / 60, totalSeconds % 60));
    }
}
