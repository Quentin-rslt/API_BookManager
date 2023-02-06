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
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTag")
    private Long idTag;

    @Column(name="textTag")
    private String textTag;

    @Column(name="colorTag")
    private int colorTag;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "tag_book",joinColumns = @JoinColumn(name = "idTag"),inverseJoinColumns = @JoinColumn(name = "idBook"))
    private List<BookEntity> books = new ArrayList<>();

    /****************************** helpers methods ******************************/
    public void addBook(BookEntity book) {
        books.add(book);
        book.getTags().add(this);
    }

    public void removeBook(BookEntity book) {
        books.remove(book);
        book.getTags().remove(this);
    }
}