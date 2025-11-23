// com/guvi/marketplace/service/CustomerService.java
package com.guvi.marketplace.service;

import com.guvi.marketplace.dao.OrderDAO;
import com.guvi.marketplace.dao.ProductDAO;
import com.guvi.marketplace.dao.ReviewDAO;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Order;
import com.guvi.marketplace.model.OrderItem;
import com.guvi.marketplace.model.Product;
import com.guvi.marketplace.model.Review;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final ProductDAO productDAO = new ProductDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final ReviewDAO reviewDAO = new ReviewDAO();

    public List<Product> browseApproved() throws AppException {
        return productDAO.listApproved();
    }

    public int purchase(int customerId, List<OrderItem> items) throws AppException {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem i : items) total = total.add(i.getUnitPrice().multiply(new BigDecimal(i.getQuantity())));
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setTotal(total);
        int orderId = orderDAO.placeOrder(order, items);
        NotificationService.asyncSendOrderConfirmation(customerId, orderId); // Threads
        return orderId;
    }

    public int review(int productId, int customerId, int rating, String comments) throws AppException {
        if (rating < 1 || rating > 5) throw new com.guvi.marketplace.exception.ValidationException("Rating must be 1-5");
        Review r = new Review();
        r.setProductId(productId);
        r.setCustomerId(customerId);
        r.setRating(rating);
        r.setComments(comments);
        return reviewDAO.create(r);
    }
}
