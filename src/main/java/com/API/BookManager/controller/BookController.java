package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
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
    public BookEntity getBook(@PathVariable(value = "id") Long id){
        return bookService.getBook(id);
    }

    @PostMapping(value = "/api/book/save")
    public BookEntity saveBook(@RequestBody BookEntity bookEntity){
        return bookService.saveBook(bookEntity);
    }

    @PutMapping(value = "/api/book/{id}")
    public BookEntity updateBook(@PathVariable(value = "id") Long id, @RequestBody BookEntity newBookEntity){
        return bookService.updateBook(id, newBookEntity);
    }

    @DeleteMapping(value = "/api/book/{id}")
    public void deleteBook(@PathVariable(value = "id") long id){
        bookService.deleteBook(id);
    }
}