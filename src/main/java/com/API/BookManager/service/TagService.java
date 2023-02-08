package com.API.BookManager.service;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.TagEntity;
import com.API.BookManager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Optional<TagEntity> getTagById(final Long id){
        return tagRepository.findById(id);
    }

    public List<TagEntity> getTags(){
        return tagRepository.findAll();
    }

    public List<TagEntity> getTagsByBookId(final Long bookId){
        return tagRepository.findTagsByBookId(bookId);
    }

    public List<BookEntity> getBooksByTagId(final Long tagId){
        return tagRepository.findById(tagId).get().getBooks();
    }

    public void deleteTagById(final Long id){
        tagRepository.deleteById(id);
    }

    public void deleteTags(){ tagRepository.deleteAll(); }

    public TagEntity saveTag(final TagEntity tag){
        return tagRepository.save(tag);
    }
}
