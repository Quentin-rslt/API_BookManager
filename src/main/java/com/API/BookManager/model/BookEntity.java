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
    private int releaseYear;

    @Column(name="summary")
    private String summary;

    @Column(name="isFav")
    private boolean isFav;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadingEntity> readings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="tag_book", joinColumns=@JoinColumn(name="idBook"), inverseJoinColumns=@JoinColumn(name="idTag"))
    @OrderBy("idTag")
    private List<TagEntity> tags = new ArrayList<>();
}