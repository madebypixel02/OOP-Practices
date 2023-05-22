package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EqualsToStringTest {

    User user1;
    User user2;
    Address address1;
    Address address2;

    @BeforeEach
    void initializeUser() {
        try {

            LocalDate birthDate = LocalDate.of(1964, 1, 12);
            address1 = new Address("Royal Avenue", 1123, "NY12044", "New York");
            address2 = new Address("Major Street", 75, "IL10241", "Illinois");

            user1 = new User("Jeff Bezos", "jeffbezzos@amazon.com", birthDate, Gender.MALE, address1);
            user2 = new User("Joffrey B.", "jeffbezzos@amazon.com", birthDate, Gender.MALE, address2);
            user2.subscribe();

        } catch (Exception e) {
            e.printStackTrace();
            fail("initializeUser failed");
        }
    }

    @Test
    void testAddressEquals() {
        try {
            assertNotEquals(address1, address2);
            assertEquals(address1, address1);
            assertNotEquals(address2, address1);
            assertEquals(address2, address2);

            address1.setStreet("Major Street");
            address1.setNumber(75);
            assertNotEquals(address1, address2);
            assertEquals(address1, address1);
            assertNotEquals(address2, address1);
            assertEquals(address2, address2);

            address1.setZipCode("IL10241");
            assertEquals(address1, address2);
            assertEquals(address1, address1);
            assertEquals(address2, address1);
            assertEquals(address2, address2);


        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddressEquals failed");
        }
    }

    @Test
    void testUserEquals() {
        try {
            assertEquals(user1, user2);
            assertEquals(user2, user1);
            assertEquals(user1, user1);
            assertEquals(user2, user2);

            user2.setAddress(address2);
            assertEquals(user1, user2);
            assertEquals(user2, user1);
            assertEquals(user1, user1);
            assertEquals(user2, user2);

            user2.setEmail("jeffbezzos@amazon.ai");
            assertNotEquals(user1, user2);
            assertNotEquals(user2, user1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("testUserEquals failed");
        }
    }

    @Test
    void testAddressToString() {
        try {
            assertEquals("Royal Avenue, 1123, New York (NY12044)", address1.toString());
            assertEquals("Major Street, 75, Illinois (IL10241)", address2.toString());
            address1.setNumber(754);
            address1.setStreet("5th Avenue");
            address2.setZipCode("IL18490");
            assertEquals("5th Avenue, 754, New York (NY12044)", address1.toString());
            assertEquals("Major Street, 75, Illinois (IL18490)", address2.toString());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testAddressToString failed");
        }
    }

    @Test
    void testUserToString() {
        try {
            assertEquals("\tName: Jeff Bezos" + System.lineSeparator() +
                    "\te-mail: jeffbezzos@amazon.com" + System.lineSeparator() +
                    "\tBirth date: 12/01/1964" + System.lineSeparator() +
                    "\tPremium?: N" + System.lineSeparator() +
                    "\tAddress: Royal Avenue, 1123, New York (NY12044)" +  System.lineSeparator(), user1.toString());
            assertEquals("\tName: Joffrey B." + System.lineSeparator() +
                    "\te-mail: jeffbezzos@amazon.com" + System.lineSeparator() +
                    "\tBirth date: 12/01/1964" + System.lineSeparator() +
                    "\tPremium?: Y" + System.lineSeparator() +
                    "\tAddress: Major Street, 75, Illinois (IL10241)" +  System.lineSeparator(), user2.toString());

            user2.setName("Joffrey Bezos Junior");
            user2.unsubscribe();
            assertEquals("\tName: Joffrey Bezos Junior" + System.lineSeparator() +
                    "\te-mail: jeffbezzos@amazon.com" + System.lineSeparator() +
                    "\tBirth date: 12/01/1964" + System.lineSeparator() +
                    "\tPremium?: N" + System.lineSeparator() +
                    "\tAddress: Major Street, 75, Illinois (IL10241)" +  System.lineSeparator(), user2.toString());

        } catch (Exception e) {
            e.printStackTrace();
            fail("testUserToString failed");
        }
    }

}
