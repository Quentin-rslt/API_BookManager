package com.API.BookManager.repository;

import com.API.BookManager.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}