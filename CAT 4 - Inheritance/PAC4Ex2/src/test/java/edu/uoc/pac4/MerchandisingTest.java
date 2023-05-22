package edu.uoc.pac4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MerchandisingTest {

    Merchandising merchandising;

    @BeforeEach
    public void initMerchandising() {
        try {
            merchandising = new Merchandising("Romeo's Funko Pop", 14.0, 5.6, 0.4);
        } catch (Exception e) {
            e.printStackTrace();
            fail("There was something wrong with initMerchandising");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Romeo's Funko Pop", merchandising.getName());
        assertEquals(14.0, merchandising.getPrice());
        assertEquals(5.6, merchandising.getFabricationCost());
        assertEquals(0.4, merchandising.getPackagingCost());
        assertEquals(0, merchandising.getSoldUnits());
    }

    @Test
    public void testDescribeProduct() {
        assertEquals("Merchandising (MANUFACTURED): A merchandising item", merchandising.describeProduct());
    }

    @Test
    public void testSetFabricationCost() {
        merchandising.setFabricationCost(6.4);
        assertEquals(6.4, merchandising.getFabricationCost());
    }

    @Test
    public void testSetPackagingCost() {
        merchandising.setPackagingCost(0.50);
        assertEquals(0.50, merchandising.getPackagingCost());
    }

    @Test
    public void auditBenefits() {
        assertEquals(0, merchandising.auditBenefits());

        merchandising.addSoldUnits(1);

        assertEquals(8.0, merchandising.auditBenefits());

        merchandising.addSoldUnits(1);

        assertEquals(16.0, merchandising.auditBenefits());

        merchandising.addSoldUnits(98);

        assertEquals(800.0, merchandising.auditBenefits());

    }
    
}
