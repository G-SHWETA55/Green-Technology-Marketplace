// com/guvi/marketplace/dao/UserDAO.java
package com.guvi.marketplace.dao;

import com.guvi.marketplace.config.DBConnectionPool;
import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.exception.NotFoundException;
import com.guvi.marketplace.model.Enums;
import com.guvi.marketplace.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User findByEmail(String email) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE email=? AND active=TRUE");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
            throw new NotFoundException("User", email);
        } catch (SQLException e) {
            throw new AppException("DB error finding user by email", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    public List<User> listAll() throws AppException {
        Connection conn = null;
        List<User> users = new ArrayList<>();
        try {
            conn = DBConnectionPool.acquire();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) users.add(map(rs));
            return users;
        } catch (SQLException e) {
            throw new AppException("DB error listing users", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    public int create(User u) throws AppException {
        Connection conn = null;
        try {
            conn = DBConnectionPool.acquire();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(name,email,password_hash,role,active) VALUES(?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPasswordHash());
            ps.setString(4, u.getRole().name());
            ps.setBoolean(5, u.isActive());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
            throw new AppException("Failed to create user: no ID returned");
        } catch (SQLException e) {
            throw new AppException("DB error creating user", e);
        } finally {
            DBConnectionPool.release(conn);
        }
    }

    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setRole(Enums.Role.valueOf(rs.getString("role")));
        u.setActive(rs.getBoolean("active"));
        return u;
    }
}
