package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
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
    public TagEntity getTagById(@PathVariable(value = "id") final Long id){
        return tagService.getTagById(id).isPresent() ? tagService.getTagById(id).get() : null;
    }

    @GetMapping(value="/api/tag/all")
    public List<TagEntity> getTags(){
        return tagService.getTags();
    }

    @GetMapping(value = "/api/tag/all/book/{bookId}")
    public List<TagEntity> getTagsByBookId(@PathVariable(value = "bookId") final Long bookId){
        return tagService.getTagsByBookId(bookId);
    }

    @GetMapping(value = "/api/tag/{tagId}/book/all")
    public List<BookEntity> getBooksByTagId(@PathVariable(value = "tagId") final Long tagId){
        return tagService.getBooksByTagId(tagId);
    }

    @DeleteMapping(value = "/api/deleteTag/{id}")
    public void deleteTagById(@PathVariable(value = "id") final Long id){
        tagService.deleteTagById(id);
    }

    @DeleteMapping(value = "/api/deleteTag/all")
    public void deleteTags(){
        tagService.deleteTags();
    }

    @PostMapping(value = "/api/saveTag/save")
    public TagEntity saveTag(@RequestBody final  TagEntity tag) {
        return tagService.saveTag(tag);
    }
}
