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

    @Column(name="image")
    private String image;

    @Column(name="numberOP")
    private int numberOP;

    @Column(name="notePerso")
    private double notePerso;

    @Column(name="noteBabelio")
    private double noteBabelio;

    @Column(name="releaseYear")
    private String releaseYear;

    @Column(name="summary")
    private String summary;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReadingEntity> readings = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<TagEntity> tags = new ArrayList<>();

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