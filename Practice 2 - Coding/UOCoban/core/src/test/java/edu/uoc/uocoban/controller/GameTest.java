package edu.uoc.uocoban.controller;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.entities.movable.Box;
import edu.uoc.uocoban.model.entities.movable.Player;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class GameTest {

    Game game;

    @BeforeEach
    public void initGame() {
        try {
            game = new Game("levels/");
        } catch (IOException e) {
            e.printStackTrace();
            fail("There was some problem with the Game constructor");
        }
    }

    @Test
    void testDecLives() {
        assertEquals(3, game.getLives());
        game.decLives();
        assertEquals(2, game.getLives());
        game.decLives();
        assertEquals(1, game.getLives());
        game.decLives();
        assertEquals(0, game.getLives());
        game.decLives();
        game.decLives();
        game.decLives();
        assertEquals(0, game.getLives());
    }

    @Test
    void testNextLevel() {
        try {
            assertEquals(0, game.getCurrentLevel());

            assertTrue(game.nextLevel());
            assertEquals(1, game.getCurrentLevel());
            assertEquals(0, game.getScore());

            assertTrue(game.nextLevel());
            assertEquals(2, game.getCurrentLevel());
            assertEquals(20, game.getScore());
            game.movePlayer(Direction.RIGHT);

            assertTrue(game.nextLevel());
            assertEquals(3, game.getCurrentLevel());
            assertEquals(44, game.getScore());
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.RIGHT);

            assertTrue(game.nextLevel());
            assertEquals(4, game.getCurrentLevel());
            assertEquals(76, game.getScore());

            assertFalse(game.nextLevel());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testNextLevel");
        }
    }

    @Test
    void testIsFinished() {
        try {
            assertFalse(game.isFinished());
            game.nextLevel();
            assertFalse(game.isFinished());
            game.nextLevel();
            assertFalse(game.isFinished());
            game.nextLevel();
            assertFalse(game.isFinished());
            game.nextLevel();
            assertTrue(game.isFinished());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testIsFinished");
        }
    }

    @Test
    void testIsLevelCompleted() {
        try {
            game.nextLevel();
            assertFalse(game.isLevelCompleted());

            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.LEFT);
            assertFalse(game.isLevelCompleted());

            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.UP);
            game.movePlayer(Direction.UP);
            assertFalse(game.isLevelCompleted());

            game.movePlayer(Direction.DOWN);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.RIGHT);
            assertFalse(game.isLevelCompleted());

            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.DOWN);
            game.movePlayer(Direction.DOWN);
            game.movePlayer(Direction.DOWN);
            assertTrue(game.isLevelCompleted());
            
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testIsLevelCompleted");
        }
    }

    @Test
    void testIsLevelDeadlocked() {
        try {
            game.nextLevel();
            game.nextLevel();
            assertFalse(game.isLevelDeadlocked());

            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.DOWN);
            game.movePlayer(Direction.DOWN);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.RIGHT);
            game.movePlayer(Direction.UP);
            game.movePlayer(Direction.UP);
            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.LEFT);
            game.movePlayer(Direction.LEFT);

            assertTrue(game.isLevelDeadlocked());
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testIsLevelDeadlocked");
        }
    }

    @Test
    void testHasLostLevel() {
        try {
            game.nextLevel();
            assertFalse(game.hasLostLevel());

            for (int i = 0; i < 20; ++i) {
                assertFalse(game.hasLostLevel());
                if (i % 2 == 0)
                    game.movePlayer(Direction.UP);
                else
                    game.movePlayer(Direction.DOWN);
            }
            assertTrue(game.hasLostLevel());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testHasLostLevel");
        }
    }

    @Test
    void testHasLostGame() {
        try {
            game.nextLevel();
            assertFalse(game.hasLostGame());
            game.decLives();
            game.decLives();
            assertFalse(game.hasLostGame());
            game.decLives();
            assertTrue(game.hasLostGame());
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testHasLostGame");
        }
    }

}
