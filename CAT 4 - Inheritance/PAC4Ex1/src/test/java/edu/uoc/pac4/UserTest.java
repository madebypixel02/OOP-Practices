package edu.uoc.pac4;

import edu.uoc.pac4.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    User user;
    Address address;

    @BeforeEach
    void initializeUser() {
        try {
            address = mock(Address.class); // Mock object

            LocalDate birthDate = LocalDate.of(1964, 1, 12);
            user = new User("Jeff Bezos", "jeffbezzos@amazon.com", birthDate, Gender.OTHER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("initializeUser failed");
        }
    }

    @Test
    void testUser1() {
        try {
            LocalDate birthDate = LocalDate.of(1964, 1, 12);
            assertEquals("Jeff Bezos", user.getName());
            assertEquals("jeffbezzos@amazon.com", user.getEmail());
            assertEquals(birthDate, user.getBirthDate());
            assertEquals(0, user.getDebt());
            assertEquals(Gender.OTHER, user.getGender());
            assertFalse(user.isPremium());
            assertNull(user.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            fail("testUser1 failed");
        }

    }

    @Test
    void testUser2() {
        try {
            LocalDate birthDate = LocalDate.of(1960, 11, 1);
            user = new User("Tim Cook", "tim.cook@apple.ai.com", birthDate, Gender.MALE, address);

            assertEquals("Tim Cook", user.getName());
            assertEquals("tim.cook@apple.ai.com", user.getEmail());
            assertEquals(birthDate, user.getBirthDate());
            assertEquals(0, user.getDebt());
            assertEquals(Gender.MALE, user.getGender());
            assertFalse(user.isPremium());
            assertNotNull(user.getAddress());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testUser2 failed");
        }

    }

    @Test
    void testSetName() {
        try {
            user.setName("Susan Wojcicki");
            assertEquals("Susan Wojcicki", user.getName());

            user.setName("Melanie Perkins");
            assertEquals("Melanie Perkins", user.getName());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetName failed");
        }

    }

    @Test
    void testSetEmail() {
        try {
            user.setEmail("jeff.bezzos@amazon.com");
            assertEquals("jeff.bezzos@amazon.com", user.getEmail());

            user.setEmail("jeff.bezzos.ceo@amazon.ai.com");
            assertEquals("jeff.bezzos.ceo@amazon.ai.com", user.getEmail());

            user.setEmail("JEFF.bezzos.CEO@amazon.ai.com");
            assertEquals("JEFF.bezzos.CEO@amazon.ai.com", user.getEmail());

            user.setEmail("j@a.");
            assertEquals("j@a.", user.getEmail());

            user.setEmail("j@.a");
            assertEquals("j@.a", user.getEmail());

            user.setEmail(".@.");
            assertEquals(".@.", user.getEmail());

            Exception ex = assertThrows(Exception.class, () -> user.setEmail("@"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("a@"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("@a"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("@a."));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("wrongemail@nodot"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("@noname.com"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("@nonamecom"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.setEmail("missingdomain@"));
            assertEquals("[ERROR] The email is not in a valid format", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetEmail failed");
        }

    }

    @Test
    void testSetBirthDate() {
        try {
            LocalDate newBirthDate = LocalDate.of(1990, 1, 1);
            user.setBirthDate(newBirthDate);
            assertEquals(newBirthDate, user.getBirthDate());

            newBirthDate = LocalDate.of(1990, 12, 31);
            user.setBirthDate(newBirthDate);
            assertEquals(newBirthDate, user.getBirthDate());

            newBirthDate = LocalDate.of(2007, 4, 1);
            user.setBirthDate(newBirthDate);
            assertEquals(newBirthDate, user.getBirthDate());

            newBirthDate = LocalDate.now().minusYears(16);
            user.setBirthDate(newBirthDate);
            assertEquals(newBirthDate, user.getBirthDate());

            LocalDate newBirthDate1 = LocalDate.now().minusYears(16).plusDays(1);
            Exception ex = assertThrows(Exception.class, () -> user.setBirthDate(newBirthDate1));
            assertEquals("[ERROR] The user must be at least 16 years old", ex.getMessage());

            LocalDate newBirthDate2 = LocalDate.now().minusYears(16).plusMonths(1);
            ex = assertThrows(Exception.class, () -> user.setBirthDate(newBirthDate2));
            assertEquals("[ERROR] The user must be at least 16 years old", ex.getMessage());

            LocalDate newBirthDate3 = LocalDate.now().minusYears(15);
            ex = assertThrows(Exception.class, () -> user.setBirthDate(newBirthDate3));
            assertEquals("[ERROR] The user must be at least 16 years old", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetBirthDate failed");
        }

    }

    @Test
    void testAddResetDebt() {
        try {
            user.addDebt(250.60);
            assertEquals(250.60, user.getDebt());

            user.addDebt(1234.27);
            assertEquals(1484.87, user.getDebt());

            user.resetDebt();
            assertEquals(0, user.getDebt());

            user.addDebt(756.01);
            assertEquals(756.01, user.getDebt());

            user.resetDebt();
            assertEquals(0, user.getDebt());

            user.resetDebt();
            assertEquals(0, user.getDebt());

            Exception ex = assertThrows(Exception.class, () -> user.addDebt(0));
            assertEquals("[ERROR] The added debt value must be greater than zero", ex.getMessage());

            ex = assertThrows(Exception.class, () -> user.addDebt(-25.9));
            assertEquals("[ERROR] The added debt value must be greater than zero", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddResetDebt failed");
        }

    }

    @Test
    void testSubscribeUnsubscribe() {
        try {
            user.subscribe();
            assertTrue(user.isPremium());

            user.subscribe();
            assertTrue(user.isPremium());

            user.unsubscribe();
            assertFalse(user.isPremium());

            user.unsubscribe();
            assertFalse(user.isPremium());

            user.addDebt(0.01);
            Exception ex = assertThrows(Exception.class, () -> user.subscribe());
            assertEquals("[ERROR] The user cannot be subscribed as premium if he/she is in debt", ex.getMessage());

            user.resetDebt();
            user.subscribe();
            assertTrue(user.isPremium());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSubscribeUnsubscribe failed");
        }

    }

    @Test
    void testSetGender() {
        try {
            user.setGender(Gender.MALE);
            assertEquals(Gender.MALE, user.getGender());

            user.setGender(Gender.FEMALE);
            assertEquals(Gender.FEMALE, user.getGender());

            user.setGender(Gender.OTHER);
            assertEquals(Gender.OTHER, user.getGender());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetGender failed");
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (User) - Fields definition")
    void checkUserFieldsSanity() {
        //check attribute fields
        assertEquals(7, User.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("email").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("birthDate").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("debt").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("premium").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("gender").getModifiers()));
            assertTrue(Modifier.isPrivate(User.class.getDeclaredField("address").getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (UserException) - Fields definition")
    void checkUserExceptionFieldsSanity() {
        //check attribute fields
        assertEquals(4, UserException.class.getDeclaredFields().length);
        try {

            assertTrue(Modifier.isPublic(UserException.class.getDeclaredField("ERR_INVALID_EMAIL").getModifiers()));
            assertTrue(Modifier.isStatic(UserException.class.getDeclaredField("ERR_INVALID_EMAIL").getModifiers()));
            assertTrue(Modifier.isFinal(UserException.class.getDeclaredField("ERR_INVALID_EMAIL").getModifiers()));

            assertTrue(Modifier.isPublic(UserException.class.getDeclaredField("ERR_MIN_AGE").getModifiers()));
            assertTrue(Modifier.isStatic(UserException.class.getDeclaredField("ERR_MIN_AGE").getModifiers()));
            assertTrue(Modifier.isFinal(UserException.class.getDeclaredField("ERR_MIN_AGE").getModifiers()));

            assertTrue(Modifier.isPublic(UserException.class.getDeclaredField("ERR_ADD_INVALID_DEBT_VALUE").getModifiers()));
            assertTrue(Modifier.isStatic(UserException.class.getDeclaredField("ERR_ADD_INVALID_DEBT_VALUE").getModifiers()));
            assertTrue(Modifier.isFinal(UserException.class.getDeclaredField("ERR_ADD_INVALID_DEBT_VALUE").getModifiers()));

            assertTrue(Modifier.isPublic(UserException.class.getDeclaredField("ERR_PREMIUM_SUBSCRIPTION").getModifiers()));
            assertTrue(Modifier.isStatic(UserException.class.getDeclaredField("ERR_PREMIUM_SUBSCRIPTION").getModifiers()));
            assertTrue(Modifier.isFinal(UserException.class.getDeclaredField("ERR_PREMIUM_SUBSCRIPTION").getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (User) - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(2, User.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(User.class.getDeclaredConstructor(String.class, String.class, LocalDate.class, Gender.class).getModifiers()));
            assertTrue(Modifier.isPublic(User.class.getDeclaredConstructor(String.class, String.class, LocalDate.class, Gender.class, Address.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("There is some problem with the definition of the constructor");
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getName").getModifiers()));
            assertEquals(String.class, User.class.getDeclaredMethod("getName").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("setName", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getEmail").getModifiers()));
            assertEquals(String.class, User.class.getDeclaredMethod("getEmail").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("setEmail", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the email attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getBirthDate").getModifiers()));
            assertEquals(LocalDate.class, User.class.getDeclaredMethod("getBirthDate").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("setBirthDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the birthDate attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getDebt").getModifiers()));
            assertEquals(double.class, User.class.getDeclaredMethod("getDebt").getReturnType());
            assertTrue(Modifier.isPrivate(User.class.getDeclaredMethod("setDebt", double.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the debt attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("isPremium").getModifiers()));
            assertEquals(boolean.class, User.class.getDeclaredMethod("isPremium").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("subscribe").getModifiers()));
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("unsubscribe").getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the premium attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getGender").getModifiers()));
            assertEquals(Gender.class, User.class.getDeclaredMethod("getGender").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("setGender", Gender.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the gender attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("getAddress").getModifiers()));
            assertEquals(Address.class, User.class.getDeclaredMethod("getAddress").getReturnType());
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("setAddress", Address.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the address attribute");
            e.printStackTrace();
        }

        // equals & toString methods
        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertEquals(boolean.class, User.class.getDeclaredMethod("equals", Object.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of the equals method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(User.class.getDeclaredMethod("toString").getModifiers()));
            assertEquals(String.class, User.class.getDeclaredMethod("toString").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of the equals method");
            e.printStackTrace();
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (UserException) - Methods definition")
    void checkUserExceptionMethodsSanity() {
        //check constructors
        assertEquals(2, UserException.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(UserException.class.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(UserException.class.getDeclaredConstructor(String.class).getModifiers()));


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("There is some problem with the definition of the constructors");
        }

    }

}
