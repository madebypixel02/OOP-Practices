package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.OrderItemException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

public class Order implements Billable {
    private UUID id;
    private LocalDate orderDate;
    private LocalDate deliveryDate = null;
    public static final int MAX_ORDER_ITEMS = 100;
    private OrderItem[] orderItems = new OrderItem[100];
    private User user;
    public Order(User user, LocalDate orderDate) throws OrderException {
        setUser(user);
        setOrderDate(orderDate);
        setId();
    }
    public UUID getId() {
        return id;
    }
    private void setId() {
        this.id = UUID.randomUUID();
    }
    public User getUser() {
        return user;
    }
    private void setUser(User user) throws OrderException {
        if (user == null)
            throw new OrderException(OrderException.ERR_NULL_USER);
        this.user = user;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) throws OrderException {
        if (deliveryDate.isBefore(this.getOrderDate()))
            throw new OrderException(OrderException.ERR_WRONG_DELIVERY_DATE);
        this.deliveryDate = deliveryDate;
    }
    public double getTotalPrice() {
        double totalPrice;

        totalPrice = 0;
        for (OrderItem item : orderItems) {
            if (item != null)
                totalPrice += item.getTotalPrice();
        }
        return (totalPrice);
    }
    public OrderItem[] getOrderItems() {
        return this.orderItems;
    }
    private int getOrderItemIndex(Product product) {
        for (int i = 0; i < orderItems.length; i++) {
            if (orderItems[i] != null && orderItems[i].getProduct().equals(product))
                return (i);
        }
        return (-1);
    }
    public void addOrderItem(Product product, int quantity) throws OrderItemException, OrderException {
        int orderIndex;

        orderIndex = getOrderItemIndex(product);
        if (orderIndex != -1) {
            orderItems[orderIndex].setQuantity(orderItems[orderIndex].getQuantity() + quantity);
            return;
        }
        for (int i = 0; i < orderItems.length; i++) {
            if (orderItems[i] == null) {
                orderItems[i] = new OrderItem(new Order(getUser(), LocalDate.now()), product, quantity);
                return;
            }
        }
    }
    public void removeOrderItem(Product product, int quantity) {
        int orderIndex;

        orderIndex = getOrderItemIndex(product);
        if (orderIndex != -1) {
            if (orderItems[orderIndex].getQuantity() - quantity <= 0)
                orderItems[orderIndex] = null;
            else
                orderItems[orderIndex].setQuantity(orderItems[orderIndex].getQuantity() - quantity);
        }
    }
    public String bill() {
        int i;
        StringBuilder bill;

        bill = new StringBuilder();
        i = 0;
        for (OrderItem item : orderItems) {
            if (item != null) {
                bill.append("#").append(i + 1).append(": ").append(item.bill()).append("\n");
                i++;
            }
        }
        bill.append("TOTAL = ").append(String.format("%.1f", getTotalPrice())).append(" | Tax: ").append(new DecimalFormat("#.##").format(taxValue(getTotalPrice())));
        return (bill.toString());
    }
    public int compareTo(Object order) throws NullPointerException {
        int compareDate;

        if (order == null)
            throw new NullPointerException();
        compareDate = orderDate.compareTo(((Order)order).getOrderDate());
        if (compareDate == 0) {
            if (getTotalPrice() > ((Order)order).getTotalPrice())
                return (1);
            if (getTotalPrice() < ((Order)order).getTotalPrice())
                return (-1);
        }
        return (compareDate);
    }
}
