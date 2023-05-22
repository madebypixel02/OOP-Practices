package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.ProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product p1;
    Product p2;
    Product p3;

    @BeforeEach
    public void initProducts() {
        try {
            p1 = new DigitalBook("Dungeons and Dragons - Game Master Book (e-book)", 9.0);
            p2 = new PrintedBook("Dungeons and Dragons - Game Master Book", 35.0, 9.5);
            p3 = new Merchandising("Dice set (6 units)", 6.9, 1.9, 0.2);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with initProducts");
        }
    }

    @Test
    public void testConstructors() {
        assertEquals("Dungeons and Dragons - Game Master Book (e-book)", p1.getName());
        assertEquals(9.0, p1.getPrice());
        assertEquals(0, p1.getSoldUnits());

        assertEquals("Dungeons and Dragons - Game Master Book", p2.getName());
        assertEquals(35.0, p2.getPrice());
        assertEquals(0, p2.getSoldUnits());

        assertEquals("Dice set (6 units)", p3.getName());
        assertEquals(6.9, p3.getPrice());
        assertEquals(0, p3.getSoldUnits());

    }

    @Test
    public void testSetName() {
        try {
            p1.setName("Dungeons and Dragons - 3rd edition");
            assertEquals("Dungeons and Dragons - 3rd edition", p1.getName());

            Exception ex = assertThrows(ProductException.class, () -> p1.setName(""));
            assertEquals("[ERROR] Product name cannot be empty or null", ex.getMessage());

            ex = assertThrows(ProductException.class, () -> p1.setName(null));
            assertEquals("[ERROR] Product name cannot be empty or null", ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with setName");
        }
    }

    @Test
    public void testSetPrice() {
        try {
            p1.setPrice(44.0);
            assertEquals(44.0, p1.getPrice());

            Exception ex = assertThrows(ProductException.class, () -> p1.setPrice(0.0));
            assertEquals("[ERROR] Price must be greater than zero", ex.getMessage());

            ex = assertThrows(ProductException.class, () -> p1.setPrice(-25.0));
            assertEquals("[ERROR] Price must be greater than zero", ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with setName");
        }
    }

    @Test
    public void testDescribeProduct() {
        assertEquals("DigitalBook: A digital book delivered in e-book format", p1.describeProduct());
        assertEquals("PrintedBook (MANUFACTURED): A book printed by an editorial", p2.describeProduct());
        assertEquals("Merchandising (MANUFACTURED): A merchandising item", p3.describeProduct());
    }

    @Test
    public void testAddSoldUnits() {
        p1.addSoldUnits(12);
        assertEquals(12, p1.getSoldUnits());

        p1.addSoldUnits(48);
        assertEquals(60, p1.getSoldUnits());

        p2.addSoldUnits(54);
        p3.addSoldUnits(90);
        p3.addSoldUnits(24);
        assertEquals(60, p1.getSoldUnits());
        assertEquals(54, p2.getSoldUnits());
        assertEquals(114, p3.getSoldUnits());
    }

    @Test
    public void testDowncasting() {
        try {
            DigitalBook digitalBook = (DigitalBook) p1;
            PrintedBook printedBook = (PrintedBook) p2;
            Merchandising merchandising = (Merchandising) p3;
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was some problem with the definition of the inheritance between Product, DigitalBook, PrintedBook and Merchandising");
        }
    }

    @Test
    public void testEquals() {
        try {
            assertNotEquals(p1, p2);
            assertNotEquals(p2, p1);
            assertNotEquals(p1, p3);
            assertNotEquals(p3, p1);
            assertNotEquals(p2, p3);
            assertNotEquals(p3, p2);

            p1.setName("Dungeons and Dragons - Game Master Book");
            assertNotEquals(p1, p2);

            Product p4 = new PrintedBook("Dungeons and Dragons - Game Master Book", 32.0, 9.0);
            assertEquals(p2, p4);
            assertNotEquals(p1, p4);

            p4.setName("Dungeons and Dragons - Game Master Book (3rd edition)");
            assertNotEquals(p2, p4);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with equals");
        }

    }

}
