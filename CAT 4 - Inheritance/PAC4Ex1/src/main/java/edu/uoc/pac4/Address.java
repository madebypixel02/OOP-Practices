package edu.uoc.pac4;

import edu.uoc.pac4.exception.AddressException;

import java.util.regex.Pattern;

public class Address {
    private String street;
    private int number;
    private String zipCode;
    private String city;
    public Address(String street, int number, String zipCode, String city) throws AddressException {
        this.setStreet(street);
        this.setNumber(number);
        this.setZipCode(zipCode);
        this.setCity(city);
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) throws AddressException {
        if (number < 1)
            throw new AddressException(AddressException.ERR_STREET_NUMBER);
        this.number = number;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    public void setZipCode(String zipCode) throws AddressException {
        if (!zipCode.matches("[a-zA-Z0-9]+"))
            throw new AddressException(AddressException.ERR_INVALID_ZIPCODE);
        this.zipCode = zipCode;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public boolean isInternational() {
        return (this.getZipCode().length() != 5 || !this.getZipCode().matches("[0-9]+"));
    }

    @Override
    public boolean equals(Object obj) {
        Address a;

        if (this.getClass() != obj.getClass())
            return (false);
        a = (Address) obj;
        return (this == obj || (this.street.equals(a.getStreet()) && this.number == a.getNumber() && this.zipCode.equals(a.getZipCode())));
    }
    @Override
    public String toString() {
        return (this.getStreet() + ", " + this.getNumber() + ", " + this.getCity() + " (" + this.getZipCode() + ")");
    }
}