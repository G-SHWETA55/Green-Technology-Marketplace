// com/guvi/marketplace/model/User.java
package com.guvi.marketplace.model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private Enums.Role role;
    private boolean active;
    private LocalDateTime createdAt;

    // Getters, setters, builder-style fluent methods omitted for brevity
    // toString, equals/hashCode as needed
}
