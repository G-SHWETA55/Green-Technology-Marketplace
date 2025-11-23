<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.User" %>
<html>
<head><title>Manage Users</title></head>
<body>
  <h2>User Management</h2>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Active</th></tr>
    <%
      List<User> users = (List<User>) request.getAttribute("users");
      if (users != null) for (User u : users) {
    %>
      <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>
        <td><%= u.isActive() %></td>
      </tr>
    <% } %>
  </table>
</body>
</html>
