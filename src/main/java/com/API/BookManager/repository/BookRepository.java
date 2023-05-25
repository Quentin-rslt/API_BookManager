package com.API.BookManager.repository;

import com.API.BookManager.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("select b from BookEntity b where b.title = ?1 and b.author = ?2")
    Optional<BookEntity> findByTitleAndAuthor(String title, String author);
}