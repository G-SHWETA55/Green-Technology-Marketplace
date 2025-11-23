<!-- web/jsp/vendor/dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Product" %>
<html>
<head><title>Vendor Dashboard</title></head>
<body>
  <h2>Vendor Dashboard</h2>
  <form method="post" action="${pageContext.request.contextPath}/vendor/dashboard">
    <input type="hidden" name="action" value="listProduct" />
    Name: <input type="text" name="name" required />
    Description: <input type="text" name="description" />
    Price: <input type="number" step="0.01" name="price" required />
    <button type="submit">Submit for approval</button>
  </form>

  <h3>Approved Products</h3>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Price</th></tr>
    <%
      List<Product> products = (List<Product>) request.getAttribute("products");
      if (products != null) for (Product p : products) {
    %>
      <tr><td><%=p.getId()%></td><td><%=p.getName()%></td><td><%=p.getPrice()%></td></tr>
    <% } %>
  </table>

  <div style="color:green;"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></div>
  <div style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
</body>
</html>
