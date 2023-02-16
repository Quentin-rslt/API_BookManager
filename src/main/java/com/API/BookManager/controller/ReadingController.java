package com.API.BookManager.controller;

import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class ReadingController {
    @Autowired
    private ReadingService readingService;

    /**
     * @see ReadingService#getReadingById(Long)
     */
    @GetMapping(value = "/api/reading/{id}")
    public ReadingEntity getReadingById(@PathVariable(value = "id") final Long id){
        return readingService.getReadingById(id);
    }

    /**
     * @see ReadingService#getReadings()
     */
    @GetMapping(value="/api/reading/all")
    public List<ReadingEntity> getReadings(){
        return readingService.getReadings();
    }

    /**
     * @see ReadingService#deleteReadingById(Long) 
     */
    @DeleteMapping(value = "/api/deleteReading/{id}")
    public void deleteReadingById(@PathVariable(value = "id") final Long id){
        readingService.deleteReadingById(id);
    }

    /**
     * @see ReadingService#deleteReadings() 
     */
    @DeleteMapping(value = "/api/deleteReading/all")
    public void deleteReadings(){
        readingService.deleteReadings();
    }

    /**
     * @see ReadingService#addReading(ReadingEntity)
     */
    @PostMapping(value = "/api/addReading")
    public ReadingEntity addReading(@RequestBody final ReadingEntity reading){
        return readingService.addReading(reading);
    }

    /**
     * @see ReadingService#updateReading(Long, ReadingEntity)
     */
    @PutMapping(value = "/api/updateReading/{readingId}")
    public ReadingEntity updateReading(@PathVariable(value = "readingId") final Long readingId, @RequestBody final ReadingEntity newReading){
        return readingService.updateReading(readingId, newReading);
    }
}
