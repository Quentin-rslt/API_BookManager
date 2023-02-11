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

    /**
     * @param id Long
     * @return Tag by id
     */
    public TagEntity getTagById(final Long id){
        return tagRepository.findById(id).isPresent() ? tagRepository.findById(id).get() : null;
    }

    /**
     * @return All tags
     */
    public List<TagEntity> getTags(){
        return tagRepository.findAll();
    }

    /**
     * @param tagId Long
     * @return Books by id tag
     */
    public List<BookEntity> getBooksByIdTag(final Long tagId){
        return tagRepository.findById(tagId).isPresent() ? tagRepository.findById(tagId).get().getBooks() : null;
    }

    /**
     * Delete tag by id
     *
     * @param id Long
     */
    public void deleteTagById(final Long id){
        tagRepository.deleteById(id);
    }

    /**
     * Delete all tags
     */
    public void deleteTags(){ tagRepository.deleteAll(); }

    /**
     * @param tag TagEntity
     * @return New tag
     */
    public TagEntity addTag(final TagEntity tag){
        return tagRepository.findByTextTag(tag.getTextTag()).isPresent() ? null : tagRepository.save(tag);
    }

    /**
     * @param idTag Long
     * @param newTag TagEntity
     * @return Old tag updated
     */
    public TagEntity updateTag(final Long idTag, final TagEntity newTag){
        Optional<TagEntity> oldTag = tagRepository.findById(idTag);

        if(!newTag.getTextTag().equals(oldTag.get().getTextTag()) && tagRepository.findByTextTag(newTag.getTextTag()).isPresent()){
            return null;
        }

        if(newTag.getTextTag() != null){
            oldTag.get().setTextTag(newTag.getTextTag());
        }
        oldTag.get().setColorTag(newTag.getColorTag());

        return tagRepository.save(oldTag.get());
    }
}
