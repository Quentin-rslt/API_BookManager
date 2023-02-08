package com.API.BookManager.repository;

import com.API.BookManager.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    @Query("SELECT t FROM TagEntity t INNER JOIN t.books books WHERE books.idBook = ?1")
    List<TagEntity> findTagsByBookId(final Long idBook);
}