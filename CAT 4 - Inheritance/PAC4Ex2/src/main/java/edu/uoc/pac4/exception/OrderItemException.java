package edu.uoc.pac4.exception;

public class OrderItemException extends Exception {
    public static final String ERR_NULL_ORDER = "[ERROR] The order cannot be null";
    public static final String ERR_NULL_PRODUCT = "[ERROR] The product cannot be null";
    public OrderItemException() {
        super();
    }
    public OrderItemException(String msg) {
        super(msg);
    }
}
