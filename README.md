Day 5: Tích hợp và Bảo mật API với Spring Security
Mục tiêu của Day 5:
- Tích hợp Spring Security vào ứng dụng Spring Boot để bảo vệ các API.
- Cấu hình xác thực (Authentication) và ủy quyền (Authorization).
- Sử dụng JWT (JSON Web Tokens) để bảo mật API và quản lý phiên làm việc.
- Quản lý người dùng, đăng nhập và đăng ký người dùng trong ứng dụng.


1. Giới thiệu về Spring Security và Các Khái Niệm Cơ Bản:

Spring Security là một framework mạnh mẽ để bảo vệ ứng dụng Spring Boot, hỗ trợ xác thực (authentication) và ủy quyền (authorization).

Các khái niệm cơ bản:
- Xác thực (Authentication): Kiểm tra danh tính của người dùng.
- Ủy quyền (Authorization): Kiểm tra quyền truy cập của người dùng.
- JWT (JSON Web Tokens): Một phương thức bảo mật API bằng cách truyền thông tin xác thực qua token.
Note: case thư mục
```text:
spring-boot-tranning/
pom.xml
src
└── main
    ├── java
    │   └── com
    │       └── tpnam
    │           └── spring_boot
    │               ├── SpringBootApplication.java
    │               ├── config
    │               │   └── SecurityConfig.java
    │               ├── controller
    │               │   └── AuthController.java
    │               ├── dto
    │               │   ├── LoginRequest.java
    │               │   └── JwtResponse.java
    │               ├── model
    │               │   └── User.java
    │               ├── repository
    │               │   └── UserRepository.java
    │               ├── security
    │               │   ├── JwtAuthenticationFilter.java
    │               │   └── JwtTokenUtil.java
    │               └── service
    │                   ├── AuthService.java
    │                   └── CustomUserDetailsService.java
    └── resources
           ├── application.properties
           └── static

```


2. Cài Đặt Spring Security
- Thêm Spring Security vào dự án Maven:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Cấu hình cơ bản cho Spring Security:
- Mặc định Spring Security sẽ bảo vệ tất cả các endpoints và yêu cầu người dùng phải đăng nhập. Để tạo một cấu hình đơn giản, bạn có thể cấu hình như sau:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity in APIs
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()  // Allow public access
                .anyRequest().authenticated()  // All other requests require authentication
                .and()
                .formLogin()  // Use form login for authentication
                .permitAll();
    }
} 
```
3. Cấu Hình Xác Thực và Ủy Quyền với UsernamePasswordAuthenticationFilter:
- Cấu hình xác thực: Bạn có thể tạo một lớp UserDetailsService để Spring Security có thể tìm kiếm và xác thực người dùng từ cơ sở dữ liệu.
```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Giả sử tìm người dùng từ database
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("{noop}password")  // NoopPasswordEncoder là một bộ mã hóa đơn giản
                    .roles("USER", "ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
```
- Cấu hình để sử dụng CustomUserDetailsService trong Spring Security:
```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
} 
```
4. Tích Hợp JWT để Bảo Mật API:
- JWT (JSON Web Token) giúp bảo mật các API trong các ứng dụng web hiện đại. Dưới đây là cách bạn có thể tích hợp JWT vào ứng dụng Spring Boot.

Bước 1: Thêm dependencies cho JWT:
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.5</version>
</dependency>
```
Bước 2: Tạo JwtTokenUtil để tạo và xác thực JWT.
```java
@Component
public class JwtTokenUtil {

    private String secretKey = "yourSecretKey";  // Thay đổi secret key khi triển khai

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expiry time 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }
}
```
Bước 3: Tạo một bộ lọc để kiểm tra JWT trong mỗi yêu cầu.
```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String username = jwtTokenUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Validate the token
                if (jwtTokenUtil.validateToken(token, username)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
```
Bước 4: Cấu hình Spring Security để sử dụng JwtAuthenticationFilter.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .permitAll();
    }
}
```
