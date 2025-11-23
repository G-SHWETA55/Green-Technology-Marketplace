// com/guvi/marketplace/dao/OrderDAO.java
package com.guvi.marketplace.dao;

import com.guvi.marketplace.config.DBConnectionPool;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Enums;
import com.guvi.marketplace.model.Order;
import com.guvi.marketplace.model.OrderItem;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    // Transactional order placement: create order + items atomically
    public int placeOrder(Order order, List<OrderItem> items) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO orders(customer_id,status,total) VALUES(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, Enums.OrderStatus.PAID.name());
            ps.setBigDecimal(3, order.getTotal());
            ps.executeUpdate();
            int orderId;
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) orderId = keys.getInt(1);
            else throw new AppException("Failed to create order");

            PreparedStatement itemPs = conn.prepareStatement(
                "INSERT INTO order_items(order_id,product_id,quantity,unit_price) VALUES(?,?,?,?)");
            for (OrderItem it : items) {
                itemPs.setInt(1, orderId);
                itemPs.setInt(2, it.getProductId());
                itemPs.setInt(3, it.getQuantity());
                itemPs.setBigDecimal(4, it.getUnitPrice());
                itemPs.addBatch();
            }
            itemPs.executeBatch();

            conn.commit();
            return orderId;
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
            throw new AppException("DB error placing order", e);
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ignored) {}
            DBConnectionPool.release(conn);
        }
    }
}
