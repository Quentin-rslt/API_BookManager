package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.API.BookManager.service.BookService;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value="/api/book/all")
    public List<BookEntity> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(value = "/api/book/{id}")
    public BookEntity getBookById(@PathVariable(value = "id") Long id){
        return bookService.getBookById(id);
    }

    @PostMapping(value = "/api/book/save")
    public BookEntity saveBook(@RequestBody BookEntity book){
        return bookService.saveBook(book);
    }

    @PutMapping(value = "/api/book/{id}")
    public BookEntity updateBookById(@PathVariable(value = "id") Long id, @RequestBody BookEntity newBook){
        return bookService.updateBook(id, newBook);
    }

    @DeleteMapping(value = "/api/book/{id}")
    public void deleteBookById(@PathVariable(value = "id") long id){
        bookService.deleteBookById(id);
    }
}