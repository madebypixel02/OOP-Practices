package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DigitalBookTest {

    DigitalBook digitalBook;

    @BeforeEach
    public void initDigitalBook() {
        try {
            digitalBook = new DigitalBook("Romeo and Juliet", 5.99);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with initDigitalBook");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Romeo and Juliet", digitalBook.getName());
        assertEquals(5.99, digitalBook.getPrice());
        assertEquals(0, digitalBook.getSoldUnits());
    }

    @Test
    public void testDescribeProduct() {
        assertEquals("DigitalBook: A digital book delivered in e-book format", digitalBook.describeProduct());
    }

}
