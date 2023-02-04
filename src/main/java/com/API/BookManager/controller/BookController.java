package com.API.BookManager.controller;

import com.API.BookManager.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.API.BookManager.service.BookService;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value="/api/book/all")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(value = "/api/book/{id}")
    public Book getBook(@PathVariable(value = "id") Long id){
        return bookService.getBook(id);
    }

    @PostMapping(value = "/api/book/save")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping(value = "/api/book/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody Book newBook){
        return bookService.updateBook(id, newBook);
    }

    @DeleteMapping(value = "/api/book/{id}")
    public void deleteBook(@PathVariable(value = "id") long id){
        bookService.deleteBook(id);
    }
}