package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.API.BookManager.service.BookService;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * @see BookService#getBooks()
     */
    @GetMapping(value="/api/book/all")
    public List<BookEntity> getBooks(){
        return bookService.getBooks();
    }


    /**
     * @see BookService#getBookById(Long)
     */
    @GetMapping(value = "/api/book/{id}")
    public BookEntity getBookById(@PathVariable(value = "id") final Long id){
        return bookService.getBookById(id);
    }

    /**
     * @see BookService#getBookByIdTag(Long)
     */
    @GetMapping(value = "/api/book/tag/{idTag}")
    public List<BookEntity> getBookByIdTag(@PathVariable(value = "idTag") final Long idTag){
        return bookService.getBookByIdTag(idTag);
    }

    /**
     * @see BookService#getBookByTitleAndAuthor(String, String)
     */
    @GetMapping(value = "/api/book/{title}/{author}")
    public BookEntity getBookByTitleAndAuthor(@PathVariable(value = "title") final String title, @PathVariable(value = "author") final String author){
        return bookService.getBookByTitleAndAuthor(title, author);
    }

    /**
     * @see BookService#deleteBookById(Long)
     */
    @DeleteMapping(value = "/api/deleteBook/{id}")
    public void deleteBookById(@PathVariable(value = "id") final Long id){
        bookService.deleteBookById(id);
    }

    /**
     * @see BookService#deleteBooks()
     */
    @DeleteMapping(value = "/api/deleteBook/all")
    public void deleteBooks(){ bookService.deleteBooks(); }

    /**
     * @see BookService#addBook(BookEntity)
     */
    @PostMapping(value = "/api/addBook")
    public BookEntity addBook(@RequestBody final  BookEntity book){
        return bookService.addBook(book);
    }

    /**
     * @see BookService#updateBook(Long, BookEntity) 
     */
    @PutMapping(value = "/api/updateBook/{id}")
    public BookEntity updateBookById(@PathVariable(value = "id") final  Long id, @RequestBody final  BookEntity newBook){
        return bookService.updateBook(id, newBook);
    }
}