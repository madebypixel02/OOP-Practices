package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PrintedBookTest {

    PrintedBook printedBook;

    @BeforeEach
    public void initPrintedBook() {
        try {
            printedBook = new PrintedBook("Romeo and Juliet", 20.0, 4.9);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with initPrintedBook");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Romeo and Juliet", printedBook.getName());
        assertEquals(20.0, printedBook.getPrice());
        assertEquals(4.9, printedBook.getPrintingCost());
        assertEquals(0, printedBook.getSoldUnits());
    }

    @Test
    public void testDescribeProduct() {
        assertEquals("PrintedBook (MANUFACTURED): A book printed by an editorial", printedBook.describeProduct());
    }

    @Test
    public void testSetPrintingCost() {
        printedBook.setPrintingCost(5.99);
        assertEquals(5.99, printedBook.getPrintingCost());
    }

    @Test
    public void auditBenefits() {
        assertEquals(0, printedBook.auditBenefits());

        printedBook.addSoldUnits(1);

        assertEquals(13.59, printedBook.auditBenefits());

        printedBook.addSoldUnits(1);

        assertEquals(27.18, printedBook.auditBenefits());

        printedBook.addSoldUnits(98);

        assertEquals(1359, printedBook.auditBenefits());

    }
    
}
