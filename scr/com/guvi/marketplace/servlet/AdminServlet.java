// com/guvi/marketplace/servlet/AdminServlet.java
package com.guvi.marketplace.servlet;

import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.User;
import com.guvi.marketplace.model.Product;
import com.guvi.marketplace.model.Enums;
import com.guvi.marketplace.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    private boolean ensureAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null || u.getRole() != Enums.Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ensureAdmin(req, resp)) return;
        String path = req.getPathInfo();
        try {
            if (path == null || path.equals("/dashboard")) {
                List<User> users = adminService.listUsers();
                List<Product> pending = adminService.listPendingProducts();
                req.setAttribute("users", users);
                req.setAttribute("pendingProducts", pending);
                req.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(req, resp);
            } else if (path.equals("/approve")) {
                int productId = Integer.parseInt(req.getParameter("id"));
                adminService.approveProduct(productId);
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(req, resp);
        }
    }
}
