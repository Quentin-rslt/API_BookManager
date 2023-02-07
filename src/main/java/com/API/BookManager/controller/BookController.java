package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.API.BookManager.service.BookService;

import java.awt.print.Book;
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

    @PostMapping(value = "/api/book/save")
    public BookEntity saveBook(@RequestBody BookEntity book){
        return bookService.saveBook(book);
    }

    @PutMapping(value = "/api/book/{id}")
    public BookEntity updateBookById(@PathVariable(value = "id") Long id, @RequestBody BookEntity newBook){
        Optional<BookEntity> oldBook = bookService.getBookById(id);
        if(oldBook.isPresent()){
            if(newBook.getTitle()!=null){
                oldBook.get().setTitle(newBook.getTitle());
            }
            if(newBook.getAuthor()!=null){
                oldBook.get().setAuthor(newBook.getAuthor());
            }
            if(newBook.getImage()!=null){
                oldBook.get().setImage(newBook.getImage());
            }
            oldBook.get().setNumberOP(newBook.getNumberOP());
            oldBook.get().setNotePerso(newBook.getNotePerso());
            oldBook.get().setNoteBabelio(newBook.getNoteBabelio());
            if(newBook.getReleaseYear()!=null){
                oldBook.get().setReleaseYear(newBook.getReleaseYear());
            }
            if(newBook.getSummary()!=null){
                oldBook.get().setSummary(newBook.getSummary());
            }
            oldBook.get().setReadings(newBook.getReadings());

            bookService.saveBook(oldBook.get());
            return oldBook.get();
        }
        else {
            return null;
        }
    }

    @DeleteMapping(value = "/api/book/{id}")
    public void deleteBookById(@PathVariable(value = "id") long id){
        bookService.deleteBookById(id);
    }
}