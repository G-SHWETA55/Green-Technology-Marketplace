<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Order" %>
<html>
<head><title>Order History</title></head>
<body>
  <h2>Your Orders</h2>
  <table border="1">
    <tr><th>Order ID</th><th>Status</th><th>Total</th><th>Date</th></tr>
    <%
      List<Order> orders = (List<Order>) request.getAttribute("orders");
      if (orders != null) for (Order o : orders) {
    %>
      <tr>
        <td><%= o.getId() %></td>
        <td><%= o.getStatus() %></td>
        <td><%= o.getTotal() %></td>
        <td><%= o.getCreatedAt() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
