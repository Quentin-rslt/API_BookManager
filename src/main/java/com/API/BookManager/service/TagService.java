package com.API.BookManager.service;

import com.API.BookManager.model.TagEntity;
import com.API.BookManager.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public TagEntity getTagById(final Long id){
        return tagRepository.findById(id).get();
    }

    public List<TagEntity> getTags(){
        return tagRepository.findAll();
    }

    public void deleteTagById(final Long id){
        tagRepository.deleteById(id);
    }

    public TagEntity saveTag(TagEntity tag){
        return tagRepository.save(tag);
    }
}
