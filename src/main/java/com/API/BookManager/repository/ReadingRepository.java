package com.API.BookManager.repository;

import com.API.BookManager.model.ReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends JpaRepository<ReadingEntity, Long> {
    List<ReadingEntity> findByIdBook(final Long id);

    @Query("select r from ReadingEntity r where r.startReadingDate = ?1 and r.endReadingDate = ?2")
    Optional<ReadingEntity> findByStartReadingDateAndEndReadingDate(Date startReadingDate, Date endReadingDate);


}