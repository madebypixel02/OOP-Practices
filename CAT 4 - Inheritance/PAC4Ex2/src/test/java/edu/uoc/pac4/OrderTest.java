package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    User user;
    Order order1;
    Order order2;
    Product p1;
    Product p2;
    Product p3;

    @BeforeEach
    public void orderItemSetUp() {
        try {
            LocalDate birthDate = LocalDate.of(1995, 6, 13);
            user = new User("Quim Motger", "jmotger@uoc.edu", birthDate, Gender.MALE);

            LocalDate orderDate1 = LocalDate.now();
            LocalDate orderDate2 = LocalDate.of(2023, 4, 1);
            order1 = new Order(user, orderDate1);
            order2 = new Order(user, orderDate2);

            p1 = new PrintedBook("Dungeons and Dragons - Game Master Book", 35.0, 9.5);
            p2 = new Merchandising("Dice set (6 units)", 6.5, 1.9, 0.2);
            p3 = new PrintedBook("A Song of Ice and Fire - Winds of Winter", 29.5, 8.5);

        } catch (Exception e) {
            e.printStackTrace();
            fail("There was some error with the orderItem constructors");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(user, order1.getUser());
        assertEquals(user, order2.getUser());
        assertEquals(LocalDate.now(), order1.getOrderDate());
        LocalDate orderDate2 = LocalDate.of(2023, 4, 1);
        assertEquals(orderDate2, order2.getOrderDate());

        LocalDate d = LocalDate.of(2023, 04, 19);
        assertNotEquals(d, order1.getOrderDate());

        assertEquals(0, order1.getTotalPrice());
        assertEquals(0, order2.getTotalPrice());

        assertNull(order1.getDeliveryDate());
        assertNull(order2.getDeliveryDate());

        assertEquals(Order.MAX_ORDER_ITEMS, order1.getOrderItems().length);
        assertEquals(Order.MAX_ORDER_ITEMS, order2.getOrderItems().length);

        Exception ex = assertThrows(OrderException.class, () -> new Order(null, LocalDate.now()));
        assertEquals("[ERROR] The user cannot be null", ex.getMessage());
    }

    @Test
    public void testSetOrderDate() {
        LocalDate d = LocalDate.of(2022, 5, 11);
        order1.setOrderDate(d);
        assertEquals(d, order1.getOrderDate());
    }

    @Test
    public void testSetDeliveryDate() {
        try {
            LocalDate d = LocalDate.of(2023, 5, 21);
            order2.setDeliveryDate(d);
            assertEquals(d, order2.getDeliveryDate());

            LocalDate d2 = LocalDate.of(2023, 3, 21);
            Exception ex = assertThrows(OrderException.class, () -> order2.setDeliveryDate(d2));
            assertEquals("[ERROR] The order date cannot be later than the delivery date", ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something failed with testSetDeliveryDate");
        }
    }

    @Test
    public void testAddOrderItem() {
        try {
            order1.addOrderItem(p1, 1);
            assertEquals(p1, order1.getOrderItems()[0].getProduct());
            assertNull(order1.getOrderItems()[1]);
            assertEquals(1, order1.getOrderItems()[0].getQuantity());
            assertEquals(35.0, order1.getOrderItems()[0].getTotalPrice());
            assertEquals(35.0, order1.getTotalPrice());

            order1.addOrderItem(p1, 1);
            assertEquals(p1, order1.getOrderItems()[0].getProduct());
            assertNull(order1.getOrderItems()[1]);
            assertEquals(2, order1.getOrderItems()[0].getQuantity());
            assertEquals(70.0, order1.getOrderItems()[0].getTotalPrice());
            assertEquals(70.0, order1.getTotalPrice());

            order1.addOrderItem(p2, 3);
            assertEquals(p1, order1.getOrderItems()[0].getProduct());
            assertEquals(p2, order1.getOrderItems()[1].getProduct());
            assertNull(order1.getOrderItems()[2]);
            assertEquals(2, order1.getOrderItems()[0].getQuantity());
            assertEquals(3, order1.getOrderItems()[1].getQuantity());
            assertEquals(70.0, order1.getOrderItems()[0].getTotalPrice());
            assertEquals(19.5, order1.getOrderItems()[1].getTotalPrice());
            assertEquals(89.5, order1.getTotalPrice());

            order1.addOrderItem(p3, 5);
            assertEquals(p1, order1.getOrderItems()[0].getProduct());
            assertEquals(p2, order1.getOrderItems()[1].getProduct());
            assertEquals(p3, order1.getOrderItems()[2].getProduct());
            assertNull(order1.getOrderItems()[3]);
            assertEquals(2, order1.getOrderItems()[0].getQuantity());
            assertEquals(3, order1.getOrderItems()[1].getQuantity());
            assertEquals(5, order1.getOrderItems()[2].getQuantity());
            assertEquals(70.0, order1.getOrderItems()[0].getTotalPrice());
            assertEquals(19.5, order1.getOrderItems()[1].getTotalPrice());
            assertEquals(147.5, order1.getOrderItems()[2].getTotalPrice());
            assertEquals(237.0, order1.getTotalPrice());

            order1.addOrderItem(p2, 1);
            assertEquals(p1, order1.getOrderItems()[0].getProduct());
            assertEquals(p2, order1.getOrderItems()[1].getProduct());
            assertEquals(p3, order1.getOrderItems()[2].getProduct());
            assertNull(order1.getOrderItems()[3]);
            assertEquals(2, order1.getOrderItems()[0].getQuantity());
            assertEquals(4, order1.getOrderItems()[1].getQuantity());
            assertEquals(5, order1.getOrderItems()[2].getQuantity());
            assertEquals(70.0, order1.getOrderItems()[0].getTotalPrice());
            assertEquals(26.0, order1.getOrderItems()[1].getTotalPrice());
            assertEquals(147.5, order1.getOrderItems()[2].getTotalPrice());
            assertEquals(243.5, order1.getTotalPrice());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with testAddOrderItem");
        }
    }

    @Test
    public void testRemoveOrderItem() {
        try {
            order2.addOrderItem(p1, 1);
            order2.addOrderItem(p2, 3);
            order2.addOrderItem(p3, 2);

            order2.removeOrderItem(p2, 1);
            assertEquals(p1, order2.getOrderItems()[0].getProduct());
            assertEquals(p2, order2.getOrderItems()[1].getProduct());
            assertEquals(p3, order2.getOrderItems()[2].getProduct());
            assertNull(order2.getOrderItems()[3]);
            assertEquals(1, order2.getOrderItems()[0].getQuantity());
            assertEquals(2, order2.getOrderItems()[1].getQuantity());
            assertEquals(2, order2.getOrderItems()[2].getQuantity());
            assertEquals(35.0, order2.getOrderItems()[0].getTotalPrice());
            assertEquals(13.0, order2.getOrderItems()[1].getTotalPrice());
            assertEquals(59.0, order2.getOrderItems()[2].getTotalPrice());
            assertEquals(107.0, order2.getTotalPrice());

            order2.removeOrderItem(p2, 2);
            assertEquals(p1, order2.getOrderItems()[0].getProduct());
            assertNull(order2.getOrderItems()[1]);
            assertEquals(p3, order2.getOrderItems()[2].getProduct());
            assertNull(order2.getOrderItems()[3]);
            assertEquals(1, order2.getOrderItems()[0].getQuantity());
            assertEquals(2, order2.getOrderItems()[2].getQuantity());
            assertEquals(35.0, order2.getOrderItems()[0].getTotalPrice());
            assertEquals(59.0, order2.getOrderItems()[2].getTotalPrice());
            assertEquals(94.0, order2.getTotalPrice());

            order2.removeOrderItem(p2, 100);
            assertEquals(p1, order2.getOrderItems()[0].getProduct());
            assertNull(order2.getOrderItems()[1]);
            assertEquals(p3, order2.getOrderItems()[2].getProduct());
            assertNull(order2.getOrderItems()[3]);
            assertEquals(1, order2.getOrderItems()[0].getQuantity());
            assertEquals(2, order2.getOrderItems()[2].getQuantity());
            assertEquals(35.0, order2.getOrderItems()[0].getTotalPrice());
            assertEquals(59.0, order2.getOrderItems()[2].getTotalPrice());
            assertEquals(94.0, order2.getTotalPrice());

            order2.removeOrderItem(p1, 5);
            assertNull(order2.getOrderItems()[0]);
            assertNull(order2.getOrderItems()[1]);
            assertEquals(p3, order2.getOrderItems()[2].getProduct());
            assertNull(order2.getOrderItems()[3]);
            assertEquals(2, order2.getOrderItems()[2].getQuantity());
            assertEquals(59.0, order2.getOrderItems()[2].getTotalPrice());
            assertEquals(59.0, order2.getTotalPrice());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with testRemoveOrderItem");
        }
    }

    @Test
    public void testBill() {
        try {
            order2.addOrderItem(p1, 1);
            order2.addOrderItem(p2, 3);
            order2.addOrderItem(p3, 2);

            assertEquals("#1: Product: Dungeons and Dragons - Game Master Book | Quantity: 1 | Price: 35.0 | Tax: 6.07" + System.lineSeparator() +
                    "#2: Product: Dice set (6 units) | Quantity: 3 | Price: 19.5 | Tax: 3.38" + System.lineSeparator() +
                    "#3: Product: A Song of Ice and Fire - Winds of Winter | Quantity: 2 | Price: 59.0 | Tax: 10.24" + System.lineSeparator() +
                    "TOTAL = 113.5 | Tax: 19.7", order2.bill());

            order2.addOrderItem(p3, 5);

            assertEquals("#1: Product: Dungeons and Dragons - Game Master Book | Quantity: 1 | Price: 35.0 | Tax: 6.07" + System.lineSeparator() +
                    "#2: Product: Dice set (6 units) | Quantity: 3 | Price: 19.5 | Tax: 3.38" + System.lineSeparator() +
                    "#3: Product: A Song of Ice and Fire - Winds of Winter | Quantity: 7 | Price: 206.5 | Tax: 35.84" + System.lineSeparator() +
                    "TOTAL = 261.0 | Tax: 45.3", order2.bill());

            order2.removeOrderItem(p3, 7);

            assertEquals("#1: Product: Dungeons and Dragons - Game Master Book | Quantity: 1 | Price: 35.0 | Tax: 6.07" + System.lineSeparator() +
                    "#2: Product: Dice set (6 units) | Quantity: 3 | Price: 19.5 | Tax: 3.38" + System.lineSeparator() +
                    "TOTAL = 54.5 | Tax: 9.46", order2.bill());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with testBill");
        }
    }

    @Test
    public void testCompareTo(){
        assertTrue(order1.compareTo(order2) > 0);
        assertTrue(order2.compareTo(order1) < 0);
        assertTrue(order1.compareTo(order1) == 0);
        assertThrows(NullPointerException.class, () -> order1.compareTo(null));

        try{
            LocalDate d = LocalDate.of(2023, 3, 31);
            order1.setOrderDate(d);
            assertTrue(order1.compareTo(order2) < 0);

            order2.setOrderDate(d);
            assertTrue(order1.compareTo(order2) == 0);

            order1.addOrderItem(p1, 1);
            assertTrue(order1.compareTo(order2) > 0);

            order2.addOrderItem(p1, 2);
            assertTrue(order1.compareTo(order2) < 0);


        }catch(Exception e){
            fail("compareTo failed");
        }

    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (Order) - Fields definition")
    void checkUserFieldsSanity() {
        //check attribute fields
        assertEquals(6, Order.class.getDeclaredFields().length);
        try {
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredField("orderDate").getModifiers()));
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredField("deliveryDate").getModifiers()));
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredField("user").getModifiers()));
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredField("orderItems").getModifiers()));
            assertTrue(Modifier.isPublic(Order.class.getDeclaredField("MAX_ORDER_ITEMS").getModifiers()));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (OrderException) - Fields definition")
    void checkUserExceptionFieldsSanity() {
        //check attribute fields
        assertEquals(2, OrderException.class.getDeclaredFields().length);
        try {

            assertTrue(Modifier.isPublic(OrderException.class.getDeclaredField("ERR_NULL_USER").getModifiers()));
            assertTrue(Modifier.isStatic(OrderException.class.getDeclaredField("ERR_NULL_USER").getModifiers()));
            assertTrue(Modifier.isFinal(OrderException.class.getDeclaredField("ERR_NULL_USER").getModifiers()));

            assertTrue(Modifier.isPublic(OrderException.class.getDeclaredField("ERR_WRONG_DELIVERY_DATE").getModifiers()));
            assertTrue(Modifier.isStatic(OrderException.class.getDeclaredField("ERR_WRONG_DELIVERY_DATE").getModifiers()));
            assertTrue(Modifier.isFinal(OrderException.class.getDeclaredField("ERR_WRONG_DELIVERY_DATE").getModifiers()));


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("[ERROR] There is some problem with the definition of the attributes");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity (Order) - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(1, Order.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Order.class.getDeclaredConstructor(User.class, LocalDate.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("There is some problem with the definition of the constructor");
        }

        //check methods, parameters and return types

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getId").getModifiers()));
            assertEquals(UUID.class, Order.class.getDeclaredMethod("getId").getReturnType());
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredMethod("setId").getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the id attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getUser").getModifiers()));
            assertEquals(User.class, Order.class.getDeclaredMethod("getUser").getReturnType());
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredMethod("setUser", User.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the user attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getOrderDate").getModifiers()));
            assertEquals(LocalDate.class, Order.class.getDeclaredMethod("getOrderDate").getReturnType());
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("setOrderDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the orderDate attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getDeliveryDate").getModifiers()));
            assertEquals(LocalDate.class, Order.class.getDeclaredMethod("getDeliveryDate").getReturnType());
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("setDeliveryDate", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the deliveryDate attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getTotalPrice").getModifiers()));
            assertEquals(double.class, Order.class.getDeclaredMethod("getTotalPrice").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the get total price method");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("getOrderItems").getModifiers()));
            assertEquals(OrderItem[].class, Order.class.getDeclaredMethod("getOrderItems").getReturnType());
            assertTrue(Modifier.isPrivate(Order.class.getDeclaredMethod("getOrderItemIndex", Product.class).getModifiers()));
            assertEquals(int.class, Order.class.getDeclaredMethod("getOrderItemIndex", Product.class).getReturnType());

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the orderItems attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Order.class.getDeclaredMethod("compareTo", Object.class).getModifiers()));
            assertEquals(int.class, Order.class.getDeclaredMethod("compareTo", Object.class).getReturnType());
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

}
