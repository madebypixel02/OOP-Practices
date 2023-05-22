package edu.uoc.pac4;

import edu.uoc.pac4.exception.ProductException;

public abstract class Product {
    private String name;
    private double price;
    private int soldUnits = 0;
    public Product(String name, double price) throws ProductException {
        setName(name);
        setPrice(price);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) throws ProductException {
        if (name == null || name.length() == 0)
            throw new ProductException(ProductException.ERR_WRONG_NAME);
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) throws ProductException {
        if (price <= 0)
            throw new ProductException(ProductException.ERR_WRONG_PRICE);
        this.price = price;
    }
    public int getSoldUnits() {
        return soldUnits;
    }
    private void setSoldUnits(int soldUnits) {
        this.soldUnits = soldUnits;
    }
    public void addSoldUnits(int units) {
        setSoldUnits(getSoldUnits() + units);
    }
    public abstract String describeProduct();

    @Override
    public boolean equals(Object obj) {
        return ((this == obj) || (this.getClass() == obj.getClass() && this.name.equals(((Product) obj).name)));
    }
}
