package edu.uoc.pac2;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RPGCharacterTest {

    @BeforeEach
    void init() {
        try{
            Field field = RPGCharacter.class.getDeclaredField("nextId");
            field.setAccessible(true);
            field.set(null, 0);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }

    @Test
    void testRPGCharacterDefault() {
        RPGCharacter character = null;
        try {
            character = new RPGCharacter();
            assertEquals(0, character.getId());
            assertEquals("Name", character.getName());
            assertEquals(1, character.getLevel());
            assertEquals(100, character.getLife());
            assertTrue(character.isAlive());
            assertNull(character.getLastDeath());
            assertEquals('N', character.getAlignment());
        } catch (Exception e) {
            fail("Default constructor failed");
            e.printStackTrace();
        }

    }

    @Test
    void testRPGCharacter1() {
        RPGCharacter character = null;
        try {
            character = new RPGCharacter("Dakrad Ceyl", 'A');
            assertEquals(0, character.getId());
            assertEquals("DakradCeyl", character.getName());
            assertEquals(1, character.getLevel());
            assertEquals(100, character.getLife());
            assertTrue(character.isAlive());
            assertNull(character.getLastDeath());
            assertEquals('A', character.getAlignment());
        } catch (Exception e) {
            fail("Parameterized constructor failed");
            e.printStackTrace();
        }
    }

    @Test
    void testRPGCharacter2() {
        RPGCharacter character = null;
        try {
            character = new RPGCharacter("Dakrad Ceyl", 'A', 150);
            assertEquals(0, character.getId());
            assertEquals("DakradCeyl", character.getName());
            assertEquals(1, character.getLevel());
            assertEquals(150, character.getLife());
            assertTrue(character.isAlive());
            assertNull(character.getLastDeath());
            assertEquals('A', character.getAlignment());
        } catch (Exception e) {
            fail("Parameterized constructor failed");
            e.printStackTrace();
        }
    }

    @Test
    void testGetId() {
        try {
            int n = new Random().nextInt(10) + 10;
            for (int i = 0; i < n; ++i) {
                RPGCharacter character = new RPGCharacter();
                assertEquals(i, character.getId());
                assertEquals(i+1, RPGCharacter.getNextId());
            }
        } catch (Exception e) {
            fail("testGetId failed");
            e.printStackTrace();
        }
    }

    @Test
    void testSetName() {
        try {
            RPGCharacter character = new RPGCharacter();

            character.setName("Dakrad");
            assertEquals("Dakrad", character.getName());

            character.setName("Dakrad Ceyl");
            assertEquals("DakradCeyl", character.getName());

            character.setName("DAKRAD");
            assertEquals("Dakrad", character.getName());

            character.setName("DAKRAD CEYL");
            assertEquals("DakradCeyl", character.getName());

            character.setName("DaKRaD cEyL");
            assertEquals("DakradCeyl", character.getName());

            character.setName("dakraD CEYL thE ThIRD");
            assertEquals("DakradCeylTheThird", character.getName());

            character.setName("           dakrad");
            assertEquals("Dakrad", character.getName());

            character.setName("    dakraD     CEYL     thE    ThIRD     ");
            assertEquals("DakradCeylTheThird", character.getName());

            Exception ex = assertThrows(Exception.class, () -> character.setName("D@krad"));
            assertEquals("[ERROR] Name cannot contain any of the forbidden chars", ex.getMessage());

        } catch (Exception e) {
            fail("testSetName failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetName2() {
        try {
            RPGCharacter character = new RPGCharacter();

            character.setName("Dakrad", "[~!@#$%^&*()]");
            assertEquals("Dakrad", character.getName());

            Exception ex = assertThrows(Exception.class, () -> character.setName("Dakrad *", "[~!@#$%^&*()]"));
            assertEquals("[ERROR] Name cannot contain any of the forbidden chars", ex.getMessage());

            character.setName("Dakrad *", "[~!@#$%^&()]");
            assertEquals("Dakrad*", character.getName());

            ex = assertThrows(Exception.class, () -> character.setName("Dakrad_Ceyl", "[_]"));
            assertEquals("[ERROR] Name cannot contain any of the forbidden chars", ex.getMessage());

            ex = assertThrows(Exception.class, () -> character.setName("Dakrad-Ceyl", "[_*#-/]"));
            assertEquals("[ERROR] Name cannot contain any of the forbidden chars", ex.getMessage());

        } catch (Exception e) {
            fail("testSetName failed");
            e.printStackTrace();
        }
    }

    @Test
    void testIncLevel() {
        try {
            RPGCharacter character = new RPGCharacter();

            character.incLevel();
            assertEquals(2, character.getLevel());

            int n = new Random().nextInt(10) + 10;
            for (int i = 0; i < n; ++i) {
                character.incLevel();
            }
            assertEquals(n + 2, character.getLevel());
        } catch (Exception e) {
            fail("testIncLevel failed");
            e.printStackTrace();
        }

    }

    @Test
    void testupdateLife() {
        try {
            RPGCharacter character = new RPGCharacter();

            character.updateLife(50);
            assertEquals(150, character.getLife());

            character.updateLife(-50);
            assertEquals(100, character.getLife());

            character.updateLife(-99);
            assertEquals(1, character.getLife());

            Exception ex = assertThrows(Exception.class, () -> character.updateLife(5));
            assertEquals("[ERROR] A character cannot increase its life more than 50% in a single healing", ex.getMessage());

            character.updateLife(-3);
            assertEquals(0, character.getLife());
            assertFalse(character.isAlive());

            ex = assertThrows(Exception.class, () -> character.updateLife(100));
            assertEquals("[ERROR] The character is dead", ex.getMessage());

            assertEquals(0, character.getLife());
            assertFalse(character.isAlive());
        }  catch (Exception e) {
            fail("testupdateLife failed");
            e.printStackTrace();
        }
    }

    @Test
    void testResurrect() {
        try {
            RPGCharacter character = new RPGCharacter();

            Exception ex = assertThrows(Exception.class, () -> character.resurrect());
            assertEquals("[ERROR] The character is already alive", ex.getMessage());

            character.updateLife(-100);
            character.resurrect();
            assertTrue(character.isAlive());
            assertEquals(1, character.getLife());
        }  catch (Exception e) {
            fail("testResurrect failed");
            e.printStackTrace();
        }
    }

    @Test
    void testLastDeath() {
        try {
            RPGCharacter character = new RPGCharacter();

            LocalDate now = LocalDate.now();

            character.updateLife(-100);
            assertEquals(now, character.getLastDeath());
        } catch (Exception e) {
            fail("testLastDeath failed");
            e.printStackTrace();
        }
    }

    @Test
    void testAlignment() {
        try {
            RPGCharacter character = new RPGCharacter();

            character.setAlignment('H');
            assertEquals('H', character.getAlignment());

            character.setAlignment('N');
            assertEquals('N', character.getAlignment());

            character.setAlignment('A');
            assertEquals('A', character.getAlignment());

            Exception ex = assertThrows(Exception.class, () -> character.setAlignment('X'));
            assertEquals("[ERROR] Alignment must be a valid value ('H', 'A' or 'N')", ex.getMessage());
        }  catch (Exception e) {
            fail("testAlignment failed");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(9, RPGCharacter.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("nextId").getModifiers()));
            assertTrue(Modifier.isStatic(RPGCharacter.class.getDeclaredField("nextId").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("forbiddenChars").getModifiers()));
            assertTrue(Modifier.isStatic(RPGCharacter.class.getDeclaredField("forbiddenChars").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("level").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("life").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("isAlive").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("lastDeath").getModifiers()));
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredField("alignment").getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(3, RPGCharacter.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredConstructor(String.class, char.class).getModifiers()));
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredConstructor(String.class, char.class, int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //check methods, parameters and return types
        try {
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getId").getModifiers()));
            assertEquals(int.class, RPGCharacter.class.getDeclaredMethod("getId").getReturnType());
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredMethod("setId").getModifiers()));
            assertTrue(Modifier.isStatic(RPGCharacter.class.getDeclaredMethod("getNextId").getModifiers()));
            assertEquals(int.class, RPGCharacter.class.getDeclaredMethod("getNextId").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the id attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getName").getModifiers()));
            assertEquals(String.class, RPGCharacter.class.getDeclaredMethod("getName").getReturnType());
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("setName", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getLevel").getModifiers()));
            assertEquals(int.class, RPGCharacter.class.getDeclaredMethod("getLevel").getReturnType());
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredMethod("setLevel", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("incLevel").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the level attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getLife").getModifiers()));
            assertEquals(int.class, RPGCharacter.class.getDeclaredMethod("getLife").getReturnType());
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredMethod("setLife", int.class).getModifiers()));
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("updateLife", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the life attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("isAlive").getModifiers()));
            assertEquals(boolean.class, RPGCharacter.class.getDeclaredMethod("isAlive").getReturnType());
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredMethod("setAlive", boolean.class).getModifiers()));
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("resurrect").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the isAlive attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getLastDeath").getModifiers()));
            assertEquals(LocalDate.class, RPGCharacter.class.getDeclaredMethod("getLastDeath").getReturnType());
            assertTrue(Modifier.isPrivate(RPGCharacter.class.getDeclaredMethod("setLastDeath", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the lastDeath attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("getAlignment").getModifiers()));
            assertEquals(char.class, RPGCharacter.class.getDeclaredMethod("getAlignment").getReturnType());
            assertTrue(Modifier.isPublic(RPGCharacter.class.getDeclaredMethod("setAlignment", char.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the alignment attribute");
            e.printStackTrace();
        }
    }

}
