package com.API.BookManager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBook")
    private Long idBook;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="numberOP")
    private int numberOP;

    @Column(name="notePerso")
    private double notePerso;

    @Column(name="releaseYear")
    private String releaseYear;

    @Column(name="summary")
    private String summary;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadingEntity> readings = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TagEntity> tags = new ArrayList<>();

    public BookEntity() {
    }

    public BookEntity(String title, String author, int numberOP, double notePerso, String releaseYear, String summary) {
        super();
        this.title = title;
        this.author = author;
        this.numberOP = numberOP;
        this.notePerso = notePerso;
        this.releaseYear = releaseYear;
        this.summary = summary;
    }

    /****************************** helpers methods ******************************/
    public void addReading(ReadingEntity reading) {
        readings.add(reading);
        reading.setBook(this);
    }
    public void removeReading(ReadingEntity reading) {
        readings.remove(reading);
        reading.setBook(null);
    }
}