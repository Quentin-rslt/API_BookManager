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

    /**
     * @param id Long
     * @return Book by id of book
     */
    public BookEntity getBookById(final Long id){
        return bookRepository.findById(id).isPresent() ? bookRepository.findById(id).get() : null;
    }

    /**
     * @param title String
     * @param author String
     * @return Book by title and author
     */
    public BookEntity getBookByTitleAndAuthor(final String title, final String author){
        return bookRepository.findByTitleAndAuthor(title, author) .isPresent() ? bookRepository.findByTitleAndAuthor(title, author).get() : null;
    }

    /**
     * @return All book
     */
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    /**
     * @param idBook Long
     * @return tags by id book
     */
    public List<TagEntity> getTagsByIdBook(final Long idBook){
        return bookRepository.findById(idBook).isPresent() ? bookRepository.findById(idBook).get().getTags() : null;
    }

    /**
     * @param idBook Long
     * @return readings by id book
     */
    public List<ReadingEntity> getReadingsByIdBook(final Long idBook){
        return bookRepository.findById(idBook).isPresent() ? bookRepository.findById(idBook).get().getReadings() : null;
    }

    /**
     * Delete book by id
     *
     * @param id Long
     */
    public void deleteBookById(final Long id){
        bookRepository.deleteById(id);
    }

    /**
     * Delete all book
     */
    public void deleteBooks(){ bookRepository.deleteAll(); }

    /**
     * @param newBook BookEntity
     * @return New book to create a book
     */
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

    /**
     * @param id Long
     * @param newBook BookEntity
     * @return Old book to update this
     */
    public BookEntity updateBook(final Long id, final BookEntity newBook){
        final Optional<BookEntity> oldBook = bookRepository.findById(id);
        if(oldBook.isPresent()){
            if(bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor()).isPresent()){
                return null;
            }

            //Update data of the book
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

            //clear these readings and tags
            oldBook.get().getReadings().clear();
            oldBook.get().getTags().clear();

            //Remove the relation between tags and the old book
            for(TagEntity tag : newBook.getTags()){
                tag.getBooks().remove(oldBook.get());
            }

            //Add new readings on the old book
            for(int i = 0; i<newBook.getReadings().size(); i++){
                ReadingEntity reading = newBook.getReadings().get(i);
                oldBook.get().getReadings().add(reading);
                reading.setBook(newBook);
            }

            //Add tag of the old book, if the tag that is entered is already existing, and create tag if not exist
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