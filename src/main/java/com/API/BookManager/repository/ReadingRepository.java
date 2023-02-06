package com.API.BookManager.repository;

import com.API.BookManager.model.ReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<ReadingEntity, Long> {
}