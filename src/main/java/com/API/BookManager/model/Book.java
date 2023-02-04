package com.API.BookManager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String image;
    private int numberOP;
    private double notePerso;
    private double noteBabelio;
    private String releaseYear;
    private int avReadingTime;
    private int numberReading;
    private String summary;
}