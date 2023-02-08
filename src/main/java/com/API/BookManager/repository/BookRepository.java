package com.API.BookManager.repository;

import com.API.BookManager.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = "SELECT * FROM book WHERE title =: title AND author =: author", nativeQuery = true)
    Optional<BookEntity> findByTitleAndAuthor(@Param("title") final String title, @Param("author") final String author);
}