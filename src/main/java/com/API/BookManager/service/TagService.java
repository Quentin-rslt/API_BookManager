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

    public List<TagEntity> getTagsByIdBook(final Long idBook){
        return tagRepository.findTagsByIdBook(idBook);
    }

    public List<BookEntity> getBooksByIdTag(final Long tagId){
        return tagRepository.findById(tagId).isPresent() ? tagRepository.findById(tagId).get().getBooks() : null;
    }

    public void deleteTagById(final Long id){
        tagRepository.deleteById(id);
    }

    public void deleteTags(){ tagRepository.deleteAll(); }

    public TagEntity addTag(final TagEntity tag){
        return tagRepository.findByTextTag(tag.getTextTag()).isPresent() ? null : tagRepository.save(tag);
    }

    public TagEntity updateTag(final Long idTag, final TagEntity newTag){
        Optional<TagEntity> oldTag = tagRepository.findById(idTag);

        if(tagRepository.findByTextTag(newTag.getTextTag()).isPresent()){
            return null;
        }

        if(oldTag.isPresent()){
            if(newTag.getTextTag() != null){
                oldTag.get().setTextTag(newTag.getTextTag());
            }
            oldTag.get().setColorTag(newTag.getColorTag());

            return tagRepository.save(oldTag.get());
        }
        else{
            return null;
        }
    }
}
