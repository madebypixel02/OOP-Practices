package edu.uoc.pac4.exception;

public class OrderException extends Exception {
    public static final String ERR_NULL_USER = "[ERROR] The user cannot be null";
    public static final String ERR_WRONG_DELIVERY_DATE = "[ERROR] The order date cannot be later than the delivery date";
    public OrderException() {
        super();
    }
    public OrderException(String msg) {
        super(msg);
    }
}
