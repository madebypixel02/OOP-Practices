package edu.uoc.uocoban.controller;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.entities.movable.Box;
import edu.uoc.uocoban.model.entities.movable.Player;
import edu.uoc.uocoban.model.entities.pathable.Destination;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

/**
 * Controller class of the game. It is the middleware (or bridge) between the model and view classes.
 * <br/>
 * This class is called from the view classes in order to access/modify the model data.
 * This class controls the logic of the game.
 *
 * @author aperez-b
 * @version 1.0
 */
public class Game {
    /**
     * Name of the folder in which level files are
     */
    private String fileFolder;
    /**
     * Number of the current level.
     */
    private int currentLevel = 0;
    /**
     * Maximum amount of levels that the game has.
     */
    private final int maxLevels;
    /**
     * Total score of the game, i.e. the sum of the levels' scores
     */
    private int score;
    /**
     * Level object that contains the information of the current level.
     */
    private Level level;
    /**
     * Number of starting lives
     */
    private int lives = 3;
    /**
     * Constructor with argument.
     * @param fileFolder Folder name where the configuration/level files are.
     * @throws IOException When there is a problem while retrieving number of levels
     */
    public Game(String fileFolder) throws IOException {
        int num;

        setFileFolder(fileFolder);

        //Get the number of files that are in the fileFolder, i.e. the number of levels.
        URL url = getClass().getClassLoader().getResource(getFileFolder());

        URLConnection urlConnection = Objects.requireNonNull(url).openConnection();

        if (urlConnection instanceof JarURLConnection) {
            //run in jar
            String path = null;
            try {
                path = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            } catch (URISyntaxException e) {
                System.out.println("ERROR: Game Constructor");
                e.printStackTrace();
                System.exit(-1);
            }

            URI uri = URI.create("jar:file:" + path);

            try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                num = (int) Files.walk(fs.getPath(getFileFolder()))
                        .filter(Files::isRegularFile).count();
            }
        } else {
            //run in ide
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream;
            inputStream = Objects.requireNonNull(classLoader.getResourceAsStream(getFileFolder()));

            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                num = (int) reader.lines().count();
            }
        }
        setScore(0);
        maxLevels = num;
    }
    /**
     * Setter of the attribute {@code fileFolder}.
     * @param fileFolder Folder name where the configuration/level files are.
     */
    private void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }
    /**
     * Getter of the attribute {@code fileFolder}.
     * @return Value of the attribute {@code fileFolder}.
     */
    private String getFileFolder() {
        return fileFolder;
    }
    /**
     * Getter of the attribute {@code currentLevel}.
     * @return Value of the attribute {@code currentLevel} that indicates which level the player is playing.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
    /**
     * Getter of the attribute "score" (Game score).
     * @return Value of the attribute "score"
     */
    public int getScore() {
        return score;
    }
    /**
     * Setter of the attribute "score" (Game score).
     * @param score New value for the attribute "score"
     */
    private void setScore(int score) {
        this.score = score;
    }
    /**
     * Returns the number of lives that the player has in the current game.
     * @return Number of lives in the current game.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Decreases the number of lives that the user has in the current game.
     * If {@code lives} is 0, then this method does nothing.
     */
    public void decLives() {
        //TODO
    }
    /**
     * Gets the current remaining movements of the level that the player is playing.
     * @return Remaining movements of the current level.
     */
    public int getRemainingMovements() {
        return level.getRemainingMovements();
    }
    /**
     * Gests the @link Player} object which is present in the current level.
     * @return Reference to the {@link Player} object in the current level.
     */
    public Player getPlayer() {
        return level.getPlayer();
    }

    /**
     * Checks if there is a new level to play and loads it.<br/>
     * If the game is finished, it returns {@code false}. Otherwise, it returns {@code true}.<br/>
     * The game score must be updated when a level is finished. The score for a given level is the total
     * amount of remaining movements when finishing the game.
     * Thus, when the player is playing the first level, game's score is zero.
     * @return {@code true} if there is a next level, and it has been loaded correctly. Otherwise, it returns {@code false}.
     * @throws LevelException When there is a level exception/problem loading the new level.
     */
    public boolean nextLevel() throws LevelException {
        //TODO
        return false;
    }
    /**
     * Loads a new level by using the value of the attribute {@code currentLevel}.
     * @throws LevelException When there is a level exception/problem.
     */
    private void loadLevel() throws LevelException {
        level = new Level(getFileFolder() + "level" + currentLevel + ".txt");
    }
    /**
     * Indicates if the game is finished ({@code true}) or not ({@code false}).
     * @return {@code true} if there are no more levels and therefore the game is finished. Otherwise, {@code false}.
     */
    public boolean isFinished() {
        //TODO
        return false;
    }
    /**
     * Checks if the current level is completed, i.e. all objects from type {@link Destination}
     * have a valid {@link Box}.
     * @return {@code true} if this level is completed, otherwise {@code false}.
     */
    public boolean isLevelCompleted() {
        //TODO
        return false;
    }
    /**
     * Checks if the current level is deadlocked, i.e. the player cannot perform any movements to win the game
     * @return {@code true} if this level is deadlocked, otherwise {@code false}.
     */
    public boolean isLevelDeadlocked() {
        //TODO
        return false;
    }
    /**
     * Checks if the player has lost the game, i.e. the number of lives is zero
     * @return {@code true} if this the player has lost, otherwise {@code false}.
     */
    public boolean hasLostGame() {
        //TODO
        return false;
    }
    /**
     * Checks if the player has lost the current level.
     * @return {@code true} if the number of remaining movements is 0 and the current level
     * has not been won; otherwise, {@code false}.
     */
    public boolean hasLostLevel() {
        //TODO
        return false;
    }
    /**
     * Reloads the current level, i.e. load the level again.
     * @throws LevelException When there is a level exception/problem.
     */
    public void reloadLevel() throws LevelException {
        loadLevel();
    }
    /**
     * Restarts the game so that it starts again.
     */
    public void restart() {
        setScore(0);
        lives = 3;
        currentLevel = 0;
    }
    /**
     * Gets an Iterator of the {@code itemMapList}
     * @return Iterator of the {@code itemMapList}
     */
    public Iterator<MapItem> getItemMapListIterator() {
        return level.getMapItemListIterator();
    }
    /**
     * Moves the player in a specific direction. If the player is successfully moved, then the remaining movements of the
     * level must be decreased.
     * @param direction The {@code direction} (i.e., UP, DOWN, lEFT, RIGHT) towards the user is moved to.
     */
    public void movePlayer(Direction direction) {
        boolean moved = getPlayer().move(direction);
        if (moved)
            level.decRemainingMovements();
    }

}
