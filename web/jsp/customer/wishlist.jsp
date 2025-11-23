<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Product" %>
<html>
<head><title>Wishlist</title></head>
<body>
  <h2>Your Wishlist</h2>
  <table border="1">
    <tr><th>Name</th><th>Description</th><th>Price</th></tr>
    <%
      List<Product> wishlist = (List<Product>) request.getAttribute("wishlist");
      if (wishlist != null) for (Product p : wishlist) {
    %>
      <tr>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getPrice() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
