package com.API.BookManager.repository;

import com.API.BookManager.model.BookEntity;
import com.API.BookManager.model.ReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<ReadingEntity, Long> {
    List<ReadingEntity> findByBookId(final Long id);
    void deleteByBookId(final Long id);
}