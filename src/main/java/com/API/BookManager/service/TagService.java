package com.API.BookManager.service;
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
     * Get Tag by id
     *
     * @param id Long
     * @return TagEntity
     */
    public TagEntity getTagById(final Long id){
        return tagRepository.findById(id).isPresent() ? tagRepository.findById(id).get() : null;
    }

    /**
     * Get all Tag
     *
     * @return List<TagEntity>
     */
    public List<TagEntity> getTags(){
        return tagRepository.findAll();
    }

    /**
     * Delete Tag by id
     *
     * @param id Long
     */
    public void deleteTagById(final Long id){
        tagRepository.deleteById(id);
    }

    /**
     * Delete all Tag
     */
    public void deleteTags(){ tagRepository.deleteAll(); }

    /**
     * Create a new Tag
     *
     * @param tag TagEntity
     * @return TagEntity
     */
    public TagEntity addTag(final TagEntity tag){
        return tagRepository.findByTextTag(tag.getTextTag()).isPresent() ? null : tagRepository.save(tag);
    }

    /**
     * Update a Tag
     *
     * @param idTag Long
     * @param newTag TagEntity
     * @return TagEntity
     */
    public TagEntity updateTag(final Long idTag, final TagEntity newTag){
        Optional<TagEntity> oldTag = tagRepository.findById(idTag);

        if(oldTag.isPresent()){
            if(!newTag.getTextTag().equals(oldTag.get().getTextTag()) && tagRepository.findByTextTag(newTag.getTextTag()).isPresent()){
                return null;
            }

            if(newTag.getTextTag() != null){
                oldTag.get().setTextTag(newTag.getTextTag());
            }
            oldTag.get().setColorTag(newTag.getColorTag());

            return tagRepository.save(oldTag.get());
        }
        else {
            return null;
        }
    }
}
