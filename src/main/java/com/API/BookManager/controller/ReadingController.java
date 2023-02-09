package com.API.BookManager.controller;

import com.API.BookManager.model.ReadingEntity;
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
        return readingService.getReadingById(id).isPresent() ? readingService.getReadingById(id).get() : null;
    }

    @GetMapping(value="/api/reading/all")
    public List<ReadingEntity> getReadings(){
        return readingService.getReadings();
    }

    @GetMapping(value = "/api/reading/all/book/{idBook}")
    public List<ReadingEntity> getReadingsByIdBook(@PathVariable(value = "idBook")final Long idBook){
        return readingService.getReadingsByIdBook(idBook);
    }

    @DeleteMapping(value = "/api/deleteReading/{id}")
    public void deleteReadingById(@PathVariable(value = "id") final Long id){
        readingService.deleteReadingById(id);
    }

    @DeleteMapping(value = "/api/deleteReading/all")
    public void deleteReadings(){
        readingService.deleteReadings();
    }

    @PostMapping(value = "/api/addReading")
    public ReadingEntity addReading(@RequestBody final ReadingEntity reading){
        return readingService.addReading(reading);
    }

    @PutMapping(value = "/api/updateReading/{readingId}")
    public ReadingEntity updateReading(@PathVariable(value = "readingId") final Long readingId, @RequestBody final ReadingEntity newReading){
        return readingService.updateReading(readingId, newReading);
    }
}
