package com.API.BookManager.repository;

import com.API.BookManager.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    @Query("select t from TagEntity t where t.textTag = ?1")
    Optional<TagEntity> findByTextTag(String textTag);

    @Query("select t from TagEntity t inner join t.books books where books.idBook = ?1")
    List<TagEntity> findByIdBook(Long idBook);


}