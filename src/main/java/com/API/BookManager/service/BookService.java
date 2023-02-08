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

    public void deleteBookById(final Long id){
        bookRepository.deleteById(id);
    }

    public void deleteBooks(){ bookRepository.deleteAll(); }

    public BookEntity addBook(final BookEntity newBook, final List<ReadingEntity> readings, final List<TagEntity> tags){
        if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
            return null;
        }

        for(ReadingEntity reading: readings) {
            newBook.addReading(reading);
        }
        for(TagEntity tag : tags){
            tag.addBook(newBook);
        }

        return bookRepository.save(newBook);
    }

    public BookEntity updateBook(final Long id, final BookEntity newBook, final List<ReadingEntity> readings, final List<TagEntity> tags){
        final Optional<BookEntity> oldBook = bookRepository.findById(id);
        if(oldBook.isPresent()){
            if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
                return null;
            }

            if(newBook.getTitle()!=null){
                oldBook.get().setTitle(newBook.getTitle());
            }
            if(newBook.getAuthor()!=null){
                oldBook.get().setAuthor(newBook.getAuthor());
            }
            oldBook.get().setNumberOP(newBook.getNumberOP());
            oldBook.get().setNotePerso(newBook.getNotePerso());
            if(newBook.getReleaseYear()!=null){
                oldBook.get().setReleaseYear(newBook.getReleaseYear());
            }
            if(newBook.getSummary()!=null){
                oldBook.get().setSummary(newBook.getSummary());
            }

            for(ReadingEntity reading: readings) {
                newBook.addReading(reading);
            }
            for(TagEntity tag : tags){
                tag.addBook(newBook);
            }

            oldBook.get().setReadings(newBook.getReadings());
            oldBook.get().setTags(newBook.getTags());

            return bookRepository.save(oldBook.get());
        }
        else {
            return null;
        }
    }
}