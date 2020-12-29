package com.swung0x48.librarymanager.controller;

import com.swung0x48.librarymanager.domain.Book;
import com.swung0x48.librarymanager.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> allBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Integer deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @GetMapping(value = "/home")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> adminBookHome() {
        return bookService.getTotalMessage();
    }

    @PutMapping(value = "/ban/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book banBook(@PathVariable Integer id)
    {
        return bookService.banBook(id);
    }

    @PutMapping(value = "/unban/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book unbanBook(@PathVariable Integer id)
    {
        return bookService.unbanBook(id);
    }
}
