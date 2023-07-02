package edu.uoc.uocoban.model.entities.movable;

import edu.uoc.uocoban.model.Level;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import edu.uoc.uocoban.model.utils.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class PlayerTest {

    private Level level;
    private Player player;

    @BeforeEach
    public void initBigBox() {
        try {
            level = new Level("levels/level1.txt");
            player = level.getPlayer();
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with PlayerTest initialization");
        }
    }

    @Test
    @Tag("initLevel")
    public void testInitPlayer() {
        assertEquals(Sprite.PLAYER, player.getSprite());
        assertEquals(level, player.getLevel());
        assertEquals(new Position(4,4), player.getPosition());
    }

    @Test
    @Tag("initLevel")
    public void testIsPathable() {
        assertFalse(player.isPathable());
    }

    @Test
    @Tag("initLevel")
    void testSetPosition() {
        player.setPosition(new Position(0,1));
        assertEquals(new Position(0,1), player.getPosition());
    }

    @Test
    @Tag("playLevel")
    public void testMovePlayer1() {
        assertFalse(player.move(Direction.RIGHT));

        assertTrue(player.move(Direction.LEFT));
        assertEquals(new Position(3,4), player.getPosition());

        assertTrue(player.move(Direction.LEFT));
        assertEquals(new Position(2,4), player.getPosition());

        assertFalse(player.move(Direction.LEFT));
        assertEquals(new Position(2,4), player.getPosition());

        assertTrue(player.move(Direction.RIGHT));
        assertEquals(new Position(3,4), player.getPosition());

        assertTrue(player.move(Direction.UP));
        assertEquals(new Position(3,3), player.getPosition());

        assertTrue(player.move(Direction.UP));
        assertEquals(new Position(3,2), player.getPosition());

        assertFalse(player.move(Direction.UP));
        assertEquals(new Position(3,2), player.getPosition());

        assertTrue(player.move(Direction.DOWN));
        assertEquals(new Position(3,3), player.getPosition());

        assertTrue(player.move(Direction.RIGHT));
        assertEquals(new Position(4,3), player.getPosition());

        assertTrue(player.move(Direction.RIGHT));
        assertEquals(new Position(5,3), player.getPosition());

        assertTrue(player.move(Direction.RIGHT));
        assertEquals(new Position(6,3), player.getPosition());

        assertFalse(player.move(Direction.RIGHT));
        assertEquals(new Position(6,3), player.getPosition());

        assertTrue(player.move(Direction.LEFT));
        assertEquals(new Position(5,3), player.getPosition());

        assertTrue(player.move(Direction.LEFT));
        assertEquals(new Position(4,3), player.getPosition());

        assertTrue(player.move(Direction.DOWN));
        assertEquals(new Position(4,4), player.getPosition());

        assertTrue(player.move(Direction.DOWN));
        assertEquals(new Position(4,5), player.getPosition());

        assertTrue(player.move(Direction.DOWN));
        assertEquals(new Position(4,6), player.getPosition());

        assertFalse(player.move(Direction.DOWN));
        assertFalse(player.move(Direction.DOWN));
        assertFalse(player.move(Direction.DOWN));
        assertFalse(player.move(Direction.DOWN));
        assertEquals(new Position(4,6), player.getPosition());
    }

    @Test
    @Tag("playLevel")
    public void testMovePlayer2() {
        try {
            level = new Level("levels/level2.txt");
            player = level.getPlayer();

            assertFalse(player.move(Direction.LEFT));
            assertFalse(player.move(Direction.UP));

            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertFalse(player.move(Direction.RIGHT));

            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.DOWN));
            assertFalse(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertFalse(player.move(Direction.RIGHT));
            assertEquals(new Position(6,3), player.getPosition());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testMovePlayer2");
        }
    }

    @Test
    @Tag("playLevel")
    public void testMovePlayer3() {
        try {
            level = new Level("levels/level3.txt");
            player = level.getPlayer();

            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertFalse(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.DOWN));
            assertEquals(new Position(5,2), player.getPosition());

            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.LEFT));
            assertFalse(player.move(Direction.LEFT));
            assertEquals(new Position(6,4), player.getPosition());

            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.UP));
            assertFalse(player.move(Direction.UP));
            assertEquals(new Position(4,5), player.getPosition());

            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.LEFT));
            assertTrue(player.move(Direction.UP));
            assertTrue(player.move(Direction.RIGHT));
            assertFalse(player.move(Direction.RIGHT));
            assertEquals(new Position(3,3), player.getPosition());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testMovePlayer3");
        }
    }

    @Test
    @Tag("playLevel")
    public void testMovePlayer4() {
        try {
            level = new Level("levels/level3.txt");
            player = level.getPlayer();

            assertTrue(player.move(Direction.DOWN));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertTrue(player.move(Direction.RIGHT));
            assertFalse(player.move(Direction.RIGHT));

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error with testMovePlayer3");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Player - Class definition")
    void checkClassSanity() {
        final Class<Player> ownClass = Player.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model.entities.movable",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Player - Fields definition")
    public void checkFieldsSanity() {
        final Class<Player> ownClass = Player.class;

        //check attribute fields
        assertEquals(0, ownClass.getDeclaredFields().length);

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Player - Methods definition")
    public void checkMethodsSanity() {
        final Class<Player> ownClass = Player.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(Position.class, Level.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Level's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(2, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());

        try {
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("move", Direction.class).getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("move", Direction.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isPathable").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isPathable").getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
