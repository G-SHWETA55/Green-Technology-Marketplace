# 🌱 Green-Technology-Marketplace
The marketplace will offer a variety of green technology products, enabling user to make eco-friendly purchases and leave reviews connecting vendors and customers to promote sustained environment.

A full-stack Java web application for an online marketplace dedicated to eco-friendly products.  
Built with **Java, JDBC, Servlets, JSP**, and follows layered architecture (DAO → Service → Servlet → JSP).

--------------------------------------------------------------------

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

-------------------------------------------------------------------------------

## 🗃️ Database Schema

Database name: **`greentech_marketplace`**

Tables:
- `users(id, name, email, password_hash, role, active)`
- `products(id, vendor_id, name, description, price, approved)`
- `orders(id, customer_id, status, total)`
- `order_items(id, order_id, product_id, quantity, unit_price)`
- `reviews(id, product_id, customer_id, rating, comments)`

Run `schema.sql` to initialize.

-----------------------------------------------------------------------------

## 🛠️ Tech Stack

- **Java Core**: OOP, Collections, Exception Handling, Threads
- **JDBC**: CRUD operations, transactions, connection pooling
- **Servlets**: Lifecycle, request/response, session management
- **JSP**: Role-based dashboards and forms
- **MySQL**: Persistent storage

-------------------------------------------------------------------------------

## 📂 Project Structure

src/com/guvi/marketplace/ <br/>  ├─ model/ # User, Product, Order, Review <br/> ├─ dao/ # JDBC DAOs <br/> ├─ service/ # Business logic <br/> ├─ servlet/ # Controllers <br/> └─ util/ # Validation, helpers <br/> web/ <br/> ├─ jsp/ # JSP dashboards <br/> └─ WEB-INF/web.xml <br/> resources/ <br/> └─ schema.sql

----------------------------------------------------------------------------------

## ⚡ How to use (Quickstart)

Prerequisites: JDK 11+, Apache Tomcat (or any servlet container), MySQL, and the MySQL JDBC driver.

1. Clone the repo
   git clone https://github.com/G-SHWETA55/Green-Technology-Marketplace.git
   cd Green-Technology-Marketplace

2. Prepare the database
   - By default the app's DB config is in scr/com/guvi/marketplace/config/AppConfig.java:
     JDBC URL: jdbc:mysql://localhost:3306/greentech
     user: root
     password: 13579@
   - Either create the database greentech, or update AppConfig to the database name you prefer.
   - Import the schema:
     mysql -u root -p greentech < schema.sql

3. Build & deploy
   - If a build tool (Maven/Gradle) is present:
     mvn clean package   # or ./gradlew build
     Copy the generated WAR to TOMCAT_HOME/webapps/
   - Or use your IDE (IntelliJ/Eclipse) to build and deploy as a web application.
   - Make sure mysql-connector-java.jar is available to the webapp (put in TOMCAT_HOME/lib or WEB-INF/lib).

4. Start the servlet container
   - Start Tomcat and open: http://localhost:8080/<your-app-context>/
   - Login via the app's login page (check schema.sql for seeded users or create an account).

5. Basic flows
   - Admin: manage users, approve product listings (Admin dashboard).
   - Vendor: list products for approval via vendor dashboard.
   - Customer: browse approved products, purchase and leave reviews.

Troubleshooting
- "MySQL Driver not found": ensure the MySQL connector JAR is on the runtime classpath.
- DB name/credentials mismatch: either change AppConfig.java or create the DB exactly as configured.
- SQL errors: verify schema.sql ran successfully and tables/seed data exist.
  
