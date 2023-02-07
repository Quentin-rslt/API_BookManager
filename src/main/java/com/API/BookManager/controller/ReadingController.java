package com.API.BookManager.controller;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.service.BookService;
import com.API.BookManager.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReadingController {
    @Autowired
    private ReadingService readingService;
    @GetMapping(value = "/api/reading/{id}")
    public ReadingEntity getReadingById(@PathVariable(value = "id") Long id){
        return readingService.getReadingById(id).get();
    }

    @GetMapping(value="/api/reading/all")
    public List<ReadingEntity> getReadings(){
        return readingService.getReadings();
    }

    @DeleteMapping(value = "/api/reading/{id}")
    public void deleteReadingById(@PathVariable(value = "id") long id){
        readingService.deleteReadingById(id);
    }

    @DeleteMapping(value = "/api/reading/all")
    public void deleteReadings(){
        readingService.deleteReadings();
    }

    @PostMapping(value = "/api/reading/save")
    public ReadingEntity saveReading(@RequestBody ReadingEntity reading){
        return readingService.saveReading(reading);
    }
}
