package edu.uoc.pac4;

import edu.uoc.pac4.exception.ProductException;

public class DigitalBook extends Product {
    private final static String DESCRIPTION = "A digital book delivered in e-book format";
    public DigitalBook(String name, double price) throws ProductException {
        super(name, price);
    }
    @Override
    public String describeProduct() {
        return (this.getClass().getSimpleName() + ": " + DESCRIPTION);
    }
}
