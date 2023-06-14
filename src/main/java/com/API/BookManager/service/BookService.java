package com.API.BookManager.service;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import com.API.BookManager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.BookManager.repository.BookRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagRepository tagRepository;

    /**
     * Get Book by id
     *
     * @param id Long
     * @return BookEntity
     */
    public BookEntity getBookById(final Long id){
        return bookRepository.findById(id).isPresent() ? bookRepository.findById(id).get() : null;
    }

    /**
     * Get all Book
     *
     * @return List<BookEntity>
     */
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    /**
     * Delete Book by id
     *
     * @param id Long
     */
    public void deleteBookById(final Long id){
        bookRepository.deleteById(id);
    }

    /**
     * Delete all Book
     */
    public void deleteBooks(){ bookRepository.deleteAll(); }

    /**
     * Can fav a book or not
     *
     * @param id Long
     * @param isFav boolean
     * @return BookEntity
     */
    public BookEntity favBook(final Long id, boolean isFav){
        BookEntity book = this.getBookById(id);

        book.setFav(isFav);

        return bookRepository.save(book);
    }

    /**
     * Create a new Book
     *
     * @param newBook BookEntity
     * @return BookEntity
     */
    public BookEntity addBook(final BookEntity newBook){
        if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
            return null;
        }

        //Set book in reading table
        newBook.getReadings().forEach(readingEntity -> readingEntity.setBook(newBook));

        //Set an existing tag from the new book
        for(int i = 0; i<newBook.getTags().size(); i++){
            if(tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).isPresent()){
                newBook.getTags().set(i, tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).get());
            }
            else{
                return null;
            }
        }

        return bookRepository.save(newBook);
    }

    /**
     * Update a Book
     *
     * @param id Long
     * @param newBook BookEntity
     * @return BookEntity
     */
    public BookEntity updateBook(final Long id, final BookEntity newBook){
        final Optional<BookEntity> oldBook = bookRepository.findById(id);
        if(oldBook.isPresent()){
            if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
                if(newBook.getTitle().equals(oldBook.get().getTitle()) && newBook.getAuthor().equals(oldBook.get().getAuthor())) {
                    if(processOfUpdateBook(oldBook.get(), newBook)){
                        return null;
                    }
                }
                else{
                    return null;
                }
            }
            else{
                if(processOfUpdateBook(oldBook.get(), newBook)){
                    return null;
                }
            }

            return bookRepository.save(oldBook.get());
        }
        else {
            return null;
        }
    }

    /**
     * If tag don't exist, we don't create the book, it is necessary that tag exist
     *
     * @param oldBook BookEntity
     * @param newBook BookEntity
     * @return False if the tag don't exist
     */
    private boolean processOfUpdateBook(BookEntity oldBook, BookEntity newBook){
        boolean tagNotExist = false;

        //Update data of the book
        if(newBook.getTitle()!=null){
            oldBook.setTitle(newBook.getTitle());
        }
        if(newBook.getAuthor()!=null){
            oldBook.setAuthor(newBook.getAuthor());
        }
        oldBook.setNumberOP(newBook.getNumberOP());
        oldBook.setNotePerso(newBook.getNotePerso());
        if(newBook.getReleaseYear()!=null){
            oldBook.setReleaseYear(newBook.getReleaseYear());
        }
        if(newBook.getSummary()!=null){
            oldBook.setSummary(newBook.getSummary());
        }

        //clear these readings and tags
        oldBook.getReadings().clear();
        oldBook.getTags().clear();

        //Remove the relation between tags and the old book
        for(TagEntity tag : newBook.getTags()){
            tag.getBooks().remove(oldBook);
        }

        //Add new readings on the old book
        for(int i = 0; i<newBook.getReadings().size(); i++){
            ReadingEntity reading = newBook.getReadings().get(i);
            oldBook.getReadings().add(reading);
            reading.setBook(oldBook);
        }

        //Add an existing tag from the old book
        for (int i = 0; i < newBook.getTags().size(); i++) {
            if (tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).isPresent()) {
                oldBook.getTags().add(tagRepository.findByTextTag(newBook.getTags().get(i).getTextTag()).get());
            }
            else {
                tagNotExist = true;
            }
        }

        return tagNotExist;
    }
}