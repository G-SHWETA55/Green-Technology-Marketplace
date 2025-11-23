// com/guvi/marketplace/dao/ProductDAO.java
package com.guvi.marketplace.dao;

import com.guvi.marketplace.config.DBConnectionPool;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public int create(Product p) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO products(vendor_id,name,description,price,approved) VALUES(?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getVendorId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setBigDecimal(4, p.getPrice());
            ps.setBoolean(5, false);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            return keys.next() ? keys.getInt(1) : -1;
        } catch (SQLException e) {
            throw new AppException("DB error creating product", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    public void setApproval(int productId, boolean approved) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE products SET approved=? WHERE id=?");
            ps.setBoolean(1, approved);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("DB error updating approval", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    public List<Product> listApproved() throws AppException {
        return query("SELECT * FROM products WHERE approved=TRUE");
    }

    public List<Product> listPending() throws AppException {
        return query("SELECT * FROM products WHERE approved=FALSE");
    }

    private List<Product> query(String sql) throws AppException {
        Connection conn = null;
        List<Product> list = new ArrayList<>();
        try {
            conn = DBConnectionPool.acquire();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setVendorId(rs.getInt("vendor_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setApproved(rs.getBoolean("approved"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new AppException("DB error listing products", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }
}
