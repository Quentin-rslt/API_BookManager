package com.API.BookManager.service;

import com.API.BookManager.model.ReadingEntity;
import com.API.BookManager.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {
    @Autowired
    private ReadingRepository readingRepository;

    public Optional<ReadingEntity> getReadingById(final Long id){
        return readingRepository.findById(id);
    }

    public List<ReadingEntity> getReadings(){
        return readingRepository.findAll();
    }

    public List<ReadingEntity> getReadingsByBoookId(final Long id){
        return readingRepository.findByBookId(id);
    }

    public void deleteReadingById(final Long id){
        readingRepository.deleteById(id);
    }

    public void deleteReadings(){
        readingRepository.deleteAll();
    }

    public ReadingEntity saveReading(final ReadingEntity reading){
        return readingRepository.save(reading);
    }
}
