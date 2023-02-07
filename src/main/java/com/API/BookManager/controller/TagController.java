package com.API.BookManager.controller;

import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.model.TagEntity;
import com.API.BookManager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping(value = "/api/tag/{id}")
    public TagEntity getTagById(@PathVariable(value = "id") Long id){
        return tagService.getTagById(id).get();
    }

    @GetMapping(value="/api/tag/all")
    public List<TagEntity> getTags(){
        return tagService.getTags();
    }

    @DeleteMapping(value = "/api/tag/{id}")
    public void deleteTagById(@PathVariable(value = "id") long id){
        tagService.deleteTagById(id);
    }

    @DeleteMapping(value = "/api/tag/all")
    public void deleteTags(){
        tagService.deleteTags();
    }

    @PostMapping(value = "/api/tag/save")
    public TagEntity saveTag(@RequestBody TagEntity tag) {
        return tagService.saveTag(tag);
    }
}
