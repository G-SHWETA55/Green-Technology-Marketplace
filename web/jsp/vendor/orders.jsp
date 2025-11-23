<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Order" %>
<html>
<head><title>Vendor Orders</title></head>
<body>
  <h2>Customer Orders</h2>
  <table border="1">
    <tr><th>Order ID</th><th>Customer</th><th>Status</th><th>Total</th></tr>
    <%
      List<Order> orders = (List<Order>) request.getAttribute("orders");
      if (orders != null) for (Order o : orders) {
    %>
      <tr>
        <td><%= o.getId() %></td>
        <td><%= o.getCustomerId() %></td>
        <td><%= o.getStatus() %></td>
        <td><%= o.getTotal() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
