// com/guvi/marketplace/servlet/VendorServlet.java
package com.guvi.marketplace.servlet;

import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.Enums;
import com.guvi.marketplace.model.User;
import com.guvi.marketplace.service.VendorService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

public class VendorServlet extends HttpServlet {
    private final VendorService vendorService = new VendorService();

    private User ensureVendor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null || u.getRole() != Enums.Role.VENDOR) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return null;
        }
        return u;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User v = ensureVendor(req, resp);
        if (v == null) return;
        try {
            req.setAttribute("products", vendorService.approvedProducts());
            req.getRequestDispatcher("/jsp/vendor/dashboard.jsp").forward(req, resp);
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/vendor/dashboard.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User v = ensureVendor(req, resp);
        if (v == null) return;
        String action = req.getParameter("action");
        try {
            if ("listProduct".equals(action)) {
                String name = req.getParameter("name");
                String desc = req.getParameter("description");
                BigDecimal price = new BigDecimal(req.getParameter("price"));
                vendorService.listProduct(v.getId(), name, desc, price);
                req.setAttribute("message", "Product submitted for approval");
            }
            resp.sendRedirect(req.getContextPath() + "/vendor/dashboard");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/vendor/dashboard.jsp").forward(req, resp);
        }
    }
}
