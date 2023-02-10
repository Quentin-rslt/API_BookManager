package com.API.BookManager.service;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import com.API.BookManager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.BookManager.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagRepository tagRepository;

    public Optional<BookEntity> getBookById(final Long id){
        return bookRepository.findById(id);
    }

    public Optional<BookEntity> getBookByTitleAndAuthor(final String title, final String author){
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookById(final Long id){
        bookRepository.deleteById(id);
    }

    public void deleteBooks(){ bookRepository.deleteAll(); }

    public BookEntity addBook(final BookEntity newBook){
        if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
            return null;
        }
        //Set book in reading table
        newBook.getReadings().forEach(readingEntity -> readingEntity.setBook(newBook));
        //Set tag of the new book, if the tag that is entered is already existing
        for(int i = 0; i<newBook.getTags().size(); i++){
            if(tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).isPresent()){
                newBook.getTags().set(i, tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).get());
            }
        }

        return bookRepository.save(newBook);
    }

    public BookEntity updateBook(final Long id, final BookEntity newBook){
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

            oldBook.get().getReadings().clear();
            oldBook.get().getTags().clear();

            for(TagEntity tag : newBook.getTags()){
                tag.getBooks().remove(oldBook.get());
            }

            for(ReadingEntity reading : newBook.getReadings()){
                oldBook.get().addReading(reading);
            }

            for(int i = 0; i<newBook.getTags().size(); i++){
                if(tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).isPresent()){
                    oldBook.get().getTags().add(tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).get());
                }
                else {
                    oldBook.get().getTags().add(newBook.getTags().get(i));
                }
            }

            return bookRepository.save(oldBook.get());
        }
        else {
            return null;
        }
    }
}