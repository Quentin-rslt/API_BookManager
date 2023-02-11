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

    /**
     * @param id Long
     * @return Reading by id
     */
    public ReadingEntity getReadingById(final Long id){
        return readingRepository.findById(id).isPresent() ? readingRepository.findById(id).get() : null;
    }

    /**
     * @return All reading
     */
    public List<ReadingEntity> getReadings(){
        return readingRepository.findAll();
    }

    /**
     * Delete reading by id
     *
     * @param id Long
     */
    public void deleteReadingById(final Long id){
        readingRepository.deleteById(id);
    }

    /**
     * Delete all reading
     */
    public void deleteReadings(){
        readingRepository.deleteAll();
    }

    /**
     * @param reading ReadingEntity
     * @return New reading
     */
    public ReadingEntity addReading(final ReadingEntity reading){
        return readingRepository.save(reading);
    }

    /**
     * @param id Long
     * @param newReading ReadingEntity
     * @return Old reading updated
     */
    public ReadingEntity updateReading(final Long id, final ReadingEntity newReading){
        Optional<ReadingEntity> oldReading = readingRepository.findById(id);

        if(oldReading.isPresent()){
            if(newReading.getStartReadingDate() != null){
                oldReading.get().setStartReadingDate(newReading.getStartReadingDate());
            }
            if(newReading.getEndReadingDate() != null){
                oldReading.get().setEndReadingDate(newReading.getEndReadingDate());
            }

            return readingRepository.save(oldReading.get());
        }
        else {
            return null;
        }
    }
}
