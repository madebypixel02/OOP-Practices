package edu.uoc.uocoban.model;

import edu.uoc.uocoban.model.entities.MapItem;
import edu.uoc.uocoban.model.entities.movable.BigBox;
import edu.uoc.uocoban.model.entities.movable.Box;
import edu.uoc.uocoban.model.entities.movable.Player;
import edu.uoc.uocoban.model.entities.movable.SmallBox;
import edu.uoc.uocoban.model.entities.pathable.BigBoxDestination;
import edu.uoc.uocoban.model.entities.pathable.Destination;
import edu.uoc.uocoban.model.entities.pathable.SmallBoxDestination;
import edu.uoc.uocoban.model.exceptions.LevelException;
import edu.uoc.uocoban.model.utils.Direction;
import edu.uoc.uocoban.model.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    private Level level;

    @BeforeEach
    public void initLevelTest() {
        try {
            level = new Level("levels/level1.txt");
        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error initializing the level");
        }
    }

    @Test
    @Tag("initLevel")
    public void testMinimumHeightError() {
        Exception ex = assertThrows(LevelException.class, () -> new Level("exception_levels/min_height.txt"));
        assertEquals("[ERROR] There is a problem with the height/width of the level", ex.getMessage());
    }

    @Test
    @Tag("initLevel")
    public void testMinimumWidthError() {
        Exception ex = assertThrows(LevelException.class, () -> new Level("exception_levels/min_width.txt"));
        assertEquals("[ERROR] There is a problem with the height/width of the level", ex.getMessage());
    }

    @Test
    @Tag("initLevel")
    public void testMinimumMovementsError() {
        Exception ex = assertThrows(LevelException.class, () -> new Level("exception_levels/min_remaining_movements.txt"));
        assertEquals("[ERROR] The maximum number of movements should be greater than zero", ex.getMessage());
    }

    @Test
    @Tag("playLevel")
    public void testDecRemainingMovements() {
        level.decRemainingMovements();
        assertEquals(19, level.getRemainingMovements());

        level.decRemainingMovements();
        assertEquals(18, level.getRemainingMovements());

        for (int i = 0; i < 18; ++i)
            level.decRemainingMovements();

        assertEquals(0, level.getRemainingMovements());

        level.decRemainingMovements();
        level.decRemainingMovements();
        assertEquals(0, level.getRemainingMovements());
    }

    @Test
    @Tag("playLevel")
    public void testHasWon() {
        assertFalse(level.hasWon());
        for (Box box : level.getBoxList()) {
            Destination d = (Destination) level.getMapItemList().stream().filter(mapItem -> (box instanceof BigBox && mapItem instanceof BigBoxDestination
                || box instanceof SmallBox && mapItem instanceof SmallBoxDestination) && ((Destination) mapItem).isEmpty()).findFirst().orElse(null);
            d.setBox(box);
        }
        assertTrue(level.hasWon());
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing deadlock by cornered box")
    public void testIsDeadlocked1() {
        try {
            level = new Level("levels/level2.txt");
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.LEFT);
            assertTrue(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked1");
        }
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing deadlock by left horizontal block between two boxes")
    public void testIsDeadlocked2() {
        try {
            level = new Level("levels/level2.txt");
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.UP);
            assertTrue(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked2");
        }
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing deadlock by right horizontal block between two boxes")
    public void testIsDeadlocked3() {
        try {
            level = new Level("levels/level2.txt");
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.UP);
            assertTrue(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked3");
        }
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing deadlock by down vertical block between two boxes")
    public void testIsDeadlocked4() {
        try {
            level = new Level("levels/level4.txt");
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.DOWN);
            assertTrue(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked2");
        }
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing deadlock by up vertical block between two boxes")
    public void testIsDeadlocked5() {
        try {
            level = new Level("levels/level4.txt");

            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);

            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.LEFT);
            assertTrue(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked3");
        }
    }

    @Test
    @Tag("deadlock")
    @DisplayName("Testing non-deadlock situations")
    public void testIsDeadlocked6() {
        try {
            level = new Level("levels/level4.txt");
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());

            level.getPlayer().move(Direction.RIGHT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.DOWN);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.LEFT);
            assertFalse(level.isDeadlocked());
            level.getPlayer().move(Direction.UP);
            assertFalse(level.isDeadlocked());

        } catch (LevelException e) {
            e.printStackTrace();
            fail("There was some error in testIsDeadlocked3");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Level - Class definition")
    void checkClassSanity() {
        final Class<Level> ownClass = Level.class;

        //Class declaration
        int modifiers = ownClass.getModifiers();
        assertTrue(Modifier.isPublic(modifiers));
        assertFalse(Modifier.isStatic(modifiers));
        assertFalse(Modifier.isFinal(modifiers));
        assertFalse(Modifier.isAbstract(modifiers));

        assertEquals("edu.uoc.uocoban.model",ownClass.getPackageName());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Level - Fields definition")
    public void checkFieldsSanity() {
        final Class<Level> ownClass = Level.class;

        //check attribute fields
        assertEquals(9, ownClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("fileName").getModifiers()));
            assertEquals(String.class, ownClass.getDeclaredField("fileName").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("width").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("width").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("height").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("height").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("remainingMovements").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("remainingMovements").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("mapItemList").getModifiers()));
            assertEquals(List.class, ownClass.getDeclaredField("mapItemList").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("player").getModifiers()));
            assertEquals(Player.class, ownClass.getDeclaredField("player").getType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("boxList").getModifiers()));
            assertEquals(List.class, ownClass.getDeclaredField("boxList").getType());

            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("MIN_WIDTH").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MIN_WIDTH").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MIN_WIDTH").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("MIN_WIDTH").getType());

            assertTrue(Modifier.isPrivate(ownClass.getDeclaredField("MIN_HEIGHT").getModifiers()));
            assertTrue(Modifier.isStatic(ownClass.getDeclaredField("MIN_HEIGHT").getModifiers()));
            assertTrue(Modifier.isFinal(ownClass.getDeclaredField("MIN_HEIGHT").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredField("MIN_HEIGHT").getType());

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity Level - Methods definition")
    public void checkMethodsSanity() {
        final Class<Level> ownClass = Level.class;

        //check constructors
        assertEquals(1, ownClass.getDeclaredConstructors().length);

        try {
            //Constructor
            int constructorModifiers = ownClass.getDeclaredConstructor(String.class).getModifiers();
            assertTrue(Modifier.isPublic(constructorModifiers));
            assertFalse(Modifier.isStatic(constructorModifiers));
            assertFalse(Modifier.isFinal(constructorModifiers));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] Level's constructor is defined wrongly");
        }

        //Max public methods
        assertEquals(15, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPublic(p.getModifiers())).count());
        //Max protected methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isProtected(p.getModifiers())).count());
        //Max package-private methods
        assertEquals(0, Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isNative(p.getModifiers())).count());
        //Max package-private methods
        assertTrue(Arrays.stream(ownClass.getDeclaredMethods()).filter(p -> Modifier.isPrivate(p.getModifiers())).count() >= 7);

        try {
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setFileName", String.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setFileName", String.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getFileName").getModifiers()));
            assertEquals(String.class, ownClass.getDeclaredMethod("getFileName").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getWidth").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getWidth").getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setWidth", int.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setWidth", int.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getHeight").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getHeight").getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setHeight", int.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setHeight", int.class).getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("parse").getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("parse").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getRemainingMovements").getModifiers()));
            assertEquals(int.class, ownClass.getDeclaredMethod("getRemainingMovements").getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("setRemainingMovements", int.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("setRemainingMovements", int.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("decRemainingMovements").getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("decRemainingMovements").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMapItemList").getModifiers()));
            assertEquals(List.class, ownClass.getDeclaredMethod("getMapItemList").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMapItem", int.class, int.class).getModifiers()));
            assertEquals(MapItem.class, ownClass.getDeclaredMethod("getMapItem", int.class, int.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getMapItem", Position.class).getModifiers()));
            assertEquals(MapItem.class, ownClass.getDeclaredMethod("getMapItem", Position.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("addMapItem", MapItem.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("addMapItem", MapItem.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("removeMapItem", MapItem.class).getModifiers()));
            assertEquals(void.class, ownClass.getDeclaredMethod("removeMapItem", MapItem.class).getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getBoxList").getModifiers()));
            assertEquals(List.class, ownClass.getDeclaredMethod("getBoxList").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("getPlayer").getModifiers()));
            assertEquals(Player.class, ownClass.getDeclaredMethod("getPlayer").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("hasWon").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("hasWon").getReturnType());
            assertTrue(Modifier.isPublic(ownClass.getDeclaredMethod("isDeadlocked").getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isDeadlocked").getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("isDeadlocked", Box.class).getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isDeadlocked", Box.class).getReturnType());
            assertTrue(Modifier.isPrivate(ownClass.getDeclaredMethod("isPathableOrMovable", int.class, int.class).getModifiers()));
            assertEquals(boolean.class, ownClass.getDeclaredMethod("isPathableOrMovable", int.class, int.class).getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of methods");
            e.printStackTrace();
        }
    }

}
