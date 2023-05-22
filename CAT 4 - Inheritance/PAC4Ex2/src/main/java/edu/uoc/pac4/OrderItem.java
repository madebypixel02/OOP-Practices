package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderItemException;

public class OrderItem implements Billable {
    private int quantity;
    private Product product;
    private Order order;
    public OrderItem(Order order, Product product, int quantity) throws OrderItemException {
        setOrder(order);
        setProduct(product);
        setQuantity(quantity);
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = Math.max(1, quantity);
    }
    public double getTotalPrice() {
        return (quantity * product.getPrice());
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) throws OrderItemException {
        if (order == null)
            throw new OrderItemException(OrderItemException.ERR_NULL_ORDER);
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    private void setProduct(Product product) throws OrderItemException {
        if (product == null)
            throw new OrderItemException(OrderItemException.ERR_NULL_PRODUCT);
        this.product = product;
    }
    public String bill() {
        return ("Product: " + getProduct().getName() + " | Quantity: " + getQuantity() + " | Price: " + String.format("%.1f", getTotalPrice()) + " | Tax: " + String.format("%.2f", taxValue(getTotalPrice())));
    }

}
