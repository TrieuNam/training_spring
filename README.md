NGÀY 1 – SPRING BOOT FOUNDATION

🎯 Mục tiêu:
Hiểu cấu trúc project Spring Boot

Biết cách tạo REST API (GET/POST/PUT/DELETE)

Sử dụng các annotation cơ bản:
- @Component
- @Service
- @Repository 
- @RestController


Xử lý Transaction và Exception

1. Cài đặt môi trường

Cần có:
- Java 17 hoặc Java 11
- IntelliJ IDEA hoặc VSCode
- Maven hoặc Gradle
- Postman hoặc curl
- Spring Initializr: https://start.spring.io

Chọn dependencies:
- Spring Web
- Spring Data JPA
- H2 Database (hoặc MySQL)
- Lombok
- Spring Boot DevTools

3. Tạo Project CRUD Quản Lý User
```css
src/
└── main/java/com/example/demo
    ├── controller/UserController.java
    ├── service/UserService.java
    ├── repository/UserRepository.java
    ├── model/User.java
    └── DemoApplication.java
```

BÀI TẬP TỰ ÔN CUỐI NGÀY:
- Viết thêm trường "age" cho User và cập nhật API
- Tạo mới 1 entity Book có title, author, price
- Viết API CRUD cho Book
- Thêm custom Exception UserNotFoundException thay RuntimeException
