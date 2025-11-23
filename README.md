# 🌱 Green-Technology-Marketplace
The marketplace will offer a variety of green technology products, enabling user to make eco-friendly purchases and leave reviews connecting vendors and customers to promote sustained environment.

A full-stack Java web application for an online marketplace dedicated to eco-friendly products.  
Built with **Java, JDBC, Servlets, JSP**, and follows layered architecture (DAO → Service → Servlet → JSP).

-------------------------------

## 🚀 Features

### Admin
- Manage users (create/update/delete)
- Approve or reject product listings
- Configure system settings
- Monitor customer reviews

### Vendor
- List green technology products
- Manage customer orders
- Respond to reviews
- View sales reports

### Customer
- Browse and purchase products
- View order history
- Leave and manage reviews
- Maintain wishlist

--------------------------------------------------------

## 🗃️ Database Schema

Database name: **`greentech_marketplace`**

Tables:
- `users(id, name, email, password_hash, role, active)`
- `products(id, vendor_id, name, description, price, approved)`
- `orders(id, customer_id, status, total)`
- `order_items(id, order_id, product_id, quantity, unit_price)`
- `reviews(id, product_id, customer_id, rating, comments)`

Run `schema.sql` to initialize.

-----------------------------------------------------

## 🛠️ Tech Stack

- **Java Core**: OOP, Collections, Exception Handling, Threads
- **JDBC**: CRUD operations, transactions, connection pooling
- **Servlets**: Lifecycle, request/response, session management
- **JSP**: Role-based dashboards and forms
- **MySQL**: Persistent storage

---

## 📂 Project Structure

src/com/guvi/marketplace/ <br/>  ├─ model/ # User, Product, Order, Review <br/> ├─ dao/ # JDBC DAOs <br/> ├─ service/ # Business logic <br/> ├─ servlet/ # Controllers <br/> └─ util/ # Validation, helpers <br/> web/ <br/> ├─ jsp/ # JSP dashboards <br/> └─ WEB-INF/web.xml <br/> resources/ <br/> └─ schema.sql
