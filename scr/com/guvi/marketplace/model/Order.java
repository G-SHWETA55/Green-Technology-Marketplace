// com/guvi/marketplace/model/Order.java
package com.guvi.marketplace.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private Enums.OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private final List<OrderItem> items = new ArrayList<>(); // Collections

    public void addItem(OrderItem item) {
        items.add(item);
        total = total.add(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
    }
    // Getters/setters
}
