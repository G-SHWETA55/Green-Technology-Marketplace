<!-- web/jsp/customer/browse.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.guvi.marketplace.model.Product" %>
<html>
<head><title>Browse Products</title></head>
<body>
  <h2>Browse Green Technology Products</h2>
  <%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null) for (Product p : products) {
  %>
    <div>
      <h4><%=p.getName()%></h4>
      <p><%=p.getDescription()%></p>
      <p>Price: <%=p.getPrice()%></p>
      <form method="post" action="${pageContext.request.contextPath}/customer/browse">
        <input type="hidden" name="action" value="purchase" />
        <input type="hidden" name="productId" value="<%=p.getId()%>" />
        <input type="hidden" name="unitPrice" value="<%=p.getPrice()%>" />
        Quantity: <input type="number" name="quantity" value="1" min="1" />
        <button type="submit">Buy</button>
      </form>
      <form method="post" action="${pageContext.request.contextPath}/customer/browse">
        <input type="hidden" name="action" value="review" />
        <input type="hidden" name="productId" value="<%=p.getId()%>" />
        Rating: <input type="number" name="rating" min="1" max="5" />
        Comments: <input type="text" name="comments" />
        <button type="submit">Submit Review</button>
      </form>
    </div>
  <% } %>
  <div style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
</body>
</html>
