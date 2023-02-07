package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.API.BookManager.service.BookService;

import java.util.List;
import java.util.Optional;

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
        return bookService.getBookById(id).get();
    }

    @DeleteMapping(value = "/api/book/{id}")
    public void deleteBookById(@PathVariable(value = "id") long id){
        bookService.deleteBookById(id);
    }

    @DeleteMapping(value = "/api/book/all")
    public void deleteBooks(){ bookService.deleteBooks(); }

    @PostMapping(value = "/api/book/save")
    public BookEntity saveBook(@RequestBody BookEntity book, @RequestBody List<ReadingEntity> readings, @RequestBody List<TagEntity> tags){
        return bookService.addBook(book, readings, tags);
    }

    @PutMapping(value = "/api/book/{id}")
    public BookEntity updateBookById(@PathVariable(value = "id") Long id, @RequestBody BookEntity newBook, @RequestBody List<ReadingEntity> readings, @RequestBody List<TagEntity> tags){
        return bookService.updateBook(id, newBook, readings, tags);
    }
}