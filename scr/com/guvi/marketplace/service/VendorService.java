// com/guvi/marketplace/service/VendorService.java
package com.guvi.marketplace.service;

import com.guvi.marketplace.dao.ProductDAO;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class VendorService {
    private final ProductDAO productDAO = new ProductDAO();

    public int listProduct(int vendorId, String name, String desc, BigDecimal price) throws AppException {
        if (price.signum() <= 0) throw new com.guvi.marketplace.exception.ValidationException("Price must be positive");
        Product p = new Product();
        p.setVendorId(vendorId);
        p.setName(name);
        p.setDescription(desc);
        p.setPrice(price);
        return productDAO.create(p);
    }

    public List<Product> approvedProducts() throws AppException {
        return productDAO.listApproved();
    }
}
