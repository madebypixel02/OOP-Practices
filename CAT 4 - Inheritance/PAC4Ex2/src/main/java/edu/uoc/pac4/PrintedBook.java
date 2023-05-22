package edu.uoc.pac4;

import edu.uoc.pac4.exception.ProductException;

public class PrintedBook extends Product implements Manufacturable {
    private double printingCost;
    private final static String DESCRIPTION = "A book printed by an editorial";
    public PrintedBook(String name, double price, double printingCost) throws ProductException {
        super(name, price);
        setPrintingCost(printingCost);
    }
    public double getPrintingCost() {
        return printingCost;
    }
    public void setPrintingCost(double printingCost) {
        this.printingCost = printingCost;
    }
    @Override
    public String describeProduct() {
        return (this.getClass().getSimpleName() + " (MANUFACTURED): " + DESCRIPTION);
    }
    public double auditBenefits() {
        return ((getPrice() - printingCost) * getSoldUnits() * 0.9);
    }
}
