// com/guvi/marketplace/util/ValidationUtil.java
package com.guvi.marketplace.util;

public class ValidationUtil {
    public static void require(boolean condition, String message) throws com.guvi.marketplace.exception.ValidationException {
        if (!condition) throw new com.guvi.marketplace.exception.ValidationException(message);
    }

    public static boolean isEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
}
