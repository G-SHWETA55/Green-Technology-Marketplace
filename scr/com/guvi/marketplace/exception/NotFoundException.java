// com/guvi/marketplace/exception/NotFoundException.java
package com.guvi.marketplace.exception;

public class NotFoundException extends AppException {
    public NotFoundException(String entity, Object id) {
        super(entity + " not found: " + id);
    }
}
