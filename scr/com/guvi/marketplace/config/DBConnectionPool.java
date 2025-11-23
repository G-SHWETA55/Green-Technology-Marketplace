// com/guvi/marketplace/config/DBConnectionPool.java
package com.guvi.marketplace.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class DBConnectionPool {
    private static final Deque<Connection> pool = new ArrayDeque<>();
    private static int created = 0;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static synchronized Connection acquire() throws SQLException {
        if (!pool.isEmpty()) return pool.pop();
        if (created < AppConfig.POOL_SIZE) {
            created++;
            return DriverManager.getConnection(AppConfig.JDBC_URL, AppConfig.JDBC_USER, AppConfig.JDBC_PASSWORD);
        }
        // Wait until a connection is released
        while (pool.isEmpty()) {
            try { DBConnectionPool.class.wait(100); } catch (InterruptedException ignored) {}
        }
        return pool.pop();
    }

    public static synchronized void release(Connection conn) {
        if (conn != null) {
            pool.push(conn);
            DBConnectionPool.class.notifyAll();
        }
    }
}
