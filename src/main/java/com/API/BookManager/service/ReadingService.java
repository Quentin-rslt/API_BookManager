package com.API.BookManager.service;

import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {
    @Autowired
    ReadingRepository readingRepository;

    public ReadingEntity getReadingById(final Long id){
        return readingRepository.findById(id).get();
    }

    public List<ReadingEntity> getReadings(){
        return readingRepository.findAll();
    }

    public void deleteReadingById(final Long id){
        readingRepository.deleteById(id);
    }

    public ReadingEntity saveReading(final ReadingEntity reading){
        return readingRepository.save(reading);
    }
}
