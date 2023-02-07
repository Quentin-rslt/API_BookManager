package com.API.BookManager.service;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.BookManager.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Optional<BookEntity> getBookById(final long id){
        return bookRepository.findById(id);
    }

    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookById(final long id){
        bookRepository.deleteById(id);
    }

    public void deleteBooks(){ bookRepository.deleteAll(); }

    public BookEntity saveBook(BookEntity bookEntity, List<ReadingEntity> readings, List<TagEntity> tags){
        bookEntity.setReadings(readings);
        bookEntity.setTags(tags);

        return bookRepository.save(bookEntity);
    }
}