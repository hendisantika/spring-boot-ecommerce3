# E-Commerce Website Using Java

### E-Commerce

<span style="color:blue">**This Website is built for following purpose:-**</span>

- For buying product online
- Maintaining sale history
- Adding and managing products
- User Friendly
- For Implemention of Generic Servlets in Java
- This is a Mini-project developed using Java, Jdbc, Bootstrap.

<span style="color:blue">**Admin Have Following Access for this E-Commerce website:-**</span>

- Add New Products
- Add New Category
- View Available Products
- View Current Users
- View Category
- Remove Products

<span style="color:blue">**Users Have Following Access for this E-Commerce website:-**</span>

- Create New Account or Register
- Login
- View Available Products
- Select Product to Buy
- Select Product Quantity
- Add To cart
- Go to Checkout Page

### Demo Login Credentials (Jujutsu Kaisen Characters)

#### Admin Accounts:

| Username     | Email                  | Password | Role  |
|--------------|------------------------|----------|-------|
| Yuji Itadori | yuji@jujutsukaisen.com | yuji123  | ADMIN |
| Satoru Gojo  | gojo@jujutsukaisen.com | gojo123  | ADMIN |

#### Normal User Accounts:

| Username         | Email                    | Password  | Role   |
|------------------|--------------------------|-----------|--------|
| Megumi Fushiguro | megumi@jujutsukaisen.com | megumi123 | NORMAL |
| Nobara Kugisaki  | nobara@jujutsukaisen.com | nobara123 | NORMAL |
| Maki Zenin       | maki@jujutsukaisen.com   | maki123   | NORMAL |
| Toge Inumaki     | toge@jujutsukaisen.com   | toge123   | NORMAL |
| Panda            | panda@jujutsukaisen.com  | panda123  | NORMAL |
| Yuta Okkotsu     | yuta@jujutsukaisen.com   | yuta123   | NORMAL |
| Kento Nanami     | nanami@jujutsukaisen.com | nanami123 | NORMAL |
| Sukuna Ryomen    | sukuna@jujutsukaisen.com | sukuna123 | NORMAL |

### Technologies used:-

1. Front-End Development:

- Html
- Css
- Javascript
- Bootstrap 4

2. Back-End Development

- Java
- JDBC
- Servlet
- Hibernate
- Maven Support

3. Database used

- MySQL 9.5.0
- Spring Data JPA with Hibernate

### How to Run

1. **Prerequisites:**
    - Java 21 or higher
    - Maven 3.9+
    - Docker and Docker Compose

2. **Start Database:**
   ```bash
   docker compose up -d
   ```

3. **Run Application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Or build and package:
   ```bash
   mvn -B package --file pom.xml
   ```

4. **Access Application:**
    - Main Application: http://localhost:8080
    - phpMyAdmin: http://localhost:8081
    - MySQL Database: localhost:3308
        - Database: `mycart`
        - Username: `yu71`
        - Password: `53cret`

### Features

- Spring Security with BCrypt password encryption
- Docker Compose integration for MySQL database
- Automatic database seeding with demo data
- Thymeleaf template engine
- Spring Boot DevTools for hot reload
- RESTful architecture

Note:- This project implements modern Spring Security with proper password encryption and authentication/authorization.

#### "Suggestions and project Improvements are Invited!"

<h1>This is Admin Page</h1>
<img src="images/admin page.png">

<h1>This is Home Page</h1>
<img src="images/home page.png">

<h1>This is Login Page</h1>
<img src="images/login page.png">

<h1>This is Register Page</h1>
<img src="images/register page.png">

<h1>This is Checkout Page</h1>
<img src="images/checkout page.png">
