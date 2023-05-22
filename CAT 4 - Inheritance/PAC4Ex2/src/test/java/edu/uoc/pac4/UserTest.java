package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User user;
    Order order1;
    Order order2;
    LocalDate orderDate1;
    LocalDate orderDate2;

    @BeforeEach
    public void orderItemSetUp() {
        try {
            LocalDate birthDate = LocalDate.of(1995, 6, 13);
            user = new User("Quim Motger", "jmotger@uoc.edu", birthDate, Gender.MALE);

            orderDate1 = LocalDate.now();
            orderDate2 = LocalDate.of(2023, 4, 1);
            order1 = new Order(user, orderDate1);
            order2 = new Order(user, orderDate2);

        } catch (Exception e) {
            e.printStackTrace();
            fail("There was some error with the orderItem constructors");
        }
    }

    @Test
    public void testGetOrders() {
        assertEquals(1000, user.getOrders().length);
        for (int i = 0; i < user.getOrders().length; ++i) {
            assertNull(user.getOrders()[i]);
        }
    }

    @Test
    public void testAddOrders() {
        assertTrue(user.addOrder(order1));

        assertEquals(1000, user.getOrders().length);
        assertNotNull(user.getOrders()[0]);
        assertEquals(user, user.getOrders()[0].getUser());
        assertEquals(orderDate1, user.getOrders()[0].getOrderDate());
        assertEquals(0, user.getOrders()[0].getTotalPrice());

        for (int i = 1; i < user.getOrders().length; ++i) {
            assertNull(user.getOrders()[i]);
        }

        for (int i = 1; i < user.getOrders().length; ++i) {
            assertTrue(user.addOrder(order2));
        }

        assertFalse(user.addOrder(order1));
        assertFalse(user.addOrder(order2));

    }

}
