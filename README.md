🎯 Mục tiêu Day 4:

- Xử lý nghiệp vụ, exception custom, và bắt đầu làm quen với quan hệ giữa các bảng (OneToMany, ManyToOne).


🎯 Bài tập Day 4:
1. Tạo Post entity có quan hệ với User
2. Tạo PostDTO, PostMapper
3. Tạo PostController có endpoint:
- POST /users/{userId}/posts: tạo post cho user
- GET /users/{userId}/posts: lấy danh sách post theo user
4. Custom Exception:
- UserNotFoundException
- PostNotFoundException 
- GlobalExceptionHandler (dùng @ControllerAdvice)