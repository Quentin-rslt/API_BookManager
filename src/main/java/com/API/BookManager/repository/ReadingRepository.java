package com.API.BookManager.repository;

import com.API.BookManager.model.ReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<ReadingEntity, Long> {
    @Query("select r from ReadingEntity r where r.book.idBook = ?1")
    List<ReadingEntity> findByIdBook(Long idBook);
}