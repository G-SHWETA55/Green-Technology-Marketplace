<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Product" %>
<html>
<head><title>My Products</title></head>
<body>
  <h2>Listed Products</h2>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Approved</th></tr>
    <%
      List<Product> products = (List<Product>) request.getAttribute("products");
      if (products != null) for (Product p : products) {
    %>
      <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= p.isApproved() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
