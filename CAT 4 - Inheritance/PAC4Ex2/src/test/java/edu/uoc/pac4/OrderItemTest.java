package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.OrderItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    Order order;
    Product p1;
    Product p2;
    Product p3;
    OrderItem orderItem1;
    OrderItem orderItem2;
    OrderItem orderItem3;

    @BeforeEach
    public void orderItemSetUp() {
        try {
            LocalDate birthDate = LocalDate.of(1995, 6, 13);
            User u = new User("Quim Motger", "jmotger@uoc.edu", birthDate, Gender.MALE);

            LocalDate orderDate = LocalDate.now();
            order = new Order(u, orderDate);

            p1 = new DigitalBook("Dungeons and Dragons - Game Master Book (e-book)", 9.0);
            p2 = new PrintedBook("Dungeons and Dragons - Game Master Book", 35.0, 9.5);
            p3 = new Merchandising("Dice set (6 units)", 6.9, 1.9, 0.2);

            orderItem1 = new OrderItem(order, p1, 2);
            orderItem2 = new OrderItem(order, p2, 3);
            orderItem3 = new OrderItem(order, p3, 5);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was some error with the orderItem constructors");
        }
    }

    @Test
    public void testConstructors() {
        assertEquals(order, orderItem1.getOrder());
        assertEquals(order, orderItem2.getOrder());
        assertEquals(order, orderItem3.getOrder());

        assertEquals(p1, orderItem1.getProduct());
        assertEquals(p2, orderItem2.getProduct());
        assertEquals(p3, orderItem3.getProduct());

        assertEquals(2, orderItem1.getQuantity());
        assertEquals(3, orderItem2.getQuantity());
        assertEquals(5, orderItem3.getQuantity());

        assertEquals(18.0, orderItem1.getTotalPrice());
        assertEquals(105.0, orderItem2.getTotalPrice());
        assertEquals(34.5, orderItem3.getTotalPrice());

        Exception ex = assertThrows(OrderItemException.class, () -> new OrderItem(null, p1, 5));
        assertEquals("[ERROR] The order cannot be null", ex.getMessage());

        ex = assertThrows(OrderItemException.class, () -> new OrderItem(order, null, 5));
        assertEquals("[ERROR] The product cannot be null", ex.getMessage());
    }

    @Test
    public void testSetQuantity() {
        try {
            orderItem1.setQuantity(1);
            assertEquals(1, orderItem1.getQuantity());
            assertEquals(9.0, orderItem1.getTotalPrice());

            orderItem2.setQuantity(2);
            assertEquals(2, orderItem2.getQuantity());
            assertEquals(70.0, orderItem2.getTotalPrice());

            orderItem3.setQuantity(20);
            assertEquals(20, orderItem3.getQuantity());
            assertEquals(138.0, orderItem3.getTotalPrice());

            orderItem1.setQuantity(0);
            assertEquals(1, orderItem1.getQuantity());

            orderItem1.setQuantity(-23);
            assertEquals(1, orderItem1.getQuantity());

        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with testSetQuantity");
        }
    }

    @Test
    public void testBill() {
        try {
            assertEquals("Product: Dungeons and Dragons - Game Master Book (e-book) | Quantity: 2 | Price: 18.0 | Tax: 3.12", orderItem1.bill());
            assertEquals("Product: Dungeons and Dragons - Game Master Book | Quantity: 3 | Price: 105.0 | Tax: 18.22", orderItem2.bill());
            assertEquals("Product: Dice set (6 units) | Quantity: 5 | Price: 34.5 | Tax: 5.99", orderItem3.bill());

            orderItem1.getProduct().setPrice(8.0);
            orderItem1.setQuantity(5);
            assertEquals("Product: Dungeons and Dragons - Game Master Book (e-book) | Quantity: 5 | Price: 40.0 | Tax: 6.94", orderItem1.bill());

            orderItem2.getProduct().setPrice(41.0);
            orderItem2.setQuantity(1);
            assertEquals("Product: Dungeons and Dragons - Game Master Book | Quantity: 1 | Price: 41.0 | Tax: 7.12", orderItem2.bill());

            orderItem3.getProduct().setPrice(5.9);
            orderItem3.setQuantity(9);
            assertEquals("Product: Dice set (6 units) | Quantity: 9 | Price: 53.1 | Tax: 9.22", orderItem3.bill());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with testBill");
        }

    }

}
