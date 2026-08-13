# 🏠 Home Appliances Management Website — Spring Boot Project

**Electroménager Management** is a full-stack web application built with **Spring Boot**, **MySQL**, and frontend technologies (**HTML, CSS, JavaScript**).  
The project simulates a **home appliances store** with both **admin management features** and a **client browsing experience**, designed to showcase secure login, product management, and interactive client feedback.

---

## 🎬 Demo Video

Check out the project demo here:  

[![Watch the demo](assets/demo-thumbnail.png)](https://drive.google.com/file/d/1ZPdMsIkTZVsit6xbGMSibDzaB3eHeVyp/view?usp=drive_link)  

> Clicking the thumbnail will open the full 3-minute video demo on Google Drive.

---

## 🏠 Home Page

The **client-facing home page** allows users to:  

- Browse **articles and products**  
- See **client reviews** for appliances  
- Leave their own **reviews**  
- Contact the store and view **location maps**

---

## 🔐 Admin Panel

The **admin panel** is secured using **Spring Security**.  
- Access is only possible via the login page: `localhost/addproduct`  
- Normal users cannot access it directly from the home page  

### 🔧 Admin Features

- **Product Management:**  
  - Add new products  
  - Monitor stock levels  
  - Notifications for products low on stock 🔔 (bell turns red)  

- **Purchase Management:**  
  - Record client purchases  
  - View invoices/factures for each client  
  - Track purchase history  

- **Client Management:**  
  - Add new clients  
  - View client details  

- **Messages & Reviews:**  
  - Read client messages  
  - View product reviews left by clients  

---

## 🛒 Client Features

Clients can:  

- Browse **products/articles** with previews  
- See **reviews** from other clients  
- Leave **feedback/reviews** for products  
- Contact the store via the **contact form**  
- View the store location on a **map**

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot  
- **Frontend:** HTML, CSS, JavaScript  
- **Database:** MySQL  
- **Security:** Spring Security for login and encrypted passwords  

---

## 🚀 Setup & Running the Project

1. **Install dependencies:**  
   - MySQL  
   - Spring Boot (Java 17+ recommended)  

2. **Configure database:**  
   - Create a MySQL database (e.g., `electromenager_db`)  
   - Update `application.properties` with your database credentials  

3. **Run the project:**  
   - Launch as a Spring Boot app  
   - Open in browser: `http://localhost:8080`  

4. **Admin Access:**  
   - Navigate to `http://localhost:8080/addproduct`  
   - Login with admin credentials  

---

## 🌟 Notes

- Notifications indicate **products about to sell out**  
- Only admins can add products, purchases, and clients  
- Client reviews and messages are visible in the admin panel for management  
- Full purchase history and invoices are tracked for transparency  

---

## 👩‍💻 Credits

- Developed by **Nadine Mlayeh**  
- Full-stack project demonstrating secure login, admin management, and interactive client features  

---

⭐ Enjoy exploring the Electroménager Management Website! 🔧🛒
