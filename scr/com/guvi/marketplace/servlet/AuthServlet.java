// com/guvi/marketplace/servlet/AuthServlet.java
package com.guvi.marketplace.servlet;

import com.guvi.marketplace.model.Enums;
import com.guvi.marketplace.model.User;
import com.guvi.marketplace.service.AuthService;
import com.guvi.marketplace.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private final AuthService auth = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User u = auth.login(email, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            if (u.getRole() == Enums.Role.ADMIN) resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            else if (u.getRole() == Enums.Role.VENDOR) resp.sendRedirect(req.getContextPath() + "/vendor/dashboard");
            else resp.sendRedirect(req.getContextPath() + "/customer/browse");
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
