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

    public List<ReadingEntity> getReadingsByIdBook(final Long id){
        return readingRepository.findByIdBook(id);
    }

    public void deleteReadingById(final Long id){
        readingRepository.deleteById(id);
    }

    public void deleteReadings(){
        readingRepository.deleteAll();
    }

    public ReadingEntity addReading(final ReadingEntity reading){
        return readingRepository.save(reading);
    }

    public ReadingEntity updateReading(final Long id, final ReadingEntity newReading){
        Optional<ReadingEntity> oldReading = readingRepository.findById(id);

        if(readingRepository.findByStartReadingDateAndEndReadingDate(newReading.getStartReadingDate(), newReading.getEndReadingDate()).isPresent()){
            return null;
        }
        if(oldReading.isPresent()){
            if(newReading.getStartReadingDate() != null){
                oldReading.get().setStartReadingDate(newReading.getStartReadingDate());
            }
            if(newReading.getEndReadingDate() != null){
                oldReading.get().setEndReadingDate(newReading.getEndReadingDate());
            }
            if(newReading.getBook() != null){
                oldReading.get().setBook(newReading.getBook());
            }

            return readingRepository.save(oldReading.get());
        }
        else {
            return null;
        }
    }
}
