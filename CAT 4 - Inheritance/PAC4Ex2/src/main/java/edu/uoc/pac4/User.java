package edu.uoc.pac4;

import edu.uoc.pac4.exception.AddressException;
import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.UserException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class User {
    private String name;
    private String email;
    private LocalDate birthDate;
    private double debt = 0;
    private boolean premium = false;
    private Address address;
    private Gender gender;
    public static final int MAX_ORDERS = 1000;
    private Order[] orders = new Order[MAX_ORDERS];
    public User(String name, String email, LocalDate birthDate, Gender gender) throws UserException {
        this.setName(name);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setGender(gender);
    }
    public User(String name, String email, LocalDate birthDate, Gender gender, Address address) throws UserException, AddressException {
        this.setName(name);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setGender(gender);
        this.setAddress(address);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) throws UserException {
        if (email.indexOf('@') == -1 || email.indexOf('@') != email.lastIndexOf('@') || email.indexOf('.', email.indexOf('@')) == -1 || email.indexOf('@') == 0 || email.indexOf('@') == email.length() - 1)
            throw new UserException(UserException.ERR_INVALID_EMAIL);
        this.email = email;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) throws UserException {
        if (Period.between(birthDate, LocalDate.now()).getYears() < 16)
            throw new UserException(UserException.ERR_MIN_AGE);
        this.birthDate = birthDate;
    }
    public double getDebt() {
        return debt;
    }
    private void setDebt(double debt) {
        this.debt = debt;
    }
    public void addDebt(double debt) throws UserException {
        if (debt <= 0)
            throw new UserException(UserException.ERR_ADD_INVALID_DEBT_VALUE);
        this.setDebt(this.getDebt() + debt);
    }
    public void resetDebt() {
        this.setDebt(0);
    }
    public boolean isPremium() {
        return premium;
    }
    public void subscribe() throws UserException {
        if (this.getDebt() > 0)
            throw new UserException(UserException.ERR_PREMIUM_SUBSCRIPTION);
        this.premium = true;
    }
    public void unsubscribe() {
        if (this.isPremium())
            this.premium = false;
    }
    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Address getAddress() {
        return this.address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public boolean equals(Object obj) {
        User u;

        if (this.getClass() != obj.getClass())
            return (false);
        u = (User) obj;
        return (this == obj || this.email.equals(u.getEmail()));
    }
    @Override
    public String toString() {
        String p;

        p = "N";
        if (this.isPremium())
            p = "Y";
        return ("\tName: " + this.getName() + "\n\te-mail: " + this.getEmail() + "\n\tBirth date: " + this.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\tPremium?: " + p + "\n\tAddress: " + this.getAddress().toString() + "\n");
    }
    public Order[] getOrders() {
        return this.orders;
    }
    public boolean addOrder(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = order;
                return true;
            }
        }
        return false;
    }
}
