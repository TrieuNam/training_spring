package com.tpnam.spring_boot.controller;

import com.tpnam.spring_boot.entity.Book;
import com.tpnam.spring_boot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getALl() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }


    @PostMapping
    public Book create(@RequestBody Book Book) {
        return bookService.createBook(Book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable("id") Long id, @RequestBody Book Book) {
        return bookService.updateBookById(id, Book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
