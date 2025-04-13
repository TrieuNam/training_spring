LỘ TRÌNH HỌC JAVA BACKEND - DAY 2

🎯 Mục tiêu Day 2: Thành thạo RESTful API cơ bản với Spring Boot

- Hiểu rõ luồng request – response
- Biết cách dùng các HTTP method (GET, POST, PUT, DELETE)
- Dùng DTO, mapping, xử lý input/output
- Thực hành CRUD đơn giản

📚 Nội dung chính

✅ 1. Kiến thức cần học

| Chủ đề	                | Nội dung                                                                                      |
|------------------------|-----------------------------------------------------------------------------------------------|
| 🔸 Spring Boot REST	   | @RestController, @RequestMapping, @GetMapping, @PostMapping, @PathVariable, @RequestBody<br/> |
| 🔸 CRUD API	           | Viết API cho 1 entity (vd: User, Book, Product)                                               |
| 🔸 DTO                 | Tạo class UserDTO, sử dụng ModelMapper hoặc tự mapping                                        |
| 🔸 ResponseEntity      | Cách trả kết quả HTTP chuẩn (status, body)                                                    |
| 🔸 Validating input	   | @Valid, @NotNull, @Email, @Size, BindingResult                                                |
| 🔸 Exception handling	 | Bổ sung GlobalExceptionHandler xử lý @Valid lỗi                                               |


✅ 2. Bài tập thực hành
👉 Tạo API cho User entity (dùng H2 hoặc file-based H2):
- POST /users – tạo user mới
- GET /users/{id} – lấy user theo ID
- PUT /users/{id} – cập nhật thông tin user
- DELETE /users/{id} – xoá user
- GET /users – lấy danh sách user


3. Yêu cầu nâng cao 
- Thêm UserDTO để tách Entity với API
- Thêm validate: @NotBlank, @Email, @Size trong DTO
- Sử dụng mapper and mapstuct
- Nếu có thời gian: Viết test đơn giản cho UserService


🛠️ Tools cần dùng
- Spring Boot 3+
- Maven/Gradle
- Spring Web, Spring Data JPA, H2 Database
- (Optional) ModelMapper hoặc MapStruct
Mapstruct:
```xml
  <!-- MapStruct -->
<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${mapstruct.version}</version>
    </dependency>
</dependencies>

<build>
<plugins>
    <!-- Maven Compiler Plugin cho annotation processing -->
    <plugin>
        <configuration>
            <annotationProcessorPaths>
                <path>
                    <groupId>org.mapstruct</groupId>
                    <artifactId>mapstruct-processor</artifactId>
                    <version>${mapstruct.version}</version>
                </path>
            </annotationProcessorPaths>
        </configuration>
    </plugin>
</plugins>
</build>
```
ModelMapper:
- maven:
```xml

<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.1</version>
</dependency>
```
- create config mapper:
```java
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
```

- Postman hoặc curl để test API


🎓 Sau Day 2 bạn sẽ:
- Hiểu rõ cách xây dựng REST API từ đầu
- Biết chuẩn hoá input/output với DTO
- Áp dụng tốt validation và exception handling

- Viết được 1 service đơn giản đầy đủ flow:
  - junit: sử dụng Mockito
  - integration test: test với DB h2


