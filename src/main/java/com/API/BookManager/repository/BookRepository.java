package com.API.BookManager.repository;

import com.API.BookManager.model.BookEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("select b from BookEntity b where b.title = ?1 and b.author = ?2")
    Optional<BookEntity> findByTitleAndAuthor(String title, String author);

    @Query("select b.idBook from BookEntity b " +
            "where (?1 is null or upper(b.title) like upper(concat('%', ?1, '%'))) " +
            "and (?2 is null or upper(b.author) like upper(concat('%', ?2, '%'))) " +
            "and ((?3 is null or ?4 is null) or b.numberOP between ?3 and ?4)" +
            "and ((?5 is null or ?6 is null) or b.notePerso between ?5 and ?6)" +
            "and ((?7 is null or ?8 is null) or b.releaseYear between ?7 and ?8)" +
            "and (?9 is null or upper(b.summary) like upper(concat('%', ?9, '%'))) " +
            "and (?10 is null or b.isFav = ?10)")
    List<Integer> findBooksByCrtieria(String title, String author, int numberOPStart, int numberOPEnd, double notePersoStart, double notePersoEnd, int releaseYearStart, int releaseYearEnd, String summary, boolean isFav, Sort sort);
}