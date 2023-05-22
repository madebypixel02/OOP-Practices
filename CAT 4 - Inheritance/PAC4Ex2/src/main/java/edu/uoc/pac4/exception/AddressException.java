package edu.uoc.pac4.exception;

public class AddressException extends Exception {
    public static final String ERR_STREET_NUMBER = "[ERROR] Street number must be greater than zero";
    public static final String ERR_INVALID_ZIPCODE = "[ERROR] The zipcode is not alphanumerical";
    public AddressException() {
        super();
    }
    public AddressException(String msg) {
        super(msg);
    }
}
