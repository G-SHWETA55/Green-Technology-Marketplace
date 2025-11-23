<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Review" %>
<html>
<head><title>Your Reviews</title></head>
<body>
  <h2>Submitted Reviews</h2>
  <table border="1">
    <tr><th>Product ID</th><th>Rating</th><th>Comments</th><th>Date</th></tr>
    <%
      List<Review> reviews = (List<Review>) request.getAttribute("reviews");
      if (reviews != null) for (Review r : reviews) {
    %>
      <tr>
        <td><%= r.getProductId() %></td>
        <td><%= r.getRating() %></td>
        <td><%= r.getComments() %></td>
        <td><%= r.getCreatedAt() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
