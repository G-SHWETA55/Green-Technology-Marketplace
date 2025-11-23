// com/guvi/marketplace/service/AdminService.java
package com.guvi.marketplace.service;

import com.guvi.marketplace.dao.ProductDAO;
import com.guvi.marketplace.dao.UserDAO;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Product;
import com.guvi.marketplace.model.User;

import java.util.List;

public class AdminService {
    private final UserDAO userDAO = new UserDAO();
    private final ProductDAO productDAO = new ProductDAO();

    public List<User> listUsers() throws AppException {
        return userDAO.listAll();
    }

    public List<Product> listPendingProducts() throws AppException {
        return productDAO.listPending();
    }

    public void approveProduct(int productId) throws AppException {
        productDAO.setApproval(productId, true);
    }
}
