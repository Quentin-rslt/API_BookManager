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

    @GetMapping(value = "/api/tag/all/book/{idBook}")
    public List<TagEntity> getTagsByIdBook(@PathVariable(value = "idBook") final Long idBook){
        return tagService.getTagsByIdBook(idBook);
    }

    @GetMapping(value = "/api/tag/{idTag}/book/all")
    public List<BookEntity> getBooksByIdTag(@PathVariable(value = "idTag") final Long idTag){
        return tagService.getBooksByIdTag(idTag);
    }

    @DeleteMapping(value = "/api/deleteTag/{id}")
    public void deleteTagById(@PathVariable(value = "id") final Long id){
        tagService.deleteTagById(id);
    }

    @DeleteMapping(value = "/api/deleteTag/all")
    public void deleteTags(){
        tagService.deleteTags();
    }

    @PostMapping(value = "/api/addTag")
    public TagEntity addTag(@RequestBody final  TagEntity tag) {
        return tagService.addTag(tag);
    }

    @PutMapping(value = "/api/updateTag/{idTag}")
    public TagEntity updateTag(@PathVariable(value = "idTag") final Long idTag, @RequestBody final  TagEntity newTag) {
        return tagService.updateTag(idTag, newTag);
    }
}
