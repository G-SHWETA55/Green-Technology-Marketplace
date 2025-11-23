<!-- web/jsp/admin/dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.User, com.guvi.marketplace.model.Product" %>
<html>
<head><title>Admin Dashboard</title></head>
<body>
  <h2>Admin Dashboard</h2>
  <h3>Users</h3>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Active</th></tr>
    <%
      List<User> users = (List<User>) request.getAttribute("users");
      if (users != null) for (User u : users) {
    %>
      <tr><td><%=u.getId()%></td><td><%=u.getName()%></td><td><%=u.getEmail()%></td><td><%=u.getRole()%></td><td><%=u.isActive()%></td></tr>
    <% } %>
  </table>
  <h3>Pending Products</h3>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Vendor</th><th>Price</th><th>Action</th></tr>
    <%
      List<Product> pending = (List<Product>) request.getAttribute("pendingProducts");
      if (pending != null) for (Product p : pending) {
    %>
      <tr>
        <td><%=p.getId()%></td><td><%=p.getName()%></td><td><%=p.getVendorId()%></td><td><%=p.getPrice()%></td>
        <td><a href="${pageContext.request.contextPath}/admin/approve?id=<%=p.getId()%>">Approve</a></td>
      </tr>
    <% } %>
  </table>
  <div style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
</body>
</html>
