// com/guvi/marketplace/dao/ReviewDAO.java
package com.guvi.marketplace.dao;

import com.guvi.marketplace.config.DBConnectionPool;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    public int create(Review r) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO reviews(product_id,customer_id,rating,comments) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, r.getProductId());
            ps.setInt(2, r.getCustomerId());
            ps.setInt(3, r.getRating());
            ps.setString(4, r.getComments());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            return keys.next() ? keys.getInt(1) : -1;
        } catch (SQLException e) {
            throw new AppException("DB error creating review", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    public List<Review> listByProduct(int productId) throws AppException {
        Connection conn = null;
        List<Review> reviews = new ArrayList<>();
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM reviews WHERE product_id=?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setProductId(productId);
                r.setCustomerId(rs.getInt("customer_id"));
                r.setRating(rs.getInt("rating"));
                r.setComments(rs.getString("comments"));
                reviews.add(r);
            }
            return reviews;
        } catch (SQLException e) {
            throw new AppException("DB error listing reviews", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }
}
