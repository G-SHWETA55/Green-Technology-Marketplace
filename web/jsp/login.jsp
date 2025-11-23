<!-- web/jsp/login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
  <h2>Login</h2>
  <form method="post" action="${pageContext.request.contextPath}/auth/login">
    Email: <input type="email" name="email" required />
    Password: <input type="password" name="password" required />
    <button type="submit">Login</button>
  </form>
  <div style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
</body>
</html>
