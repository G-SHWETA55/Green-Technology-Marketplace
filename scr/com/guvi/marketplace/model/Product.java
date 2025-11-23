// com/guvi/marketplace/model/Product.java
package com.guvi.marketplace.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private int vendorId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean approved;
    private LocalDateTime createdAt;
    // Getters/setters
}
