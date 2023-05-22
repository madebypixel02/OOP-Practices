package edu.uoc.pac4;

import edu.uoc.pac4.exception.ProductException;

public class Merchandising extends Product implements Manufacturable {
    private double fabricationCost;
    private double packagingCost;
    private final static String DESCRIPTION = "A merchandising item";
    public Merchandising(String name, double price, double fabricationCost, double packagingCost) throws ProductException {
        super(name, price);
        setFabricationCost(fabricationCost);
        setPackagingCost(packagingCost);
    }
    public double getFabricationCost() {
        return fabricationCost;
    }
    public void setFabricationCost(double fabricationCost) {
        this.fabricationCost = fabricationCost;
    }
    public double getPackagingCost() {
        return packagingCost;
    }
    public void setPackagingCost(double packagingCost) {
        this.packagingCost = packagingCost;
    }
    @Override
    public String describeProduct() {
        return (this.getClass().getSimpleName() + " (MANUFACTURED): " + DESCRIPTION);
    }
    public double auditBenefits() {
        return ((getPrice() - (fabricationCost + packagingCost)) * getSoldUnits());
    }
}
