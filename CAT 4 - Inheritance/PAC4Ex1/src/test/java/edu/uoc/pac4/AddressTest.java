package edu.uoc.pac4;

import edu.uoc.pac4.exception.AddressException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    Address address;

    @BeforeEach
    void initializeAddress() {
        try {
            address = new Address("C/ Diputació", 214, "08034", "BARCELONA");
        } catch (Exception e) {
            fail("initializeAddress failed");
            e.printStackTrace();
        }
    }

    @Test
    void testAddress() {
        try {
            assertEquals("C/ Diputació", address.getStreet());
            assertEquals(214, address.getNumber());
            assertEquals("08034", address.getZipCode());
            assertEquals("BARCELONA", address.getCity());
        } catch (Exception e) {
            fail("testAddress failed");
            e.printStackTrace();
        }

    }

    @Test
    void testSetStreet() {
        try {
            address.setStreet("C/ Aragó");
            assertEquals("C/ Aragó", address.getStreet());

            address.setStreet("Av. Meridiana");
            assertEquals("Av. Meridiana", address.getStreet());

            address.setStreet("Av. Mare de Déu de la Verge del Puig");
            assertEquals("Av. Mare de Déu de la Verge del Puig", address.getStreet());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetStreet failed");
        }

    }

    @Test
    void testSetNumber() {
        try {
            address.setNumber(1);
            assertEquals(1, address.getNumber());

            address.setNumber(1044);
            assertEquals(1044, address.getNumber());

            Exception ex = assertThrows(Exception.class, () -> address.setNumber(0));
            assertEquals("[ERROR] Street number must be greater than zero", ex.getMessage());

            ex = assertThrows(Exception.class, () -> address.setNumber(-1));
            assertEquals("[ERROR] Street number must be greater than zero", ex.getMessage());

            ex = assertThrows(Exception.class, () -> address.setNumber(-132));
            assertEquals("[ERROR] Street number must be greater than zero", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testSetNumber failed");
        }
    }

    @Test
    void testSetCity() {
        try {
            address.setCity("Girona");
            assertEquals("Girona", address.getCity());

            address.setCity("LLEIDA");
            assertEquals("LLEIDA", address.getCity());

            address.setCity("Washington D.C.");
            assertEquals("Washington D.C.", address.getCity());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddress failed");
        }

    }

    @Test
    void testSetZipCode() {
        try {
            address.setZipCode("43700");
            assertEquals("43700", address.getZipCode());

            address.setZipCode("27540");
            assertEquals("27540", address.getZipCode());

            address.setZipCode("NC60451");
            assertEquals("NC60451", address.getZipCode());

            address.setZipCode("IL27540");
            assertEquals("IL27540", address.getZipCode());

            address.setZipCode("il27540");
            assertEquals("il27540", address.getZipCode());

            address.setZipCode("00023948");
            assertEquals("00023948", address.getZipCode());

            address.setZipCode("397");
            assertEquals("397", address.getZipCode());

            Exception ex = assertThrows(Exception.class, () -> address.setZipCode("NC-60451"));
            assertEquals("[ERROR] The zipcode is not alphanumerical", ex.getMessage());

            ex = assertThrows(Exception.class, () -> address.setZipCode("NC_60451"));
            assertEquals("[ERROR] The zipcode is not alphanumerical", ex.getMessage());

            ex = assertThrows(Exception.class, () -> address.setZipCode("NC 60451"));
            assertEquals("[ERROR] The zipcode is not alphanumerical", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddress failed");
        }

    }

    @Test
    void testIsInternational() {
        try {
            address.setZipCode("43700");
            assertFalse(address.isInternational());

            address.setZipCode("27540");
            assertFalse(address.isInternational());

            address.setZipCode("NC60451");
            assertTrue(address.isInternational());

            address.setZipCode("IL27540");
            assertTrue(address.isInternational());

            address.setZipCode("il27540");
            assertTrue(address.isInternational());

            address.setZipCode("00023948");
            assertTrue(address.isInternational());

            address.setZipCode("397");
            assertTrue(address.isInternational());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddress failed");
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (Address) - Fields definition")
    void checkAddressFieldsSanity() {
        //check attribute fields
        assertEquals(4, Address.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(Address.class.getDeclaredField("street").getModifiers()));
            assertTrue(Modifier.isPrivate(Address.class.getDeclaredField("number").getModifiers()));
            assertTrue(Modifier.isPrivate(Address.class.getDeclaredField("zipCode").getModifiers()));
            assertTrue(Modifier.isPrivate(Address.class.getDeclaredField("city").getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (AddressException) - Fields definition")
    void checkAddressExceptionFieldsSanity() {
        //check attribute fields
        assertEquals(2, AddressException.class.getDeclaredFields().length);
        try {

            assertTrue(Modifier.isPublic(AddressException.class.getDeclaredField("ERR_STREET_NUMBER").getModifiers()));
            assertTrue(Modifier.isStatic(AddressException.class.getDeclaredField("ERR_STREET_NUMBER").getModifiers()));
            assertTrue(Modifier.isFinal(AddressException.class.getDeclaredField("ERR_STREET_NUMBER").getModifiers()));

            assertTrue(Modifier.isPublic(AddressException.class.getDeclaredField("ERR_INVALID_ZIPCODE").getModifiers()));
            assertTrue(Modifier.isStatic(AddressException.class.getDeclaredField("ERR_INVALID_ZIPCODE").getModifiers()));
            assertTrue(Modifier.isFinal(AddressException.class.getDeclaredField("ERR_INVALID_ZIPCODE").getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (Address) - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, Address.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Address.class.getDeclaredConstructor(String.class, int.class, String.class, String.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of the constructor");
            e.printStackTrace();
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("getStreet").getModifiers()));
            assertEquals(String.class, Address.class.getDeclaredMethod("getStreet").getReturnType());
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("setStreet", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the street attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("getNumber").getModifiers()));
            assertEquals(int.class, Address.class.getDeclaredMethod("getNumber").getReturnType());
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("setNumber", int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the number attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("getZipCode").getModifiers()));
            assertEquals(String.class, Address.class.getDeclaredMethod("getZipCode").getReturnType());
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("setZipCode", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the zipCode attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("getCity").getModifiers()));
            assertEquals(String.class, Address.class.getDeclaredMethod("getCity").getReturnType());
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("setCity", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the city attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("isInternational").getModifiers()));
            assertEquals(boolean.class, Address.class.getDeclaredMethod("isInternational").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the international attribute");
            e.printStackTrace();
        }

        // equals & toString methods
        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("equals", Object.class).getModifiers()));
            assertEquals(boolean.class, Address.class.getDeclaredMethod("equals", Object.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of the equals method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Address.class.getDeclaredMethod("toString").getModifiers()));
            assertEquals(String.class, Address.class.getDeclaredMethod("toString").getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the equals method");
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (AddressException) - Methods definition")
    void checkAddressExceptionMethodsSanity() {
        //check constructors
        assertEquals(2, AddressException.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(AddressException.class.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(AddressException.class.getDeclaredConstructor(String.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("There is some problem with the definition of the constructors");
        }

    }

}
