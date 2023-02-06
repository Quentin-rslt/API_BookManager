package com.API.BookManager.service;

import com.API.BookManager.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.BookManager.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookEntity getBook(final long id){
        return bookRepository.findById(id).get();
    }

    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public void deleteBook(final long id){
        bookRepository.deleteById(id);
    }

    public BookEntity saveBook(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    public BookEntity updateBook(Long id, BookEntity newBookEntity){
        BookEntity oldBookEntity = getBook(id);

        oldBookEntity.setTitle(newBookEntity.getTitle());
        oldBookEntity.setAuthor(newBookEntity.getAuthor());
        oldBookEntity.setImage(newBookEntity.getImage());
        oldBookEntity.setNumberOP(newBookEntity.getNumberOP());
        oldBookEntity.setNoteBabelio((newBookEntity.getNoteBabelio()));
        oldBookEntity.setReleaseYear(newBookEntity.getReleaseYear());
        oldBookEntity.setSummary(newBookEntity.getSummary());

        return bookRepository.save(oldBookEntity);
    }
}