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
    public ReadingEntity getReadingById(@PathVariable(value = "id") final Long id){
        return readingService.getReadingById(id).get();
    }

    @GetMapping(value="/api/reading/all")
    public List<ReadingEntity> getReadings(){
        return readingService.getReadings();
    }

    @GetMapping(value = "/api/reading/all/book/{bookId}")
    public List<ReadingEntity> getReadingsByBookId(@PathVariable(value = "bookId")final Long bookId){
        return readingService.getReadingsByBoookId(bookId);
    }

    @DeleteMapping(value = "/api/deleteReading")
    public void deleteReading(@RequestBody final ReadingEntity reading){
        readingService.deleteReading(reading);
    }

    @DeleteMapping(value = "/api/deleteReading/{id}")
    public void deleteReadingById(@PathVariable(value = "id") final Long id){
        readingService.deleteReadingById(id);
    }

    @DeleteMapping(value = "/api/deleteReading/all")
    public void deleteReadings(){
        readingService.deleteReadings();
    }

    @DeleteMapping(value = "/api/deleteReading/all/book/{bookId}")
    public void deleteReadingsByBookId(@PathVariable(value = "bookId") final Long bookId){
        readingService.deleteReadingsByBookId(bookId);
    }

    @PostMapping(value = "/api/saveReading/save")
    public ReadingEntity saveReading(@RequestBody final ReadingEntity reading){
        return readingService.saveReading(reading);
    }
}
