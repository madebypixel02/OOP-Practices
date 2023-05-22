package edu.uoc.pac4.exception;

public class UserException extends Exception {
    public static final String ERR_INVALID_EMAIL = "[ERROR] The email is not in a valid format";
    public static final String ERR_MIN_AGE = "[ERROR] The user must be at least 16 years old";
    public static final String ERR_PREMIUM_SUBSCRIPTION = "[ERROR] The user cannot be subscribed as premium if he/she is in debt";
    public static final String ERR_ADD_INVALID_DEBT_VALUE = "[ERROR] The added debt value must be greater than zero";
    public UserException() {
        super();
    }
    public UserException(String msg) {
        super(msg);
    }
}
