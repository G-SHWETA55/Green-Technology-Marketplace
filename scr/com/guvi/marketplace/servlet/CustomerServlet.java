// com/guvi/marketplace/servlet/CustomerServlet.java
package com.guvi.marketplace.servlet;

import com.guvi.marketplace.exception.AppException;
import com.guvi.marketplace.model.OrderItem;
import com.guvi.marketplace.model.User;
import com.guvi.marketplace.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    private User ensureCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null || u.getRole() != com.guvi.marketplace.model.Enums.Role.CUSTOMER) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return null;
        }
        return u;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.equals("/browse")) {
            try {
                req.setAttribute("products", customerService.browseApproved());
                req.getRequestDispatcher("/jsp/customer/browse.jsp").forward(req, resp);
            } catch (AppException e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/jsp/customer/browse.jsp").forward(req, resp);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User c = ensureCustomer(req, resp);
        if (c == null) return;
        String action = req.getParameter("action");
        try {
            if ("purchase".equals(action)) {
                // Example: single-item purchase from form parameters
                int productId = Integer.parseInt(req.getParameter("productId"));
                int qty = Integer.parseInt(req.getParameter("quantity"));
                BigDecimal unitPrice = new BigDecimal(req.getParameter("unitPrice"));

                List<OrderItem> items = new ArrayList<>();
                OrderItem item = new OrderItem();
                item.setProductId(productId);
                item.setQuantity(qty);
                item.setUnitPrice(unitPrice);
                items.add(item);

                int orderId = customerService.purchase(c.getId(), items);
                req.getSession().setAttribute("lastOrderId", orderId);
                resp.sendRedirect(req.getContextPath() + "/customer/browse?success=1");
            } else if ("review".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                int rating = Integer.parseInt(req.getParameter("rating"));
                String comments = req.getParameter("comments");
                customerService.review(productId, c.getId(), rating, comments);
                resp.sendRedirect(req.getContextPath() + "/customer/browse?review=1");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/customer/browse.jsp").forward(req, resp);
        }
    }
}
