package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
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
    public BookEntity getBookById(@PathVariable(value = "id") final Long id){
        return bookService.getBookById(id).isPresent() ? bookService.getBookById(id).get() : null;
    }

    @GetMapping(value = "/api/book/{title}/{author}")
    public BookEntity getBookByTitleAndAuthor(@PathVariable(value = "title") final String title, @PathVariable(value = "author") final String author){
        return bookService.getBookByTitleAndAuthor(title, author).isPresent() ? bookService.getBookByTitleAndAuthor(title, author).get() : null;
    }

    @GetMapping(value = "/api/book/{idBook}/tag/all")
    public List<TagEntity> getTagsByIdBook(@PathVariable(value = "idBook") final Long idBook){
        return bookService.getBookById(idBook).isPresent() ? bookService.getBookById(idBook).get().getTags() : null;
    }

    @GetMapping(value = "/api/book/{idBook}/reading/all")
    public List<ReadingEntity> getReadingsByIdBook(@PathVariable(value = "idBook") final Long idBook){
        return bookService.getBookById(idBook).isPresent() ? bookService.getBookById(idBook).get().getReadings() : null;
    }

    @DeleteMapping(value = "/api/deleteBook/{id}")
    public void deleteBookById(@PathVariable(value = "id") final Long id){
        bookService.deleteBookById(id);
    }

    @DeleteMapping(value = "/api/deleteBook/all")
    public void deleteBooks(){ bookService.deleteBooks(); }

    @PostMapping(value = "/api/addBook")
    public BookEntity addBook(@RequestBody final  BookEntity book){
        return bookService.addBook(book);
    }

    @PutMapping(value = "/api/updateBook/{id}")
    public BookEntity updateBookById(@PathVariable(value = "id") final  Long id, @RequestBody final  BookEntity newBook){
        return bookService.updateBook(id, newBook);
    }
}