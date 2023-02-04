package com.API.BookManager.service;

import lombok.Data;
import com.API.BookManager.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.BookManager.repository.BookRepository;

import java.util.List;

@Data
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book getBook(final long id){
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void deleteBook(final long id){
        bookRepository.deleteById(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBook){
        Book oldBook = getBook(id);

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setImage(newBook.getImage());
        oldBook.setNumberOP(newBook.getNumberOP());
        oldBook.setNoteBabelio((newBook.getNoteBabelio()));
        oldBook.setReleaseYear(newBook.getReleaseYear());
        oldBook.setAvReadingTime(newBook.getAvReadingTime());
        oldBook.setNumberReading(newBook.getNumberReading());
        oldBook.setSummary(newBook.getSummary());

        return bookRepository.save(oldBook);
    }
}